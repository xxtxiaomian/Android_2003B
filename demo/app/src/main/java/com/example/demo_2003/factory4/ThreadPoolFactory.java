package com.example.demo_2003.factory4;

import com.example.demo_2003.Constant;

public class ThreadPoolFactory {

    public static AbThreadPool getThreadPool(int type) {
        switch (type) {
            case Constant.CUSTOM_THREADPOOL:
                return CustomThreadPool.getThreadPool();
            case Constant.SINGLE_THREADPOOL:
                return SingleThreadPool.getThreadPool();
            case Constant.SCHEDULED_THREADPOOL:
                return ScheduledThreadPool.getThreadPool();
        }
        return null;
    }
}
