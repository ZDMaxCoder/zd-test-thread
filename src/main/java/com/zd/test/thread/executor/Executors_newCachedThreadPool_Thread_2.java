package com.zd.test.thread.executor;

import java.util.concurrent.ThreadFactory;

public class Executors_newCachedThreadPool_Thread_2 implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("定制池中的线程对象的名称" + Math.random());
        return thread;
    }
}
