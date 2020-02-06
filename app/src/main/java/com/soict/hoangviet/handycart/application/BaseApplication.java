package com.soict.hoangviet.handycart.application;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.di.DaggerAppComponent;
import com.soict.hoangviet.handycart.utils.LanguageUtil;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class BaseApplication extends Application implements HasActivityInjector {

    private static final String TAG = BaseApplication.class.getSimpleName();
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    ISharePreference mSharePreference;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
        getDeviceTokenId();
        if (mSharePreference.getCurrentLanguage() != "") {
            LanguageUtil.setCurrentLanguage(this, mSharePreference.getCurrentLanguage());
        }
    }

    private void getDeviceTokenId() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "getInstanceId failed", task.getException());
                        return;
                    }
                    // Get new Instance ID token
                    String token = task.getResult().getToken();
                    mSharePreference.setDeviceTokenId(token);
                });
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}

