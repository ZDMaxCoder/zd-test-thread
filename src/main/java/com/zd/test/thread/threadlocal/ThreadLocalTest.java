package com.zd.test.thread.threadlocal;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * 验证：
 * ThreadLocal的使用仅仅是为了在线程执行的流程中传递变量，并不能解决线程安全的问题；
 *
 */
public class ThreadLocalTest {


    static class MyList {

        ArrayList<String> arrayList = new ArrayList<>();

        public void add(String value) {
            arrayList.add(value);
        }

        public int size() {
            return arrayList.size();
        }

    }

    public static void main(String[] args) throws Exception{

//        ArrayList<String> arrayList = new ArrayList<>();
//        System.out.println(arrayList);

        MyList myList = new MyList();

        System.out.println("myList: "+myList);

        ThreadLocal<MyList> threadLocal = new ThreadLocal<>();

        int corePoolSize = 2;
        int maximumPoolSize = 10;
        long keepAliveTime = 5;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(2);

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);

        int activeCoreWorkerCount = poolExecutor.prestartAllCoreThreads();
        while (activeCoreWorkerCount!=corePoolSize){
            // 等待核心线程全部启动
        }

        for (int i = 0; i < 2; i++) {

            poolExecutor.execute(()->{

                String threadName = Thread.currentThread().getName();
                System.out.println("线程名称："+threadName);

                threadLocal.set(myList);
                MyList arrayList_copy = threadLocal.get();

                System.out.println(arrayList_copy);
                try{
                    Thread.sleep(1000); //避免执行过快，等第二个线程介入任务
                }catch (Exception e) {
                    e.printStackTrace();
                }

                for (int j = 0; j < 1000; j++) {
                    myList.add(j+"");
                }

            });

        }

        poolExecutor.shutdown();
        boolean shutDownRet = poolExecutor.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("shutDownRet: "+shutDownRet);
        System.out.println("arrayList size: "+myList.size());

    }









}
