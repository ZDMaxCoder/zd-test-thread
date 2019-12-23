package com.zd.test.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 类Semaphore主要的作用就是控制线程并发的数量。这种功能可以应用在pool池技术中，可以设置同时访问pool池中数据的线程数量。
 */
public class SemaphoreTest implements Runnable{

    private Semaphore semaphore = new Semaphore(1); //创建持有一个permit的Semaphore,默认的是非公平的
//    private Semaphore semaphore = new Semaphore(1,true); //创建持有一个permit的Semaphore,公平的

    public void run() {
        try {
            semaphore.acquire();
//            semaphore.acquireUninterruptibly(); //在等待permit的情况下不允许中断，配合Thread.interupt()方法可以进行测试
//            semaphore.availablePermits();
//            semaphore.drainPermits(); //获取并返回所有立即可用的permit,并且将许可置为0
//            semaphore.getQueueLength(); //获取等待许可的线程个数
//            semaphore.hasQueuedThreads(); //判断是否有线程在等待permit
//            semaphore.tryAcquire(); //尝试获取1个permit，如果获取不到返回false，具有非阻塞的特点，通常与if语句结合使用
            System.out.println(Thread.currentThread().getName() + " begin timer=" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " end timer=" + System.currentTimeMillis());
            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
