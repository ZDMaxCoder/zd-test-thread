package com.zd.test.thread.phaser;

import java.util.concurrent.Phaser;

public class PhaserRun_4 {

    public static void main(String[] args) {

        // 设置障碍处parties=2
        Phaser phaser = new Phaser(2){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("到达了未通过！phase="+phase+" registeredParties=" + registeredParties + " getArrivedParties=" + this.getArrivedParties());
                return super.onAdvance(phase, registeredParties);
            }
        };

        System.out.println("A1 getPhase=" + phaser.getPhase() + " getRegisterdParties=" + phaser.getRegisteredParties() + " getArrivedParties=" + phaser.getArrivedParties());
//        A1 getPhase=0 getRegisterdParties=2 getArrivedParties=0   线程通过的屏障数为0，Phaser要求parties=2时才能通过障碍，还没有人到达屏障处
        phaser.arrive();
        System.out.println("A1 getPhase=" + phaser.getPhase() + " getRegisterdParties=" + phaser.getRegisteredParties() + " getArrivedParties=" + phaser.getArrivedParties());
//        A1 getPhase=0 getRegisterdParties=2 getArrivedParties=1   线程通过的屏障数为0，Phaser要求parties=2时才能通过障碍，arrive()使parties值加1并且不在障碍处等待

        System.out.println("A2 getPhase=" + phaser.getPhase() + " getRegisterdParties=" + phaser.getRegisteredParties() + " getArrivedParties=" + phaser.getArrivedParties());
//        A2 getPhase=0 getRegisterdParties=2 getArrivedParties=1   线程通过的障碍数为0，Phaser要求parties=2时才能通过障碍，arrive()使parties值加1并且不在障碍处等待
        phaser.arrive();
//        到达了未通过！phase=0 registeredParties=2 getArrivedParties=2   // 2次调用arrive()方法，此时parties=2，Phaser认为此时可以通过障碍
        System.out.println("A2 getPhase=" + phaser.getPhase() + " getRegisterdParties=" + phaser.getRegisteredParties() + " getArrivedParties=" + phaser.getArrivedParties());
//        A2 getPhase=1 getRegisterdParties=2 getArrivedParties=0   // 线程通过的障碍数为1，此时到达的parties重置为0

        System.out.println("B1 getPhase=" + phaser.getPhase() + " getRegisterdParties=" + phaser.getRegisteredParties() + " getArrivedParties=" + phaser.getArrivedParties());
//        B1 getPhase=1 getRegisterdParties=2 getArrivedParties=0
        phaser.arrive();
        System.out.println("B1 getPhase=" + phaser.getPhase() + " getRegisterdParties=" + phaser.getRegisteredParties() + " getArrivedParties=" + phaser.getArrivedParties());
//        B1 getPhase=1 getRegisterdParties=2 getArrivedParties=1

        System.out.println("B2 getPhase=" + phaser.getPhase() + " getRegisterdParties=" + phaser.getRegisteredParties() + " getArrivedParties=" + phaser.getArrivedParties());
//        B2 getPhase=1 getRegisterdParties=2 getArrivedParties=1
        phaser.arrive();
//        到达了未通过！phase=1 registeredParties=2 getArrivedParties=2
        System.out.println("B2 getPhase=" + phaser.getPhase() + " getRegisterdParties=" + phaser.getRegisteredParties() + " getArrivedParties=" + phaser.getArrivedParties());
//        B2 getPhase=2 getRegisterdParties=2 getArrivedParties=0

        System.out.println("C1 getPhase=" + phaser.getPhase() + " getRegisterdParties=" + phaser.getRegisteredParties() + " getArrivedParties=" + phaser.getArrivedParties());
//        C1 getPhase=2 getRegisterdParties=2 getArrivedParties=0

        phaser.arrive();
        System.out.println("C1 getPhase=" + phaser.getPhase() + " getRegisterdParties=" + phaser.getRegisteredParties() + " getArrivedParties=" + phaser.getArrivedParties());
//        C1 getPhase=2 getRegisterdParties=2 getArrivedParties=1

        System.out.println("C2 getPhase=" + phaser.getPhase() + " getRegisterdParties=" + phaser.getRegisteredParties() + " getArrivedParties=" + phaser.getArrivedParties());
//        C2 getPhase=2 getRegisterdParties=2 getArrivedParties=1

        phaser.arrive();
//        到达了未通过！phase=2 registeredParties=2 getArrivedParties=2

        System.out.println("C2 getPhase=" + phaser.getPhase() + " getRegisterdParties=" + phaser.getRegisteredParties() + " getArrivedParties=" + phaser.getArrivedParties());
//        C2 getPhase=3 getRegisterdParties=2 getArrivedParties=0

    }

}
