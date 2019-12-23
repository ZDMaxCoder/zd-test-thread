package com.zd.test.thread.phaser;

import java.util.concurrent.Phaser;

public class PhaserThread_3 implements Runnable {

    private Phaser phaser;

    PhaserThread_3(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {

        try{
            String threadName = Thread.currentThread().getName();

            // 第一阶段
            System.out.println("1 begin " + threadName + " " +System.currentTimeMillis());
            if ("线程B".equals(threadName)) {
                Thread.sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("1 end " + threadName + " phase = " + phaser.getPhase() + " " +System.currentTimeMillis());

            // 第二阶段
            System.out.println("2 begin " + threadName + " " +System.currentTimeMillis());
            if ("线程B".equals(threadName)) {
                Thread.sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("2 end " + threadName + " phase = " + phaser.getPhase() + " " +System.currentTimeMillis());

            // 第三阶段
            System.out.println("3 begin " + threadName + " " +System.currentTimeMillis());
            if ("线程B".equals(threadName)) {
                Thread.sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("3 end " + threadName + " phase = " + phaser.getPhase() + " " +System.currentTimeMillis());

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
