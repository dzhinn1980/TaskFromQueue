package ru.dzhinn.taskfromqueue.mock;

import ru.dzhinn.taskfromqueue.service.Task3Payload;

public class Task3PayloadMockImpl implements Task3Payload {

    int lineCount;

    @Override
    public void init(int payloadId) {
//        logger.info("Task3Payload.init");
    }

    @Override
    public String nextLine() {

        if (lineCount++ < 1000) {
            return "qwertyui" + lineCount;
        } else {
            lineCount = 0;
            return null;
        }
    }

    @Override
    public void finish() {
//        logger.info("Task3Payload.finish");
    }
}
