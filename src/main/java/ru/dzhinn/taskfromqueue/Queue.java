package ru.dzhinn.taskfromqueue;

public interface Queue {
    public void connect(); // Подключиться к очереди

    public Task getNextTask() throws IllegalStateException; // получить задачу, ошибка - если не был вызван connect(), null - если очередь пуста

    public void disconnect(); // Отключиться от очереди
}
