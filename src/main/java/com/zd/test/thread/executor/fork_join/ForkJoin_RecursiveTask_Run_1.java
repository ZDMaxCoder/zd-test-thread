package com.zd.test.thread.executor.fork_join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoin_RecursiveTask_Run_1 {

    public static void main(String[] args) {
        try {

            FrokJoin_RecursiveTask_1 task1 = new FrokJoin_RecursiveTask_1();
            System.out.println(task1.hashCode());
            ForkJoinPool pool = new ForkJoinPool();
            ForkJoinTask<Integer> task2 = pool.submit(task1);

            // get() 和 join() 在处理异常上有区别，get()会抛出异常到main()线程，join不会；
            System.out.println(task2.hashCode() + " get " + task2.get());
            System.out.println(task2.hashCode() + " join " + task2.join());

            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
