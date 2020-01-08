package com.soict.hoangviet.handycart.di.module;

import android.content.Context;

import com.soict.hoangviet.handycart.BuildConfig;
import com.soict.hoangviet.handycart.data.network.ApiInterface;
import com.soict.hoangviet.handycart.data.network.LanguageInterceptor;
import com.soict.hoangviet.handycart.data.network.NetworkCheckerInterceptor;
import com.soict.hoangviet.handycart.data.network.TokenInterceptor;
import com.soict.hoangviet.handycart.utils.Define;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        LanguageInterceptor languageInterceptor = new LanguageInterceptor();
        TokenInterceptor tokenInterceptor = new TokenInterceptor();
        NetworkCheckerInterceptor networkCheckerInterceptor = new NetworkCheckerInterceptor(context);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(tokenInterceptor)
                .addInterceptor(networkCheckerInterceptor)
                .addInterceptor(languageInterceptor)
                .connectTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface(OkHttpClient client) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }
}
