package com.harry.myapplication.retrofit;

/**
 * Created on 2021/7/14.
 *
 * @author harry
 */
public interface Wash {
    void wash();

    public static class WashAgent implements Wash{

        @Override
        public void wash() {

        }
    }
}
