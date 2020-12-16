package com.example.demo_2003.factory3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool implements ThreadPoolInterface {

    private ExecutorService executorService;

    public FixedThreadPool() {
        executorService = Executors.newFixedThreadPool(3);
    }

    @Override
    public void executeTask(Runnable runnable) {
        if (executorService == null) {
            return;
        }
        executorService.execute(runnable);
    }

    @Override
    public void removeTask() {
        if (executorService == null) {
            return;
        }
        executorService.shutdown();
    }
}
