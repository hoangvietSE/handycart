package com.beetech.ec.tienichmuasam.ui.detailsupplier.service;

import android.content.Context;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;

import javax.inject.Inject;

public class ServiceViewModel extends BaseViewModel {

    @Inject
    public ServiceViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
