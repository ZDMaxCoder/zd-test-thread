package com.zd.test.thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierRun_2 {

    public static void main(String[] args) {

        /**
         * 在1中，线程数量刚好等于parties，此处展示：当线程数量刚好是parties数量的整数倍时，会分批执行，批次 = 线程总数/parties
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            public void run() {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " 全部到齐了");
            }
        });

        Thread[] threads = new Thread[4];
        for (int i = 0; i<threads.length; i++) {
            threads[i] = new Thread(new CyclicBarrierThreadTest_2(cyclicBarrier));
        }

        for (int i = 0; i<threads.length; i++) {
            threads[i].start();
        }
    }
}

//        Thread-1 等待凑齐两个一起运行！1515663566906
//        Thread-3 等待凑齐两个一起运行！1515663566906
//        Thread-3 全部到齐了
//        Thread-1 已经凑齐两个继续运行！1515663566906
//        Thread-3 已经凑齐两个继续运行！1515663566906
//        Thread-0 等待凑齐两个一起运行！1515663566906
//        Thread-2 等待凑齐两个一起运行！1515663566906
//        Thread-2 全部到齐了
//        Thread-0 已经凑齐两个继续运行！1515663566906
//        Thread-2 已经凑齐两个继续运行！1515663566906

