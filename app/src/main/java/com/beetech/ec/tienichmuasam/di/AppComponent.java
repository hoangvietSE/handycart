package com.beetech.ec.tienichmuasam.di;

import android.app.Application;


import com.beetech.ec.tienichmuasam.application.BaseApplication;
import com.beetech.ec.tienichmuasam.di.module.ActivityBindingModule;
import com.beetech.ec.tienichmuasam.di.module.AppModule;
import com.beetech.ec.tienichmuasam.di.module.FragmentBindingModule;
import com.beetech.ec.tienichmuasam.di.module.NetworkModule;
import com.beetech.ec.tienichmuasam.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBindingModule.class,
        FragmentBindingModule.class,
        ViewModelModule.class,
        NetworkModule.class
})
public interface AppComponent {

    void inject(BaseApplication baseApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
