package com.soict.hoangviet.handycart.ui.favorite;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class FavoriteViewModel extends BaseViewModel {

    @Inject
    public FavoriteViewModel(CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference) {
        super(mCompositeDisposable, mSharePreference);
    }
}
