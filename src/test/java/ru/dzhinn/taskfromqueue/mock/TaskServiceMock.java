package ru.dzhinn.taskfromqueue.mock;

import org.apache.log4j.Logger;
import ru.dzhinn.taskfromqueue.QueueHandler;
import ru.dzhinn.taskfromqueue.Task;
import ru.dzhinn.taskfromqueue.service.*;

import java.io.InputStream;

public class TaskServiceMock extends TaskService {

    final static Logger logger = Logger.getLogger(QueueHandler.class);

    private Task3Payload task3Payload;
    private Task3Service task3Service;
    private Task2AsyncService task2AsyncService;
    private Task1Service task1Service;
    
    @Override
    public Task1Service getTask1Service() {
        if (task1Service == null){
            task1Service = new Task1ServiceMockImpl();
        }

        return task1Service;
    }

    @Override
    public Task2AsyncService getTask2AsyncService() {
        if (task2AsyncService == null){
            task2AsyncService = new Task2AsyncServiceMockImpl();
        }

        return task2AsyncService;
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

    @Override
    public Task3Service getTask3Service() {
        if (task3Service == null){
            task3Service = new Task3ServiceMockImpl();
        }

        return task3Service;
    }

    @Override
    public Task3Payload getTask3Payload() {

        if (task3Payload == null){
            task3Payload = new Task3PayloadMockImpl();
        }

        return task3Payload;
    }
}
