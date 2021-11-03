package com.harry.myapplication.reflect;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created on 2021/7/1.
 *
 * @author harry
 */
public class Deserialize {

    public static void main(String[] args) {
        // Gson
        Response<Data> dataResponse = new Response(new Data("数据"), 200, "成功");
        Gson gson = new Gson();
        // 序列化
        String json = gson.toJson(dataResponse);
        System.out.println(json);
        // 反序列化
        // 这样会报错，因为fromJson时没有指定泛型则会认为是Gson提供的LinkedTreeMap，而LinkedTreeMap转换不成Data
//        Response<Data> response = gson.fromJson(json, Response.class);
        // Gson提供了TypeToken，应该用这个反序列化
        Type type = new TypeToken<Response<Data>>() {
        }.getType();
        System.out.println(type);
        Response<Data> response = gson.fromJson(json, type);
        System.out.println(response.data.getClass());

    }

    static class Response<T> {
        T data;
        int code;
        String message;

        public Response(T data, int code, String message) {
            this.data = data;
            this.code = code;
            this.message = message;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "data=" + data +
                    ", code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    static class Data {
        String result;

        public Data(String result) {
            this.result = result;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "result='" + result + '\'' +
                    '}';
        }
    }

    static class TypeReference<T> {
        Type type;

        public TypeReference() {

            Type genericSuperclass = getClass().getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            // 因为泛型类可以声明多个泛型，所以是个数组
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            type = typeArguments[0];
        }

        public Type getType() {
            return type;
        }
    }
}
