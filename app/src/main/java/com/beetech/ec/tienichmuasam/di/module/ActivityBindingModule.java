package com.beetech.ec.tienichmuasam.di.module;


import com.beetech.ec.tienichmuasam.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();
}
