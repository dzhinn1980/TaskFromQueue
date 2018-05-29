package ru.dzhinn.taskfromqueue.mock;

import org.apache.log4j.Logger;
import ru.dzhinn.taskfromqueue.service.Task3Service;

import java.io.InputStream;

public class Task3ServiceMockImpl implements Task3Service {

    final static Logger logger = Logger.getLogger(Task3ServiceMockImpl.class);

    @Override
    public void doTask(InputStream stream) {
        logger.info("Read bytes " + stream);
    }
}
