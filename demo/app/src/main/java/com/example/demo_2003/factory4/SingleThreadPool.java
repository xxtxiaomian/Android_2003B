package com.example.demo_2003.factory4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool extends AbThreadPool {
    private static SingleThreadPool mThreadPool;
    private ExecutorService mExecutor;

    private SingleThreadPool() {
        mExecutor = Executors.newSingleThreadExecutor();
    }

    public synchronized static SingleThreadPool getThreadPool() {
        if (mThreadPool == null) {
            synchronized (SingleThreadPool.class) {
                if (mThreadPool == null) {
                    mThreadPool = new SingleThreadPool();
                }
            }
        }
        return mThreadPool;
    }

    @Override
    public void executorTask(Runnable runnable) {
        if (mExecutor == null)
            return;
        mExecutor.execute(runnable);
    }

    @Override
    public void removeTask() {
        if (mExecutor == null)
            return;
        mExecutor.shutdown();
    }
}
