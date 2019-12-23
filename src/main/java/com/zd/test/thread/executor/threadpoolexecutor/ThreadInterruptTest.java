package com.zd.test.thread.executor.threadpoolexecutor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.*;

public class ThreadInterruptTest {

    public static void main(String[] args) throws Exception {

        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {

                Thread thread = new Thread(r);
                thread.setUncaughtExceptionHandler((t, e) -> {
                    System.out.println(t.getName() + " e= " + e.getMessage());
                });
                return thread;

            }
        };

        int corePoolSize = 3;
        int maximumPoolSize = 10;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(3);

//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue);

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory);

        // 启动所有的核心线程
        // 在Worker的构造方法中对新创建的Worker上锁，防止新创建的Worker被打断，新创建的Worker在成功添加到HashSet中后会启动Worker中的thread，从而执行Worker中的
        // runWork()方法，在runWork()方法中：Worker解锁 -> getTask()阻塞,等待BlockingQueue中新增任务 -> Worker获取到任务后上锁，防止在执行任务过程中被shutdown()打断 -> 执行任务 -> finally释放锁
        poolExecutor.prestartAllCoreThreads();


        Thread.sleep(2000);// 待上面所有核心线程中的线程完全启动，释放启动时持有的锁

        // 遍历HashSet，统计持有锁的Worker；该方法返回的数值不一定是正在执行Task的Worker数，因为从Worker创建到Worker启动它持有的线程这段时间Worker是持有锁的，我们可以称之为启动锁;
        System.out.println("activeCount-1 = " + poolExecutor.getActiveCount());
        System.out.println("completedTaskCount-1 = " + poolExecutor.getCompletedTaskCount()); // 遍历HashSet，统计所有Worker中的completedTasks

        System.out.println(LocalDateTime.now());


        for (int k = 1; k<=3; k++) {
            poolExecutor.execute(()->{
                System.out.println(Thread.currentThread().getName());
                try {
                    // 阻塞在此处，Worker仍然持有锁；
                    // shutdown()只会interrupt()没有锁的Worker，所以调用shutdown()对此处无效;
                    // shutdownNow()会遍历所有的Worker进行Interrupt()，所以调用shutdownNow()此处会异常：java.lang.InterruptedException: sleep interrupted
                    Thread.sleep(500000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

//        for (int k = 1; k<=3; k++) {
//            poolExecutor.execute(()->{
//                while(true); // 不论是shutdown()还是shutdownNow()都是通过interrupt()来使线程遇到阻塞时抛出异常从而退出阻塞状态，但是如果线程是死循环且不会阻塞，那么线程会一直运行下去占用资源
//            });
//        }

        System.out.println("activeCount-2 = " + poolExecutor.getActiveCount());
        System.out.println("completedTaskCount-2 = " + poolExecutor.getCompletedTaskCount());

        poolExecutor.shutdown();  // 可以防止线程线程阻塞，不能防止死循环

        System.out.println("activeCount-3 = " + poolExecutor.getActiveCount());
        System.out.println("completedTaskCount-3 = " + poolExecutor.getCompletedTaskCount());

        boolean awaitTermination_1 = poolExecutor.awaitTermination(2, TimeUnit.SECONDS);

        if(!awaitTermination_1) {

            poolExecutor.shutdownNow(); // 可以防止线程线程阻塞，不能防止死循环

        }

        System.out.println("activeCount-3 = " + poolExecutor.getActiveCount());
        System.out.println("completedTaskCount-3 = " + poolExecutor.getCompletedTaskCount());

        boolean awaitTermination_2 = poolExecutor.awaitTermination(2, TimeUnit.SECONDS);

        System.out.println(awaitTermination_2);

        System.out.println(LocalDateTime.now());


    }

}
