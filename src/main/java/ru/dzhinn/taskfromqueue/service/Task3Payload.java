package ru.dzhinn.taskfromqueue.service;

public interface Task3Payload {
    void init(int payloadId);

    String nextLine();

    void finish();
}
