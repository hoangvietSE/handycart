package com.soict.hoangviet.handycart.ui.notification;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class NotificationViewModel extends BaseViewModel {
    private Repository repository;

    @Inject
    public NotificationViewModel(Repository repository, CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference) {
        super(mCompositeDisposable, mSharePreference);
        this.repository = repository;
    }

    public boolean isLogin(){
        return mSharePreference.isLogin();
    }
}
