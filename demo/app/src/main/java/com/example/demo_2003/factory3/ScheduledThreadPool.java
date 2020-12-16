package com.example.demo_2003.factory3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool implements ThreadPoolInterface {

    private ScheduledExecutorService executorService;

    public ScheduledThreadPool() {
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void executeTask(Runnable runnable) {
        if (executorService == null)
            return;
        executorService.schedule(runnable, 1, TimeUnit.SECONDS);
    }

    @Override
    public void removeTask() {
        if (executorService == null)
            return;
        executorService.shutdown();
    }
}
