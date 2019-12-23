package com.zd.test.thread.phaser;

import java.util.concurrent.Phaser;

public class PhaserThread_5_1 implements Runnable {

    private Phaser phaser;

    PhaserThread_5_1(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();
        try {
            System.out.println(threadName + " begin A1 " + System.currentTimeMillis());
            Thread.currentThread().sleep(3000);
            System.out.println(phaser.getArrivedParties());
            phaser.arriveAndAwaitAdvance();
            System.out.println(threadName + " end A1 " + System.currentTimeMillis());

            System.out.println(threadName + " begin A2 " + System.currentTimeMillis());
            Thread.currentThread().sleep(3000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(threadName + " end A2 " + System.currentTimeMillis());

            System.out.println(threadName + " begin A3 " + System.currentTimeMillis());
            Thread.currentThread().sleep(3000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(threadName + " end A3 " + System.currentTimeMillis());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
