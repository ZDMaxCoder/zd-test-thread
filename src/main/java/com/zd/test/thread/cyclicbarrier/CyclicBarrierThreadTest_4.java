package com.zd.test.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierThreadTest_4 implements Runnable {

    private CyclicBarrier cyclicBarrier;

    CyclicBarrierThreadTest_4(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }


    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            if ("Thread-2".equals(threadName)) {
                System.out.println("Thread-2进来了");
                Thread.sleep(3000);

                // 从运行结果来看：线程在此处抛出 java.lang.ArithmeticException: / by zero 异常，其他线程继续阻塞等待。
                // int i = 1 / 0;

                // 首先要了解interrupt操作，该操作不会中断一个正在运行的线程，只是将该线程的中断状态位设置为true，在线程受到阻塞的地方
                // 抛出一个InterruptedException异常，并且中断状态也会被清除即重置为false，这样线程就得以退出阻塞状态。
                // 需要注意的是interrupt操作对锁阻塞和IO阻塞无效即不会抛出InterruptedException异常，仍然处于阻塞状态。

                // 通过interrupt操作将Thread-2线程的中断状态标志位设置为true，当Thread-2到达障碍处时抛出InterruptedException异常，
                // 其他在障碍处等待的线程抛出BrokenBarrierException异常，并且所有线程都离开障碍点（不会执行障碍处的action）进入各自的异常处理分支续走。
                Thread.currentThread().interrupt();

            }

            System.out.println(threadName + " 到达屏障 ");
            cyclicBarrier.await();
            System.out.println(threadName + " 通过屏障 ");

        } catch (InterruptedException e) {
            System.out.println(threadName + " 进入了InterruptedException " + cyclicBarrier.isBroken());
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            System.out.println(threadName + " 进入了BrokenBarrierException " + cyclicBarrier.isBroken());
            e.printStackTrace();
        }


    }
}
