package com.zd.test.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author zdmaxcoder@gmail.com
 */
public class UseSemaphoreToPrintABC {



    public static void main(String[] args) {

        Semaphore semaphoreA = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(0);
        Semaphore semaphoreC = new Semaphore(0);

        new Thread(()->{

            try {
                while (true) {
                    semaphoreA.acquire();
                    System.out.print("A");
                    semaphoreB.release();
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }).start();

        new Thread(()->{

            try {

                while (true) {
                    semaphoreB.acquire();
                    System.out.print("B");
                    semaphoreC.release();
                }


            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(()->{

            try {

                while (true) {
                    semaphoreC.acquire();
                    System.out.print("C");
                    semaphoreA.release();
                }

            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();


        while (true) {

        }

    }


}
