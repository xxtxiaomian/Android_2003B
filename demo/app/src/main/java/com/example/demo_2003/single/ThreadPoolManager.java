package com.example.demo_2003.single;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {

//    /**
//     * 饿汉式 单例
//     */
//    private static ThreadPoolManager mManager = new ThreadPoolManager();
//
//    private ThreadPoolManager() {
//    }
//
//    public static ThreadPoolManager getmManager(){
//        return mManager;
//    }


    /**
     * 懒汉式 单例
     */
    private static ThreadPoolManager mManager;
    //线程池
    private ThreadPoolExecutor mExecutor;

    private ThreadPoolManager() {            //核心线程数      //最大线程数        //线程空闲存活时间
        //自定义线程池
        mExecutor = new ThreadPoolExecutor(5, 20, 30,
                //单位秒
                TimeUnit.SECONDS,
                //
                new LinkedBlockingQueue<Runnable>(),
                //
                Executors.defaultThreadFactory());
    }

    public synchronized static ThreadPoolManager getmManager() {
        if (mManager == null) {
            synchronized (ThreadPoolManager.class) {
                if (mManager == null) {
                    mManager = new ThreadPoolManager();
                }
            }
        }
        return mManager;
    }

    //线程池执行任务
    public void executeTask(Runnable runnable) {
        if (mExecutor == null) {
            return;
        }
        mExecutor.execute(runnable);
    }

    //线程池移除任务
    public void removeTask(Runnable runnable) {
        if (mExecutor == null) {
            return;
        }
        mExecutor.remove(runnable);
    }


}
