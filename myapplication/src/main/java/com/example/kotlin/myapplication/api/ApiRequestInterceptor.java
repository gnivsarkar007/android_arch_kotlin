package com.example.kotlin.myapplication.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ApiRequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        chain.request().headers().newBuilder()
                .add("Authorization", "94bc4b841e8143fb8c9c7d5f4455d8ce");
        return chain.proceed(chain.request());
    }
}
