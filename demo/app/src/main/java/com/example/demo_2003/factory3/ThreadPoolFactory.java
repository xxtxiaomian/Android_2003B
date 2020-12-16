package com.example.demo_2003.factory3;

import com.example.demo_2003.Constant;

/**
 * 创建线程池对象的工厂类
 */
public class ThreadPoolFactory {


    public static ThreadPoolInterface getExecutor(int type) {
        switch (type) {
            case Constant.CACHED_THREADPOOL:        //频繁的网络请求，异步操作
                return new CachedThreadPool();
            case Constant.FIXED_THREADPOOL:         //与自定义线程池，执行耗时操作
                return new FixedThreadPool();
            case Constant.SCHEDULED_THREADPOOL:     //一个线程，请求按顺序执行
                return new ScheduledThreadPool();
            case Constant.SINGLE_THREADPOOL:
                return new SingleThreadPool();
        }
        return null;
    }
}
