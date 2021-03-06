package ru.dzhinn.taskfromqueue;

import ru.dzhinn.taskfromqueue.mock.QueueMock;
import ru.dzhinn.taskfromqueue.mock.TaskServiceMock;

public class QueueHandlerTest {

    public static void main(String[] args) {
        QueueHandler queueHandler = new QueueHandler();

        queueHandler.setAlwaysRun(true);

        queueHandler.handle(new QueueMock(), new TaskServiceMock());
    }
}
