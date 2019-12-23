package com.zd.test.thread.executor.scheduledexecutorservice;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 使用Callable延迟执行
 */
public class Delay_Callable_Test {

    public static class MyCallableA implements Callable<String> {
        @Override
        public String call() throws Exception {
            String threadName = Thread.currentThread().getName();
            System.out.println("CallA begin " + threadName + " " + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("CallA end " + threadName + " " + System.currentTimeMillis());
            return "returnA";
        }
    }

    public static class MyCallableB implements Callable<String> {
        @Override
        public String call() throws Exception {
            String threadName = Thread.currentThread().getName();
            System.out.println("CallB begin " + threadName + " " + System.currentTimeMillis());
            System.out.println("CallB end " + threadName + " " + System.currentTimeMillis());
            return "returnB";
        }
    }

    public static void main(String[] args) throws Exception {

        ArrayList<Callable> callableList = new ArrayList<>();
        callableList.add(new MyCallableA());
        callableList.add(new MyCallableB());

        // 获得一个单任务的计划任务执行池
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        /**
         * 注意：public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit);
         *
         * 当有多个调度任务时，第二个参数delay是同时消耗时间的，并不是一个任务执行完毕后再等待4秒继续执行的效果。
         * 在本例中，由于第一个任务从计划任务到运行结束需要用时7秒，第二个任务其实是想在第4秒被执行，由于
         * 本例是单任务的计划任务池，所以第2个任务的执行时间被延后3秒
         *
         */
        ScheduledFuture futureA = scheduledExecutor.schedule(new MyCallableA(), 4L, TimeUnit.SECONDS);
        ScheduledFuture futureB = scheduledExecutor.schedule(new MyCallableB(), 4L, TimeUnit.SECONDS);

        System.out.println("      X= "+System.currentTimeMillis());
        System.out.println("futureA= "+futureA.get());
        System.out.println("futureB= "+futureB.get());
        System.out.println("      Y= "+System.currentTimeMillis());

    }

}
