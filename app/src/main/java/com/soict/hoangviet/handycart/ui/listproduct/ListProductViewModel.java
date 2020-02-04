package com.soict.hoangviet.handycart.ui.listproduct;

import android.content.Context;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

public class ListProductViewModel extends BaseViewModel {

    @Inject
    public ListProductViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
