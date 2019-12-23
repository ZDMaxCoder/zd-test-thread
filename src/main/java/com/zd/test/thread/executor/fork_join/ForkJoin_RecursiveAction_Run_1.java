package com.zd.test.thread.executor.fork_join;

import java.util.concurrent.ForkJoinPool;

public class ForkJoin_RecursiveAction_Run_1 {

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new ForkJoin_RecusiveAction_1(1,10));
        Thread.sleep(5000);
    }
}
