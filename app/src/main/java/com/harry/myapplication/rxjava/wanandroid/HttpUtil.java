package com.harry.myapplication.rxjava.wanandroid;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 2022/3/14.
 *
 * @author harry
 */
public class HttpUtil {

    private static final String TAG = "HttpUtil";

    public static String BASE_URL = "https://www.wanandroid.com";

    public static Retrofit getRetrofit() {
        // okhttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder
                .connectTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .build();
        
        return new Retrofit.Builder().baseUrl(BASE_URL)
                // 请求用OkHttp
                .client(client)
                // 响应用RxJava
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                // 用Gson解析成JavaBean
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
