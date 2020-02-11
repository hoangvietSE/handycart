package com.beetech.ec.tienichmuasam.ui.favorite;

import android.content.Context;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;

import javax.inject.Inject;

public class FavoriteViewModel extends BaseViewModel {

    @Inject
    public FavoriteViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
