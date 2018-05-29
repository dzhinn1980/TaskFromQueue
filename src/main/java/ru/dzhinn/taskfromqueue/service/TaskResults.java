package ru.dzhinn.taskfromqueue.service;

import ru.dzhinn.taskfromqueue.Task;

public interface TaskResults {
    void saveResult(Task t, Object result);
}
