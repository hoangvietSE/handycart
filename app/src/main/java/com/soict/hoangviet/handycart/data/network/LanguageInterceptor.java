package com.soict.hoangviet.handycart.data.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LanguageInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
                .header("Content-Type","application/json")
                .header("lang", "vi")
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
