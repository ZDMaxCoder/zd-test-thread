package com.zd.test.thread.phaser;

import java.util.concurrent.Phaser;

public class PhaserThread_1_1 implements Runnable {

    private Phaser phaser;

    PhaserThread_1_1(Phaser phaser) {
        this.phaser = phaser;
    }

    public void run() {

            String threadName = Thread.currentThread().getName();

            System.out.println(threadName + "第一阶段比赛开始");
            phaser.arriveAndAwaitAdvance();
            System.out.println(threadName + "第一阶段比赛结束");

            System.out.println(threadName + "第二阶段比赛开始");
            phaser.arriveAndAwaitAdvance();
            System.out.println(threadName + "第二阶段比赛结束");


    }
}
