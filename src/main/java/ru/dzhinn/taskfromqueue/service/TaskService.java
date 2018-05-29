package ru.dzhinn.taskfromqueue.service;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import ru.dzhinn.taskfromqueue.Task;

import java.io.IOException;
import java.io.InputStream;

public abstract class TaskService {

    final static Logger logger = Logger.getLogger(TaskService.class);


    public void runTask(final Task task){

        if (task.getTaskType() == 1){
            getTask1Service().prepareForTask(task);
            getTaskResults().saveResult(task, getTask1Service().doTask(task));
        } else if (task.getTaskType() == 2){

            int subsystemTaskId = getTask2AsyncService().scheduleTask(task);
            Object result = null;
            while ((result = getTask2AsyncService().getStatus(subsystemTaskId)) == null){
                getTaskResults().saveResult(task, result);
            }

        } else if (task.getTaskType() == 3){

            getTask3Payload().init(task.getPayloadId());
            String nextLine = null;
            while ((nextLine = getTask3Payload().nextLine()) != null){
                try {
                    getTask3Service().doTask(IOUtils.toInputStream(nextLine, "UTF-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            getTask3Payload().finish();
        } else {
            logger.warn("No service for task type=" + task.getTaskType());
            return;
        }


    }

    public abstract Task1Service getTask1Service();

    public abstract Task2AsyncService getTask2AsyncService();

    public abstract Task3Service getTask3Service();

    public abstract TaskResults getTaskResults();

    public abstract Task3Payload getTask3Payload();
}
