package com.soict.hoangviet.handycart.ui.cart;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.base.ObjectResponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.response.CartDetailResponse;

import javax.inject.Inject;

public class CartViewModel extends BaseViewModel {

    private Repository repository;
    private MutableLiveData<ObjectResponse<CartDetailResponse>> cartDetail;

    @Inject
    public CartViewModel(Context context, Repository repository, ISharePreference mSharePreference) {
        super(context, mSharePreference);
        this.repository = repository;
    }

    public MutableLiveData<ObjectResponse<CartDetailResponse>> getCartDetail() {
        if (cartDetail == null) cartDetail = new MutableLiveData<>();
        return cartDetail;
    }

    public void setCartDetailWithAuth() {
        mCompositeDisposable.add(
                repository.getCartDetailWithAuth(mSharePreference.getAccessToken(), mSharePreference.getDeviceTokenId())
                        .doOnSubscribe(disposable -> {
                            getCartDetail().setValue(new ObjectResponse<CartDetailResponse>().loading());
                        })
                        .subscribe(response -> {
                            getCartDetail().setValue(new ObjectResponse<CartDetailResponse>().success(response.getData()));

                        }, throwable -> {
                            getCartDetail().setValue(new ObjectResponse<CartDetailResponse>().error(throwable));

                        })
        );
    }

    public void setCartDetailNoAuth() {
        mCompositeDisposable.add(
                repository.getCartDetailNoAuth(mSharePreference.getDeviceTokenId())
                        .doOnSubscribe(disposable -> {
                            getCartDetail().setValue(new ObjectResponse<CartDetailResponse>().loading());
                        })
                        .subscribe(response -> {
                            getCartDetail().setValue(new ObjectResponse<CartDetailResponse>().success(response.getData()));
                        }, throwable -> {
                            getCartDetail().setValue(new ObjectResponse<CartDetailResponse>().error(throwable));
                        })
        );
    }

}
