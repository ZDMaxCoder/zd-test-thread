package com.zd.test.thread.phaser;

import java.util.concurrent.Phaser;

public class PhaserThread_2 implements Runnable {

    private Phaser phaser;
    PhaserThread_2(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " begin");
        phaser.arriveAndAwaitAdvance();
        System.out.println(threadName + " end And phase value = "+ phaser.getPhase());

        System.out.println(threadName + " begin");
        phaser.arriveAndAwaitAdvance();
        System.out.println(threadName + " end And phase value = "+ phaser.getPhase());

        System.out.println(threadName + " begin");
        phaser.arriveAndAwaitAdvance();
        System.out.println(threadName + " end And phase value = "+ phaser.getPhase());

        System.out.println(threadName + " begin");
        phaser.arriveAndAwaitAdvance();
        System.out.println(threadName + " end And phase value = "+ phaser.getPhase());



    }
}
