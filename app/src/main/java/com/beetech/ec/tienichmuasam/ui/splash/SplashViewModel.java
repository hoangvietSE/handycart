package com.beetech.ec.tienichmuasam.ui.splash;


import android.content.Context;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;

import javax.inject.Inject;

public class SplashViewModel extends BaseViewModel {

    @Inject
    SplashViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
