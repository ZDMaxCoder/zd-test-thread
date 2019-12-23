package com.zd.test.thread.executor.scheduledexecutorservice;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * scheduleAtFixedRate方法参数中的间隔时间 period 指的是同一个任务第一次开始时间和第二次开始时间的间隔
 *      当任务执行时间超过这个间隔时：任务两次开始时间的间隔为任务的执行时间
 *      当任务执行时间不超过这个间隔时：任务两次开始时间的间隔为period
 */
public class FixedRate_Test {

    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        System.out.println("main begin time : " + LocalDateTime.now());

//        scheduledExecutor.scheduleAtFixedRate(()->{
//            System.out.println("task begin time : " + LocalDateTime.now());
//            try{
//                // 在本例中，初始延迟时间为1s，周期为2s，线程执行时间为4s，模仿任务执行时间超过周期间隔时间的情况；
//                Thread.sleep(9000);
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.out.println("task   end time : " + LocalDateTime.now());
//
//        },1,5, TimeUnit.SECONDS);


        scheduledExecutor.scheduleAtFixedRate(()->{
            System.out.println("task begin time : " + LocalDateTime.now());
            try{
                // 在本例中，初始延迟时间为1s，周期为2s，线程执行时间为4s，模仿任务执行时间不超过周期间隔时间的情况；
                Thread.sleep(1000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("task   end time : " + LocalDateTime.now());

        },1,10, TimeUnit.SECONDS);


        System.out.println("main   end time : " + LocalDateTime.now());



    }

}
