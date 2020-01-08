package com.soict.hoangviet.handycart.ui.profile;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ProfileViewModel extends BaseViewModel {

    @Inject
    public ProfileViewModel(CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference) {
        super(mCompositeDisposable, mSharePreference);
    }
}
