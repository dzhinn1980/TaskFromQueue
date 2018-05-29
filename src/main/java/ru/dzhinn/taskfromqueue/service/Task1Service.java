package ru.dzhinn.taskfromqueue.service;

import ru.dzhinn.taskfromqueue.Task;

public interface Task1Service{

    void prepareForTask(Task t); // подготовить к испольнению задачи

    Object doTask(Task t);

}
