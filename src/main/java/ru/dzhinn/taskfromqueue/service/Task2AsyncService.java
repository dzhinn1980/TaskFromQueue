package ru.dzhinn.taskfromqueue.service;

import ru.dzhinn.taskfromqueue.Task;

public interface Task2AsyncService {
    int scheduleTask(Task t); // отправить задачу на выполнение, результат - id задачи в подсистеме

    Object getStatus(int subsystemTaskId); // Получить результат выполнения задачи, возвращается гарантированно, если null, значит задача ещё не выполнена.
}
