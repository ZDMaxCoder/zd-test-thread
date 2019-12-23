package com.zd.test.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用Executors的newFixedThreadPool(int)创建的是有界线程池，也就是池中的线程个数可以指定最大数量
 * 使用Executors的newFixedThreadPool(int,ThreadFactory)可以定义池中的线程对象
 */
public class Executors_newFixedThreadPool_run_1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Executors_newFixedThreadPool_Thread_1("" + (i+1)));
        }
    }


}
