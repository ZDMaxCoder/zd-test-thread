package com.zd.test.thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 类CyclicBarrier对于线程的interrupt处理会使用全有或全无的破坏模型，
 * 意思是如果有一个线程由于中断（InterruptedException）或者超时（示例见5， await(long timeout,Time unit) -> TimeoutException）提前离开了障碍点，
 * 其他所有在障碍处等待的线程会抛出BrokenBarrierException异常，并且离开障碍点。
 *
 */
public class CyclicBarrierRun_4 {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            public void run() {
                System.out.println("组团成功");
            }
        });

        Thread[] threads =  new Thread[4];
        for(int i=0; i<4; i++){
            threads[i] = new Thread(new CyclicBarrierThreadTest_4(cyclicBarrier));
        }
        for(int i=0; i<4; i++){
            threads[i].start();
        }

    }
}
