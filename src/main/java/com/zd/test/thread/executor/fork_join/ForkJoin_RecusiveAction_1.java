package com.zd.test.thread.executor.fork_join;

import java.util.concurrent.RecursiveAction;

public class ForkJoin_RecusiveAction_1 extends RecursiveAction {

    private int beginValue;
    private int endValue;

    public ForkJoin_RecusiveAction_1(int beginValue, int endValue) {
        this.beginValue = beginValue;
        this.endValue = endValue;
    }

    @Override
    protected void compute() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "------------");
        if (endValue - beginValue > 2 ) {
            int middleNum = (beginValue + endValue) / 2;
            ForkJoin_RecusiveAction_1 leftAction = new ForkJoin_RecusiveAction_1(beginValue, middleNum);
            ForkJoin_RecusiveAction_1 rightAction = new ForkJoin_RecusiveAction_1(middleNum+1, endValue);
            this.invokeAll(leftAction,rightAction);
        } else {
            System.out.println(threadName + " 打印组合为：" + beginValue + " - " + endValue);
        }

    }
}
