package com.zd.test.thread.executor.scheduledexecutorservice;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * scheduleWithFixedDelay并没有超时和不超时的情况，参数long delay 表示的是任务的下一次开始时间与上一次结束时间的时间间隔
 */
public class FixedRate_Delay_Test {

    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        System.out.println("main begin time : " + LocalDateTime.now());

        scheduledExecutor.scheduleWithFixedDelay(()->{
            System.out.println("task begin time : " + LocalDateTime.now());
            try{
                // 在本例中，初始延迟时间为1s，周期为2s，线程执行时间为4s，模仿任务执行时间超过周期间隔时间的情况；
                Thread.sleep(9000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("task   end time : " + LocalDateTime.now());

        },1,5, TimeUnit.SECONDS);

//        scheduledExecutor.scheduleWithFixedDelay(()->{
//            System.out.println("task begin time : " + LocalDateTime.now());
//            try{
//                // 在本例中，初始延迟时间为1s，周期为2s，线程执行时间为4s，模仿任务执行时间不超过周期间隔时间的情况；
//                Thread.sleep(1000);
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.out.println("task   end time : " + LocalDateTime.now());
//
//        },1,10, TimeUnit.SECONDS);


        System.out.println("main   end time : " + LocalDateTime.now());



    }

}
