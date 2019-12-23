package com.zd.test.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 方法：await(long timeout,Time unit)的功能是如果在指定的时间内到达paties的数量，则程序续走，否则如果出现超时，则抛出TimeoutException异常，离开障碍点，
 * 此时等待在障碍处的其他线程会抛出BrokenBarrierException异常，并且离开障碍点
 */
public class CyclicBarrierThreadTest_5 implements Runnable {

    private CyclicBarrier cyclicBarrier;

    CyclicBarrierThreadTest_5(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public void run() {

        String threadName = Thread.currentThread().getName();

        try {
            if ("Thread-0".equals(threadName)) {
                System.out.println(threadName + "进入了await(long timeout,Time unit)");
                cyclicBarrier.await(3, TimeUnit.SECONDS);
            }
            if ("Thread-1".equals(threadName)) {
                System.out.println(threadName + "进入了await()");
                cyclicBarrier.await();
            }

        } catch (InterruptedException e) {
            System.out.println(threadName + " 进入了InterruptedException");
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            System.out.println(threadName + " 进入了BrokenBarrierException");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println(threadName + " 进入了TimeoutException");
            e.printStackTrace();
        }
    }
}
