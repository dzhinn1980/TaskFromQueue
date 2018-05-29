package ru.dzhinn.taskfromqueue;

public interface Task {
    int getTaskId(); // Уникальный ID задачи

    int getTaskType(); // Тип задачи

    int getPayloadId(); // ID полезной нагрузки - для получения дополнительных данных по конкретной задачи. Вид дополнительных данных зависит от taskType.
}
