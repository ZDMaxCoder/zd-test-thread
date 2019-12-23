package com.zd.test.thread.executor.scheduledexecutorservice;


import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 测试使用Runnable
 */
public class Delay_Runnable_Test {

    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        System.out.println("main begin = " + LocalDateTime.now());

        scheduledExecutor.schedule(() ->{

            String threadName = Thread.currentThread().getName();
            System.out.println("runnableA begin " + threadName + " " + LocalDateTime.now());
            try {Thread.sleep(3000);}catch (Exception e) {e.printStackTrace();}
            System.out.println("runnableA   end " + threadName + " " + LocalDateTime.now());

        },0L , TimeUnit.SECONDS);

        scheduledExecutor.schedule(()->{

            String threadName = Thread.currentThread().getName();
            System.out.println("runnableB begin " + threadName + " " + LocalDateTime.now());
            System.out.println("runnableB   end " + threadName + " " + LocalDateTime.now());


        },0L, TimeUnit.SECONDS);

        System.out.println("main end = " + LocalDateTime.now());

    }

}

