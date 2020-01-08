package com.soict.hoangviet.handycart.ui.notification;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class NotificationViewModel extends BaseViewModel {

    @Inject
    public NotificationViewModel(CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference) {
        super(mCompositeDisposable, mSharePreference);
    }
}
