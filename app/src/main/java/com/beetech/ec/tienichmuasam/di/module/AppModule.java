package com.beetech.ec.tienichmuasam.di.module;

import android.app.Application;
import android.content.Context;

import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;
import com.beetech.ec.tienichmuasam.data.sharepreference.SharePreference;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    public Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    public ISharePreference provideSharePreference(Context context) {
        return new SharePreference(context);
    }

//    @Provides
//    @Singleton
//    public CompositeDisposable provideCompositeDisposable() {
//        return new CompositeDisposable();
//    }
}
