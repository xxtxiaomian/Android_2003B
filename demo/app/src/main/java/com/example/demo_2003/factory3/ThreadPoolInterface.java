package com.example.demo_2003.factory3;

public interface ThreadPoolInterface {

    void executeTask(Runnable runnable);

    void removeTask();

}
