package ru.dzhinn.taskfromqueue.mock;

import ru.dzhinn.taskfromqueue.Task;

public class TaskMock implements Task {

    private int taskId;

    public TaskMock(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public int getTaskId() {
        return taskId;
    }

    @Override
    public int getTaskType() {
        return taskId%3 + 1;
    }

    @Override
    public int getPayloadId() {
        return 0;
    }
}
