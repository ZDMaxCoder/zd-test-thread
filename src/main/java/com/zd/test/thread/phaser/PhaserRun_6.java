package com.zd.test.thread.phaser;

import java.util.concurrent.Phaser;

/**
 * 此实验说明Phaser类的运行时机是可以通过逻辑控制的，主要的原理就是计数+1，然后通过逻辑代码的方式来决定线程是否继续向下执行。
 */
public class PhaserRun_6 {

    public static void main(String[] args) throws Exception {

        Phaser phaser = new Phaser(3);
        phaser.register(); //在主线程中将 parties + 1
        for (int i=0; i<3; i++ ) {
            new Thread(new PhaserThread_6(phaser)).start();
        }
        Thread.sleep(5000);
        phaser.arriveAndAwaitAdvance(); //主线程中将上面添加的parties减去

    }

}
