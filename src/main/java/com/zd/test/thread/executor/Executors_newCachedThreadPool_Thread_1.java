package com.zd.test.thread.executor;

public class Executors_newCachedThreadPool_Thread_1 implements Runnable {

    private String username;

    public Executors_newCachedThreadPool_Thread_1(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " username=" + username + "开始" + System.currentTimeMillis());
        System.out.println(threadName + " username=" + username + "结束" + System.currentTimeMillis());

    }
}
