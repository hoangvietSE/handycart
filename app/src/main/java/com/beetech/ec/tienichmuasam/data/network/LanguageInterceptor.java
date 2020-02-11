package com.beetech.ec.tienichmuasam.data.network;

import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LanguageInterceptor implements Interceptor {
    private ISharePreference sharePreference;

    public LanguageInterceptor(ISharePreference sharePreference) {
        this.sharePreference = sharePreference;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
                .addHeader("Content-Type","application/json")
                .addHeader("lang", sharePreference.getCurrentLanguage())
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
