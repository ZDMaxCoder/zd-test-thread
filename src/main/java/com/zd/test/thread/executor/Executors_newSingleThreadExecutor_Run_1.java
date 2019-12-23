package com.zd.test.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用Executors的newSingleThreadExecutor()方法可以创建单一线程池，单一线程池可以实现以队列的方式来执行任务
 * 使用Executors的newSingleThreadExecutor(ThreadFactory)可以定制池中的线程
 */
public class Executors_newSingleThreadExecutor_Run_1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Executors_newSingleThreadExecutor_Thread_1(""+(i+1)));
        }

    }
}
