package com.zd.test.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * getNumberWaiting()方法：获得已经有几个线程到达屏障点。
 * 使用该方法验CyclicBarrier的屏障重置性。
 *
 * 从运行结果可以看出，parties的值是可以重置为0的
 *
 *
 */
public class CyclicBarrierRun_3 {

    public static void main(String[] args) throws Exception {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        Thread thread1 = new Thread(new CyclicBarrierThreadTest_3(cyclicBarrier));
        thread1.start();
        thread1.sleep(500);
        System.out.println(cyclicBarrier.getNumberWaiting());

        Thread thread2 = new Thread(new CyclicBarrierThreadTest_3(cyclicBarrier));
        thread2.start();
        thread2.sleep(500);
        System.out.println(cyclicBarrier.getNumberWaiting());

        Thread thread3 = new Thread(new CyclicBarrierThreadTest_3(cyclicBarrier));
        thread3.start();
        thread3.sleep(500);
        System.out.println(cyclicBarrier.getNumberWaiting());

        Thread thread4 = new Thread(new CyclicBarrierThreadTest_3(cyclicBarrier));
        thread4.start();
        thread4.sleep(500);
        System.out.println(cyclicBarrier.getNumberWaiting());
    }
}

