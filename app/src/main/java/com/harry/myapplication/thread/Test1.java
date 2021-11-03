package com.harry.myapplication.thread;

/**
 * Created on 2021/8/26.
 *
 * @author harry
 */
public class Test1 {
    public static void main(String[] args) {


        Thread1 thread1 = new Thread1();

        thread1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.suspend();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.resume();
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println("Thread1 run");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread1 end");
        }
    }
}
