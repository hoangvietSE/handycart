package com.soict.hoangviet.handycart.ui.master;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MasterViewModel extends BaseViewModel {

    @Inject
    public MasterViewModel(CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference) {
        super(mCompositeDisposable, mSharePreference);
    }


}
