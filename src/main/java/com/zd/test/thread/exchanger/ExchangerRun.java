package com.zd.test.thread.exchanger;

import java.util.concurrent.Exchanger;

/**
 * 类Exchanger的主要作用是使2个线程之间互相进行通信。
 * 类Exchanger中的方法exchange()具有阻塞的特性，也就是此方法被调用后等待其他线程来取得数据，如果没有其他线程来取得数据，则一直阻塞等待。
 */
public class ExchangerRun {

    public static void main(String[] args) {

        Exchanger<String> stringExchanger = new Exchanger<String>();
        Thread threadA = new Thread(new ExchangerThreadA(stringExchanger));
        Thread threadB = new Thread(new ExchangerThreadB(stringExchanger));

        threadA.start();
        threadB.start();

    }


}
