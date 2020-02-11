package com.beetech.ec.tienichmuasam.ui.detailproduct.description;

import android.content.Context;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;

import javax.inject.Inject;

public class DescriptionProductViewModel extends BaseViewModel {

    @Inject
    public DescriptionProductViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
