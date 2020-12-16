package com.example.demo_2003.factory4;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool extends AbThreadPool {
    private static ScheduledThreadPool mThreadPool;
    private ScheduledExecutorService mExecutor;

    private ScheduledThreadPool() {
        mExecutor = Executors.newSingleThreadScheduledExecutor();
    }

    public synchronized static ScheduledThreadPool getThreadPool() {
        if (mThreadPool == null) {
            synchronized (ScheduledThreadPool.class) {
                if (mThreadPool == null) {
                    mThreadPool = new ScheduledThreadPool();
                }
            }
        }
        return mThreadPool;
    }

    @Override
    public void executorTimerTask(Runnable runnable, long firstStart, long interval, TimeUnit unit) {
        if (mExecutor == null)
            return;
        mExecutor.scheduleWithFixedDelay(runnable, firstStart, interval, unit);
    }

    @Override
    public void removeTask() {
        if (mExecutor == null)
            return;
        mExecutor.shutdown();
    }
}
