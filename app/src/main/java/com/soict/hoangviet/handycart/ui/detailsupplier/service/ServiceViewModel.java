package com.soict.hoangviet.handycart.ui.detailsupplier.service;

import android.content.Context;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

public class ServiceViewModel extends BaseViewModel {

    @Inject
    public ServiceViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
