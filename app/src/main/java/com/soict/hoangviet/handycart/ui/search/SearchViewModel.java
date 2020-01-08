package com.soict.hoangviet.handycart.ui.search;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SearchViewModel extends BaseViewModel {

    @Inject
    public SearchViewModel(CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference) {
        super(mCompositeDisposable, mSharePreference);
    }
}
