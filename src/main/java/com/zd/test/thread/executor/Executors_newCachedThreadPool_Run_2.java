package com.zd.test.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用newCachedThreadPool(ThreadFactory)接口，
 * 程序员可以将自定义的线程交给无界线程池来管理
 */
public class Executors_newCachedThreadPool_Run_2 {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    public static void main(String[] args) {
//        Executors_newCachedThreadPool_Thread_2 myThreadFactory = new Executors_newCachedThreadPool_Thread_2();
//        ExecutorService executorService = Executors.newCachedThreadPool(myThreadFactory);
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        });
        System.out.println(CAPACITY);
    }

}
