package com.soict.hoangviet.handycart.ui.detailproduct.guide;

import android.content.Context;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

public class GuideProductViewModel extends BaseViewModel {

    @Inject
    public GuideProductViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
