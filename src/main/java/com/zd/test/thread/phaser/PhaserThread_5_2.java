package com.zd.test.thread.phaser;

import java.util.concurrent.Phaser;

public class PhaserThread_5_2 implements Runnable {

    private Phaser phaser;
    PhaserThread_5_2(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + " begin A1 " + System.currentTimeMillis());
        phaser.arrive();
        System.out.println(threadName + " end A1 " + System.currentTimeMillis());

        System.out.println(threadName + " begin A2 " + System.currentTimeMillis());
        phaser.arrive();
        System.out.println(threadName + " end A2 " + System.currentTimeMillis());

        System.out.println(threadName + " begin A3 " + System.currentTimeMillis());
        phaser.arrive();
        System.out.println(threadName + " end A3 " + System.currentTimeMillis());

    }
}
