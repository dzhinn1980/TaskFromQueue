package ru.dzhinn.taskfromqueue.mock;

import ru.dzhinn.taskfromqueue.Queue;
import ru.dzhinn.taskfromqueue.Task;

public class QueueMock implements Queue {

    private int taskId;

    @Override
    public void connect() {
        System.out.println("Queue connected...");
    }

    @Override
    public Task getNextTask() throws IllegalStateException {
        taskId++;
        if (taskId%5 != 0) {
            return new TaskMock(taskId);
        } else {
            return null;
        }
    }

    @Override
    public void disconnect() {
        System.out.println("Queue disconnected...");
    }
}
