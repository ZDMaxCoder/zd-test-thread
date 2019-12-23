package com.zd.test.thread.executor;

public class Executors_newFixedThreadPool_Thread_1 implements Runnable {

    private String username;

    public Executors_newFixedThreadPool_Thread_1(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " " + username + " begin");
        System.out.println(threadName + " " + username + " end");
    }
}
