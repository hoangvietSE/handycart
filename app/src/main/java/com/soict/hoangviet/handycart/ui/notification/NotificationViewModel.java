package com.soict.hoangviet.handycart.ui.notification;

import android.content.Context;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class NotificationViewModel extends BaseViewModel {
    private Repository repository;

    @Inject
    public NotificationViewModel(Context context, Repository repository, ISharePreference mSharePreference) {
        super(context, mSharePreference);
        this.repository = repository;
    }

    public boolean isLogin(){
        return mSharePreference.isLogin();
    }
}
