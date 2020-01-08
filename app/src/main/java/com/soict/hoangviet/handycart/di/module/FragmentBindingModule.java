package com.soict.hoangviet.handycart.di.module;

import com.soict.hoangviet.handycart.ui.favorite.FavoriteFragment;
import com.soict.hoangviet.handycart.ui.home.HomeFragment;
import com.soict.hoangviet.handycart.ui.login.LoginFragment;
import com.soict.hoangviet.handycart.ui.master.MasterFragment;
import com.soict.hoangviet.handycart.ui.notification.NotificationFragment;
import com.soict.hoangviet.handycart.ui.profile.ProfileFragment;
import com.soict.hoangviet.handycart.ui.search.SearchFragment;
import com.soict.hoangviet.handycart.ui.splash.SplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    //TODO bind fragment
    @ContributesAndroidInjector
    abstract SplashFragment bindSplashFragment();

    @ContributesAndroidInjector
    abstract HomeFragment bindHomeFragment();

    @ContributesAndroidInjector
    abstract LoginFragment bindLoginFragment();

    @ContributesAndroidInjector
    abstract MasterFragment bindMasterFragment();

    @ContributesAndroidInjector
    abstract FavoriteFragment bindFavoriteFragment();

    @ContributesAndroidInjector
    abstract SearchFragment bindSearchFragment();

    @ContributesAndroidInjector
    abstract NotificationFragment bindNotificationFragment();

    @ContributesAndroidInjector
    abstract ProfileFragment bindProfileFragment();
}
