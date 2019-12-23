package com.zd.test.thread.executor.threadpoolexecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 参数之间的关系详解，经测验，
 当                                    0 < 并发量 <= corePoolSize                           提交的任务会直接被线程池处理掉，任务不会进入队列；
 当                         corePoolSize < 并发量 <= corePoolSize + workQueue.capacity      线程池仅仅使用核心池中的线程进行处理，多余的缓冲到队列；
 当    corePoolSize + workQueue.capacity < 并发量 <= maximumPoolSize + workQueue.capacity   线程池不仅仅使用核心池中的线程进行处理，同时启动(maximumPoolSize-corePoolSize-workQueue.capacity)个非核心池线程进行处理(非核心池的空闲线程在过了存活时间后必死)，多余的缓冲到队列；
 当 maximumPoolSize + workQueue.capacity < 并发量
 */
public class TheadPoolExecutor_Constructor_Run_1 {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + " run! " + System.currentTimeMillis());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        int corePoolSize = 7;
        int maximumPoolSize = 20;
        int keepAliveTime = 5;
        TimeUnit unit = TimeUnit.SECONDS;
//        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>(2);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);

        // 线程池在建立后如果没有任务是不会启动核心线程的，随着任务并发的增多逐渐启动全部核心线程，可以调用以下方法来启动所有核心线程：
        System.out.println("A: " + threadPoolExecutor.getPoolSize());
        threadPoolExecutor.prestartAllCoreThreads();
        System.out.println("A: " + threadPoolExecutor.getPoolSize());

        for (int i = 0; i < 2; i++) {
            threadPoolExecutor.execute(runnable);
        }
        
        Thread.sleep(300);

        System.out.println("A: " + threadPoolExecutor.getCorePoolSize());
        System.out.println("A: " + threadPoolExecutor.getPoolSize());
        System.out.println("A: " + threadPoolExecutor.getQueue().size());

        Thread.sleep(10000); // 使空闲线程超过存活期被线程池终结

        System.out.println("A: " + threadPoolExecutor.getCorePoolSize());
        System.out.println("A: " + threadPoolExecutor.getPoolSize());
        System.out.println("A: " + threadPoolExecutor.getQueue().size());



    }
}
