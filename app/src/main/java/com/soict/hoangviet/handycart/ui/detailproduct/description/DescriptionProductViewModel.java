package com.soict.hoangviet.handycart.ui.detailproduct.description;

import android.content.Context;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

public class DescriptionProductViewModel extends BaseViewModel {

    @Inject
    public DescriptionProductViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
