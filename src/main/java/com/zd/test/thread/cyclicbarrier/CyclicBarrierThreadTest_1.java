package com.zd.test.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierThreadTest_1 implements Runnable{

    private CyclicBarrier cyclicBarrier;

    CyclicBarrierThreadTest_1(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    public void run() {

        try{

            Thread.sleep((int)Math.random()*1000);
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " 到了！" + System.currentTimeMillis());
            cyclicBarrier.await();
            System.out.println(threadName + " 通过！" + System.currentTimeMillis());

        }catch (InterruptedException e) {
            e.printStackTrace();
        }catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
