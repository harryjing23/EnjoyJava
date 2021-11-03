package com.harry.myapplication.thread;

/**
 * Created on 2021/8/26.
 *
 * @author harry
 */
public class Test2 {

    public static void main(String[] args) {

        new Test2().test();

    }

    MyThread t1 = new MyThread(1);
    MyThread t2 = new MyThread(2);
    MyThread t3 = new MyThread(3);

    public void test() {


        t2.start();
        t3.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
    }

    class MyThread extends Thread {
        private int id;

        public MyThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("t" + id + "start");

            try {

                if (id == 3) {
                    t2.join();

                } else if (id == 2) {
                    t1.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("t" + id + "end");
        }
    }
}
