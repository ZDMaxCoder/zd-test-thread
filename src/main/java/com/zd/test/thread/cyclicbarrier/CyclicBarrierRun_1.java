package com.zd.test.thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierRun_1 {

    public static void main(String[] args) {

        /**
         * 设置parties(同伴)=5，也就是5个线程都执行了cyclicBarrier对象的await()方法后程序才可以继续向下执行，
         * 否则这些线程彼此互相等待，一直呈阻塞状态。
         * 注意：cyclicBarrier中的action行为由最后一个到达cyclicBarrier的线程负责执行。在执行完cyclicBarrier的action之后才会放行。
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            public void run() {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " 全部到齐了");
            }
        });

        Thread[] threads = new Thread[5];
        for (int i = 0; i<threads.length; i++) {
            threads[i] = new Thread(new CyclicBarrierThreadTest_1(cyclicBarrier));
        }

        for (int i = 0; i<threads.length; i++) {
            threads[i].start();
        }

    }
}

//    运行结果
//    Thread-1 到了！1515662988346
//    Thread-4 到了！1515662988346
//    Thread-2 到了！1515662988346
//    Thread-3 到了！1515662988346
//    Thread-0 到了！1515662988346
//    Thread-4 全部到齐了
//    Thread-4 通过！
//    Thread-1 通过！
//    Thread-2 通过！
//    Thread-0 通过！
//    Thread-3 通过！
