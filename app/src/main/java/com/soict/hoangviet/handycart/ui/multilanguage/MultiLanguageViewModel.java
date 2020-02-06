package com.soict.hoangviet.handycart.ui.multilanguage;

import android.content.Context;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

public class MultiLanguageViewModel extends BaseViewModel {

    @Inject
    public MultiLanguageViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
