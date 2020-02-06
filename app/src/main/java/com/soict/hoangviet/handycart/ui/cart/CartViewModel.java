package com.soict.hoangviet.handycart.ui.cart;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.base.ObjectResponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.request.CartTransactionDeleteRequest;
import com.soict.hoangviet.handycart.entity.request.CartTransactionRequest;
import com.soict.hoangviet.handycart.entity.response.CartDetailResponse;
import com.soict.hoangviet.handycart.entity.response.ProductListItem;
import com.soict.hoangviet.handycart.eventbus.CartAmountEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public class CartViewModel extends BaseViewModel {

    private Repository repository;
    private MutableLiveData<ObjectResponse<CartDetailResponse>> cartDetail;
    private MutableLiveData<Boolean> isUpdateCart;

    @Inject
    public CartViewModel(Context context, Repository repository, ISharePreference mSharePreference) {
        super(context, mSharePreference);
        this.repository = repository;
    }

    public MutableLiveData<ObjectResponse<CartDetailResponse>> getCartDetail() {
        if (cartDetail == null) cartDetail = new MutableLiveData<>();
        return cartDetail;
    }

    public MutableLiveData<Boolean> getIsUpdateCart() {
        if (isUpdateCart == null) isUpdateCart = new MutableLiveData<>(false);
        return isUpdateCart;
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

    public void updateCartDetailWithAuth(ProductListItem data, int quantity) {
        CartTransactionRequest request = new CartTransactionRequest(
                getCartDetail().getValue().getData().getCartId(),
                mSharePreference.getDeviceTokenId(),
                data.getProductId(),
                quantity
        );
        mCompositeDisposable.add(
                repository.updateCartDetailWithAuth(mSharePreference.getAccessToken(), request)
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

    public void updateCartDetailNoAuth(ProductListItem data, int quantity) {
        CartTransactionRequest request = new CartTransactionRequest(
                getCartDetail().getValue().getData().getCartId(),
                mSharePreference.getDeviceTokenId(),
                data.getProductId(),
                quantity
        );
        mCompositeDisposable.add(
                repository.updateCartDetailNoAuth(request)
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

    public void deleteItemCartWithAuth(ProductListItem data) {
        CartTransactionDeleteRequest request = new CartTransactionDeleteRequest(
                getCartDetail().getValue().getData().getCartId(),
                mSharePreference.getDeviceTokenId(),
                data.getProductId()
        );
        mCompositeDisposable.add(
                repository.deleteItemCartWithAuth(mSharePreference.getAccessToken(), request)
                        .doOnSubscribe(disposable -> {
                        })
                        .subscribe(response -> {
                            EventBus.getDefault().postSticky(new CartAmountEvent());
                            getCartDetail().setValue(new ObjectResponse<CartDetailResponse>().success(response.getData()));

                        }, throwable -> {
                            getCartDetail().setValue(new ObjectResponse<CartDetailResponse>().error(throwable));
                        })
        );
    }

    public void deleteItemCartNoAuth(ProductListItem data) {
        CartTransactionDeleteRequest request = new CartTransactionDeleteRequest(
                getCartDetail().getValue().getData().getCartId(),
                mSharePreference.getDeviceTokenId(),
                data.getProductId()
        );
        mCompositeDisposable.add(
                repository.deleteItemCartNoAuth(request)
                        .doOnSubscribe(disposable -> {
                        })
                        .subscribe(response -> {
                            EventBus.getDefault().postSticky(new CartAmountEvent());
                            getCartDetail().setValue(new ObjectResponse<CartDetailResponse>().success(response.getData()));

                        }, throwable -> {
                            getCartDetail().setValue(new ObjectResponse<CartDetailResponse>().error(throwable));
                        })
        );
    }
}
