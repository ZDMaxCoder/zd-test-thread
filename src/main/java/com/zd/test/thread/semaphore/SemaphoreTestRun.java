package com.zd.test.thread.semaphore;

public class SemaphoreTestRun {

    public static void main(String[] args) {

        SemaphoreTest semaphoreTest = new SemaphoreTest();

        Thread threadA = new Thread(semaphoreTest);
        threadA.setName("A");
        Thread threadB = new Thread(semaphoreTest);
        threadB.setName("B");
        Thread threadC = new Thread(semaphoreTest);
        threadC.setName("C");

        threadA.start();
        threadB.start();
        threadC.start();


    }
}
