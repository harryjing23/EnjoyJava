package com.harry.myapplication.thread;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created on 2021/8/10.
 *
 * @author harry
 */
public class Test implements Externalizable {

    public static void main(String[] args) {

        ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return "init";
            }
        };

        new Thread() {
            @Override
            public void run() {
                super.run();

                threadLocal.set("run");
            }
        }.start();


        ForkJoinPool pool = ForkJoinPool.commonPool();
        MyTask<Object> task = new MyTask<>();
        pool.submit(task);


    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException {
 
    }


    public static class MySyn extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            return super.tryAcquire(arg);
        }
    }

    public static class MyTask<Integer> extends RecursiveTask<Integer> {

        @Override
        protected Integer compute() {



            return null;
        }
    }
}
