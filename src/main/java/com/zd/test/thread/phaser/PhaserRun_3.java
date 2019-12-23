package com.zd.test.thread.phaser;

import java.util.concurrent.Phaser;

/**
 * 方法onAdvance()
 */
public class PhaserRun_3 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(2){

            // 重写Phaser中的onAdvance()方法
            protected boolean onAdvance(int phase, int registeredParties) {

                System.out.println(Thread.currentThread().getName() + " 调用了onAdvance");
                return true;
                // 返回true取消屏障，Phaser呈无效/销毁的状态，从结果可以看出线程A只等待了一次5秒，而在其他的屏障处不再发生阻塞；
                // 返回false则Phaser继续工作；
            }

        };

        Thread threadA = new Thread(new PhaserThread_3(phaser));
        threadA.setName("线程A");

        Thread threadB = new Thread(new PhaserThread_3(phaser));
        threadB.setName("线程B");

        threadA.start();
        threadB.start();



    }

}

