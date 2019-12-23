package com.zd.test.thread.phaser;

import java.util.concurrent.Phaser;

public class PhaserThread_1_2 implements Runnable {

    private Phaser phaser;

    PhaserThread_1_2(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        try {
            String threadName = Thread.currentThread().getName();

            System.out.println(threadName + "第一阶段比赛开始");
            Thread.currentThread().sleep(5000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(threadName + "第一阶段比赛结束");

// 注释掉运动员C的第二阶段的比赛，此时整个流程阻塞，运动员A和运动员B仍然在等待运动员C到达第二阶段的障碍处
//            System.out.println(threadName + "第二阶段比赛开始");
//            Thread.currentThread().sleep(5000);
//            phaser.arriveAndAwaitAdvance();
//            System.out.println(threadName + "第二阶段比赛结束");

            // 运动员C在比赛完第一阶段后注销第二阶段的比赛，此时parties减一，运动员A和运动员B到达障碍处后开始进行第二阶段的比赛
            phaser.arriveAndDeregister();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
