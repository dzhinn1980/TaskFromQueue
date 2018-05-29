package ru.dzhinn.taskfromqueue;

import org.apache.log4j.Logger;
import ru.dzhinn.taskfromqueue.service.TaskResults;
import ru.dzhinn.taskfromqueue.service.Task1Service;
import ru.dzhinn.taskfromqueue.service.Task2AsyncService;
import ru.dzhinn.taskfromqueue.service.TaskService;

public class TaskServiceMock extends TaskService {

    final static Logger logger = Logger.getLogger(QueueHandler.class);

//    private Subsystem  subsystem = new SubsystemMock();
    
    @Override
    public Task1Service getTask1Service() {
        return new Task1Service() {
            @Override
            public void prepareForTask(Task t) {
                logger.info("Task " + t.getTaskId() + " prepared");
            }

            @Override
            public Object doTask(Task t) {
                logger.info("Task " + t.getTaskId() + " done");
                return "Task1Service.doTask";
            }
        };
    }

    @Override
    public Task2AsyncService getTask2AsyncService() {
        return new Task2AsyncService() {
            @Override
            public int scheduleTask(final Task t) {

                logger.info("Task " + t.getTaskId() + " scheduled");

                return t.getTaskId();
            }

            @Override
            public Object getStatus(int subsystemTaskId) {
                logger.info("Getting task status with subsystemTaskId " + subsystemTaskId);
                return "Task2AsyncService.getStatus";
            }
        };
    }

    @Override
    public TaskResults getTaskResults() {
        return new TaskResults() {
            @Override
            public void saveResult(Task t, Object result) {
                logger.info("Task " + t.getTaskId() + " saved: " + result.toString());
            }
        };
    }
}
