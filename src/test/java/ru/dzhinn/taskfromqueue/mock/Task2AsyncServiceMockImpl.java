package ru.dzhinn.taskfromqueue.mock;

import org.apache.log4j.Logger;
import ru.dzhinn.taskfromqueue.Task;
import ru.dzhinn.taskfromqueue.service.Task2AsyncService;

public class Task2AsyncServiceMockImpl implements Task2AsyncService {

    final static Logger logger = Logger.getLogger(Task2AsyncServiceMockImpl.class);

    @Override
    public int scheduleTask(final Task t) {

        logger.info("Task " + t.getTaskId() + " scheduled; thread: " + Thread.currentThread().getName());

        return t.getTaskId();
    }

    @Override
    public Object getStatus(int subsystemTaskId) {
        logger.info("Getting task status with subsystemTaskId " + subsystemTaskId);
        return "Task2AsyncService.getStatus";
    }
}
