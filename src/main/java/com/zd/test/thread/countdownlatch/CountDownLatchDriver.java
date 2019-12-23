package com.zd.test.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 * countdown:翻译成中文是倒计时的意思。latch:翻译成中文是门闩，门锁的意思。顾名思义，CountDownLatch翻译过来就是倒计时锁的意思。
 * 初始化CountDownLatch时给定一个count计数，
 *  当count计数为0时锁打开；
 *  当count计数不为0时当前线程在锁处等待，处于wait状态；
 *
 * 调用CountDownLatch的await()方法判断count计数是否为0，如果不为0则呈等待状态。
 * 其他线程可以调用CountDownLatch()的countDown()方法将count计数减1，当count计数减到0时，处于等待状态的线程继续运行。
 * 调用getCount()方法可以获得当前的count计数值。
 *
 * 要说明的是CountDownLatch中计数无法被重置，如果需要重置计数，考虑使用CyclicBarrier。
 *
 * 使用CountDownLatch可以实现一个或多个线程处于等待状态直到在其他的线程中完成一系列操作后才开始运行。
 *
 *  <pre> {@code
 * class Driver { // ...
 *   void main() throws InterruptedException {
 *     CountDownLatch startSignal = new CountDownLatch(1);
 *     CountDownLatch doneSignal = new CountDownLatch(N);
 *
 *     for (int i = 0; i < N; ++i) // create and start threads
 *       new Thread(new Worker(startSignal, doneSignal)).start();
 *
 *     doSomethingElse();            // don't let run yet
 *     startSignal.countDown();      // let all threads proceed
 *     doSomethingElse();
 *     doneSignal.await();           // wait for all to finish
 *   }
 * }
 *
 * class Worker implements Runnable {
 *   private final CountDownLatch startSignal;
 *   private final CountDownLatch doneSignal;
 *   Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
 *     this.startSignal = startSignal;
 *     this.doneSignal = doneSignal;
 *   }
 *   public void run() {
 *     try {
 *       startSignal.await();
 *       doWork();
 *       doneSignal.countDown();
 *     } catch (InterruptedException ex) {} // return;
 *   }
 *
 *   void doWork() { ... }
 * }}</pre>
 *
 *
 * <p>Another typical usage would be to divide a problem into N parts,
 * describe each part with a Runnable that executes that portion and
 * counts down on the latch, and queue all the Runnables to an
 * Executor.  When all sub-parts are complete, the coordinating thread
 * will be able to pass through await. (When threads must repeatedly
 * count down in this way, instead use a {@link 'CyclicBarrier'}.)
 *
 *  <pre> {@code
 * class Driver2 { // ...
 *   void main() throws InterruptedException {
 *     CountDownLatch doneSignal = new CountDownLatch(N);
 *     Executor e = ...
 *
 *     for (int i = 0; i < N; ++i) // create and start threads
 *       e.execute(new WorkerRunnable(doneSignal, i));
 *
 *     doneSignal.await();           // wait for all to finish
 *   }
 * }
 *
 * class WorkerRunnable implements Runnable {
 *   private final CountDownLatch doneSignal;
 *   private final int i;
 *   WorkerRunnable(CountDownLatch doneSignal, int i) {
 *     this.doneSignal = doneSignal;
 *     this.i = i;
 *   }
 *   public void run() {
 *     try {
 *       doWork(i);
 *       doneSignal.countDown();
 *     } catch (InterruptedException ex) {} // return;
 *   }
 *
 *   void doWork() { ... }
 * }}</pre>
 *
 *
 */
public class CountDownLatchDriver {

    public static void main(String[] args) {

        int workerNum = 10;

        CountDownLatch comingSignal = new CountDownLatch(workerNum);// 等待运动员到达起跑点
        CountDownLatch waitSignal = new CountDownLatch(1);// 等待裁判说准备
        CountDownLatch waitRunSignal = new CountDownLatch(workerNum);// 等待起跑
        CountDownLatch startSignal = new CountDownLatch(1);// 起跑
        CountDownLatch endSignal = new CountDownLatch(workerNum);// 所有运动员到达终点

        for (int i= 0; i<10; i++) {
            new Thread(new CountDownLatchWorker(comingSignal,waitSignal,waitRunSignal,startSignal,endSignal)).start();
        }

        try{
            System.out.println("裁判在等待运功员到起跑点");
            comingSignal.await();

            System.out.println("所有运动员已到达起跑点");

            System.out.println("裁判说准备起跑姿势");
            waitSignal.countDown();

            waitRunSignal.await();
            System.out.println("所有运动员起跑姿势准备就绪");

            System.out.println("发令枪响");
            startSignal.countDown();

            endSignal.await();
            System.out.println("所有运动员到达终点，比赛结束");

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
