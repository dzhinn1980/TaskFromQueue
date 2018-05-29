package ru.dzhinn.taskfromqueue.mock;

import org.apache.log4j.Logger;
import ru.dzhinn.taskfromqueue.Task;
import ru.dzhinn.taskfromqueue.service.Task1Service;

public class Task1ServiceMockImpl implements Task1Service {

    final static Logger logger = Logger.getLogger(Task1ServiceMockImpl.class);

    @Override
    public void prepareForTask(Task t) {
        logger.info("Task " + t.getTaskId() + " prepared; thread: " + Thread.currentThread().getName());
    }

    @Override
    public Object doTask(Task t) {
        logger.info("Task " + t.getTaskId() + " done");
        return "Task1Service.doTask";
    }
}
