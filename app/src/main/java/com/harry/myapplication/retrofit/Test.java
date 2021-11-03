package com.harry.myapplication.retrofit;

/**
 * Created on 2021/7/16.
 *
 * @author harry
 */
public class Test {

//    public static void main(String[] args) {
//        // 用Retrofit实现
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://restapi.amap.com").build();
//        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
//
//        // 自定义实现
//
//    }

    public static void main(String[] args) {

        for (int i = 0; i <= 1920; i++) {
            String format = String.format("<dimen name=\"w" + i + "\">%.2fpx</dimen>", (float)i * 1024 / 1920);
            System.out.println(format);
        }

    }
}
