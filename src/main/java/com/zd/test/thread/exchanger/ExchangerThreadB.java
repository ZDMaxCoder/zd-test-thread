package com.zd.test.thread.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerThreadB implements Runnable {

    private Exchanger<String> exchanger;

    ExchangerThreadB(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    public void run() {

        try {
            String msg = "我是线程B中的变量";
            Object exchangeRet = exchanger.exchange(msg);
            System.out.println("在线程B中得到线程A中的变量 = "+exchangeRet);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
