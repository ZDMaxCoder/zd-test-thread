package com.zd.test.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierThreadTest_2 implements Runnable{

    private CyclicBarrier cyclicBarrier;

    CyclicBarrierThreadTest_2(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    public void run() {

        try{

            Thread.sleep((int)Math.random()*1000);
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " 等待凑齐两个一起运行！" + System.currentTimeMillis());
            cyclicBarrier.await();
            System.out.println(threadName + " 已经凑齐两个继续运行！" + System.currentTimeMillis());

        }catch (InterruptedException e) {
            e.printStackTrace();
        }catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
