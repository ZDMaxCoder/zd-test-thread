package com.zd.test.thread.phaser;

import java.util.concurrent.Phaser;

/**
 * 方法getParties()获取的是已经到达第几个屏障；
 */
public class PhaserRun_2 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        new Thread(new PhaserThread_2(phaser)).start();

    }
}
//        运行结果
//        Thread-0 begin
//        Thread-0 end And phase value = 1
//        Thread-0 begin
//        Thread-0 end And phase value = 2
//        Thread-0 begin
//        Thread-0 end And phase value = 3
//        Thread-0 begin
//        Thread-0 end And phase value = 4