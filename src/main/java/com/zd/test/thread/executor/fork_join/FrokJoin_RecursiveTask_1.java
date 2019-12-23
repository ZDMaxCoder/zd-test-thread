package com.zd.test.thread.executor.fork_join;

import java.util.concurrent.RecursiveTask;

public class FrokJoin_RecursiveTask_1 extends RecursiveTask<Integer> {

    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread().getName() + " compute time=" + System.currentTimeMillis());
        return 100;
    }
}
