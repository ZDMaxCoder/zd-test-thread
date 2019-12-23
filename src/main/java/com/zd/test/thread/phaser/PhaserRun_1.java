package com.zd.test.thread.phaser;

import java.util.concurrent.Phaser;

public class PhaserRun_1 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);

        Thread threadA = new Thread(new PhaserThread_1_1(phaser));
        threadA.setName("运动员A");

        Thread threadB = new Thread(new PhaserThread_1_1(phaser));
        threadB.setName("运动员B");

        Thread threadC = new Thread(new PhaserThread_1_2(phaser));
        threadC.setName("运动员C");

        threadA.start();
        threadB.start();
        threadC.start();

    }


}

