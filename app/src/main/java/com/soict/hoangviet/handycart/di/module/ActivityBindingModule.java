package com.soict.hoangviet.handycart.di.module;


import com.soict.hoangviet.handycart.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();
}
