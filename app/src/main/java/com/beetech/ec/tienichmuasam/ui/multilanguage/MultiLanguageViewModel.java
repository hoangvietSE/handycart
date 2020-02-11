package com.beetech.ec.tienichmuasam.ui.multilanguage;

import android.content.Context;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;

import javax.inject.Inject;

public class MultiLanguageViewModel extends BaseViewModel {

    @Inject
    public MultiLanguageViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
