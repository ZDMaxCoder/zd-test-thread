package com.zd.test.thread.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerThreadA implements Runnable {

    private Exchanger<String> exchanger;
    ExchangerThreadA(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    public void run() {

        try{
            String msg = "我是线程A中的变量";
            String exchangeRet = exchanger.exchange(msg);
            System.out.println("在线程A中得到线程B的变量 = " + exchangeRet);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
