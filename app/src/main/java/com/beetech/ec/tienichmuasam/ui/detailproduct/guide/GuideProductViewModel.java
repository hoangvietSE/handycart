package com.beetech.ec.tienichmuasam.ui.detailproduct.guide;

import android.content.Context;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;

import javax.inject.Inject;

public class GuideProductViewModel extends BaseViewModel {

    @Inject
    public GuideProductViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
