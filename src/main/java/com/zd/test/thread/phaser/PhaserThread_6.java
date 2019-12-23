package com.zd.test.thread.phaser;

import java.util.concurrent.Phaser;

public class PhaserThread_6 implements Runnable {

    private Phaser phaser;
    PhaserThread_6(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " A1 begin=" + System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(threadName + " A1 end=" + System.currentTimeMillis());


    }
}
