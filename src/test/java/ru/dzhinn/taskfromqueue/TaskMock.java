package ru.dzhinn.taskfromqueue;

public class TaskMock implements Task{

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
        return 0;
    }

    @Override
    public int getPayloadId() {
        return 0;
    }
}
