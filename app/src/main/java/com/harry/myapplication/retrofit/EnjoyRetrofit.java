package com.harry.myapplication.retrofit;

import java.net.URI;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created on 2021/7/16.
 *
 * @author harry
 */
public class EnjoyRetrofit {

    public EnjoyRetrofit(Call.Factory factory, HttpUrl baseUrl) {

    }

    // 建造者设计模式。将一个复杂对象的构建和它的表示分离，可以使使用者不必知道内部组成的细节
    public static final class Builder {
        private HttpUrl baseUrl;
        private Call.Factory callFactory;

        // Call.Factory唯一的实现类就是OkHttpClient，用于定制OkHttpClient
        public Builder callFactory(Call.Factory factory) {
            this.callFactory = factory;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = HttpUrl.get(URI.create(baseUrl));
            return this;
        }

        public EnjoyRetrofit build() {
            if (baseUrl == null) {
                throw new IllegalStateException("Base Url required");
            }

            Call.Factory factory = this.callFactory;
            if (factory == null) {
                factory = new OkHttpClient();
            }

            return new EnjoyRetrofit(factory, baseUrl);
        }
    }
}
