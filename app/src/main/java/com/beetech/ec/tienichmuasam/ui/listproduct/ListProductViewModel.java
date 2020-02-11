package com.beetech.ec.tienichmuasam.ui.listproduct;

import android.content.Context;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;

import javax.inject.Inject;

public class ListProductViewModel extends BaseViewModel {

    @Inject
    public ListProductViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
