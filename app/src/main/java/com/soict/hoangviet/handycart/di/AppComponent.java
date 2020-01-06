package com.soict.hoangviet.handycart.di;

import android.app.Application;


import com.soict.hoangviet.handycart.application.BaseApplication;
import com.soict.hoangviet.handycart.di.module.ActivityBindingModule;
import com.soict.hoangviet.handycart.di.module.AppModule;
import com.soict.hoangviet.handycart.di.module.FragmentBindingModule;
import com.soict.hoangviet.handycart.di.module.NetworkModule;
import com.soict.hoangviet.handycart.di.module.ViewModelModule;

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
