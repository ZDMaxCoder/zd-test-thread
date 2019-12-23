package com.zd.test.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchWorker implements Runnable {

    private CountDownLatch comingSignal;// 等待运动员到达起跑点
    private CountDownLatch waitSignal;// 等待裁判说准备
    private CountDownLatch waitRunSignal;// 等待起跑
    private CountDownLatch startSignal;// 起跑
    private CountDownLatch endSignal;// 所有运动员到达终点

    CountDownLatchWorker(CountDownLatch comingSignal,
                         CountDownLatch waitSignal,
                         CountDownLatch waitRunSignal,
                         CountDownLatch startSignal,
                         CountDownLatch endSignal) {
        this.comingSignal = comingSignal;
        this.waitSignal = waitSignal;
        this.waitRunSignal = waitRunSignal;
        this.startSignal = startSignal;
        this.endSignal = endSignal;
    }


    public void run() {

        try{

            String threadName = Thread.currentThread().getName();

            Thread.sleep(3000);
            System.out.println(threadName + " 到达起跑点,等待裁判说准备");
            comingSignal.countDown();
            waitSignal.await();

            System.out.println(threadName + " 准备起跑姿势，等待发令枪响");
            waitRunSignal.countDown();

            startSignal.await();
            System.out.println(threadName + " 起跑，跑完全程用时不确定");

            Thread.sleep(5000);

            System.out.println(threadName + " 到达终点");
            endSignal.countDown();

        }catch (Exception e) {
            e.printStackTrace();
        }



    }
}
