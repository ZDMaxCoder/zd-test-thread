package com.zd.test.thread.executor.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *使用CompletionService解决Future的缺点
 */
public class CompletionService_Run_1 {

    public static class MyCallable implements Callable<String> {

        private String username;
        private long sleepValue;

        public MyCallable(String username, long sleepValue) {
            this.username = username;
            this.sleepValue = sleepValue;
        }

        @Override
        public String call() throws Exception {

            // 异常测试
            if (sleepValue == 5000) {
                int i = 1/0;
            }

            System.out.println(username);
            Thread.sleep(sleepValue);
            return "return " + username;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        MyCallable callable1 = new MyCallable("1", 5000);
        MyCallable callable2 = new MyCallable("2", 4000);
        MyCallable callable3 = new MyCallable("3", 3000);
        MyCallable callable4 = new MyCallable("4", 2000);
        MyCallable callable5 = new MyCallable("5", 1000);

        List<Callable> list = new ArrayList<Callable>();
        list.add(callable1);
        list.add(callable2);
        list.add(callable3);
        list.add(callable4);
        list.add(callable5);

        ExecutorService pool = Executors.newCachedThreadPool();
        CompletionService<String> csPool = new ExecutorCompletionService<String>(pool);

        int taskSize = list.size();

        for (int i = 0; i < taskSize; i++) {
            csPool.submit(list.get(i)); // 提交任务
        }

        for (int i = 0; i < taskSize; i++) {
            Future<String> future = csPool.take(); // take不受异常影响
            System.out.println(future);
            String ret = future.get(); // 抛出异常
            System.out.println(ret);
        }
//        for (int i = 0; i < taskSize; i++) {
//            Future<String> future = csPool.poll(10000,TimeUnit.MILLISECONDS); // poll不受异常影响
//            System.out.println(future.get()); // 抛出异常
//        }


    }


}
