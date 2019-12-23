package com.zd.test.thread.concurrentframe;

import java.util.HashMap;
import java.util.Map;

public class HashMap_Concurrent_Test_1 {

    public static class MyService {
        public Map<String,String> map = new HashMap<String,String>();
    }

    public static class MyRunnable implements Runnable {

        private MyService myService;

        public MyRunnable(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {

            String threadName = Thread.currentThread().getName() + " ";
            for (int i = 0; i < 5000000; i++) {
                int j = i+1;
                myService.map.put(threadName + j, threadName + j);
                System.out.println(threadName + j);
            }
        }
    }

    public static void main(String[] args) {

        MyService myService = new MyService();
        Thread threadA = new Thread(new MyRunnable(myService));
        Thread threadB = new Thread(new MyRunnable(myService));
        threadA.start();
        threadB.start();

    }


}
