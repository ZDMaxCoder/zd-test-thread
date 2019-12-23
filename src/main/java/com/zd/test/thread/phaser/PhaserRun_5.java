package com.zd.test.thread.phaser;

import java.util.concurrent.Phaser;


public class PhaserRun_5 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);

        Thread threadA = new Thread(new PhaserThread_5_1(phaser));
        threadA.setName("A");
        threadA.start();

        Thread threadB = new Thread(new PhaserThread_5_1(phaser));
        threadB.setName("B");
        threadB.start();

        Thread threadC = new Thread(new PhaserThread_5_2(phaser));
        threadC.setName("C");
        threadC.start();


    }
}
