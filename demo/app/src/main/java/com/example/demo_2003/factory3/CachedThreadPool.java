package com.example.demo_2003.factory3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool implements ThreadPoolInterface {

    private ExecutorService executorService;

    public CachedThreadPool() {
        executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void executeTask(Runnable runnable) {
        if (executorService == null)
            return;
        executorService.execute(runnable);
    }

    @Override
    public void removeTask() {
        if (executorService == null)
            return;
        executorService.shutdown();
    }
}
