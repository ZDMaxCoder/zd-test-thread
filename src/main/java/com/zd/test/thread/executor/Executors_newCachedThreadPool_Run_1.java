package com.zd.test.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用Executors的newCachedThreadPool()方法创建的是无界线程池,可以进行线程自动回收。
 * 所谓的“无界线程池”就是池中理论上存放线程个数最大为Intger.MAX_VAULE
 */
public class Executors_newCachedThreadPool_Run_1 {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Executors_newCachedThreadPool_Thread_1(""+(i+1)));
        }
        Thread.sleep(1000);// 验证线程池中的线程可以复用
        System.out.println("");
        System.out.println("");
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Executors_newCachedThreadPool_Thread_1(""+(i+1)));
        }


    }

}
