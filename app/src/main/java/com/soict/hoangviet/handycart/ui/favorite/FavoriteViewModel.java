package com.soict.hoangviet.handycart.ui.favorite;

import android.content.Context;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class FavoriteViewModel extends BaseViewModel {

    @Inject
    public FavoriteViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
