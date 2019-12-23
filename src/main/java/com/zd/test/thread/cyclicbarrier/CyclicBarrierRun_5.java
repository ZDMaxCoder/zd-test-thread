package com.zd.test.thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 方法：await(long timeout,Time unit)的功能是如果在指定的时间内到达paties的数量，则程序续走，否则如果出现超时，则抛出TimeoutException异常，离开障碍点，
 * 此时等待在障碍处的其他线程会抛出BrokenBarrierException异常，并且离开障碍点
 */
public class CyclicBarrierRun_5 {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            public void run() {
                System.out.println("组团成功");
            }
        });

        Thread[] threads =  new Thread[2];
        for(int i=0; i<2; i++){
            threads[i] = new Thread(new CyclicBarrierThreadTest_5(cyclicBarrier));
        }
        for(int i=0; i<2; i++){
            threads[i].start();
        }

    }


}
