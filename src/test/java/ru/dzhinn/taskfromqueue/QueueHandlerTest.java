package ru.dzhinn.taskfromqueue;

public class QueueHandlerTest {

    public static void main(String[] args) {
        QueueHandler queueHandler = new QueueHandler();

        queueHandler.handle(new QueueMock());
    }
}
