package com.soict.hoangviet.handycart.ui.favorite.product;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ProductFavoriteViewModel extends BaseViewModel {

    private Repository repository;

    @Inject
    public ProductFavoriteViewModel(CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference, Repository repository) {
        super(mCompositeDisposable, mSharePreference);
        this.repository = repository;
    }

    public boolean isLogin(){
        return mSharePreference.isLogin();
    }
}
