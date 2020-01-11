package com.soict.hoangviet.handycart.ui.favorite.supplier;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SupplierFavoriteViewModel extends BaseViewModel {

    private Repository repository;

    @Inject
    public SupplierFavoriteViewModel(CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference, Repository repository) {
        super(mCompositeDisposable, mSharePreference);
        this.repository = repository;
    }

    public boolean isLogin(){
        return mSharePreference.isLogin();
    }
}
