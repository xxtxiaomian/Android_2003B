package com.example.demo_2003.factory4;

import java.util.concurrent.TimeUnit;

public abstract class AbThreadPool {
    public void executorTask(Runnable runnable) {
    }

    public void executorTimerTask(Runnable runnable, long firstStart, long interval, TimeUnit unit) {
    }

    public void removeTask(Runnable runnable) {
    }

    public void removeTask() {
    }
}
