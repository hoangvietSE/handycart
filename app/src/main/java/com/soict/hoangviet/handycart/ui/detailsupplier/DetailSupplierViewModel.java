package com.soict.hoangviet.handycart.ui.detailsupplier;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.base.ObjectResponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.response.CartAmountResponse;
import com.soict.hoangviet.handycart.entity.response.DetailSupplierResponse;
import com.soict.hoangviet.handycart.entity.response.ItemMenuDetailSupplierResponse;

import javax.inject.Inject;

public class DetailSupplierViewModel extends BaseViewModel {
    private Repository repository;
    private MutableLiveData<ObjectResponse<CartAmountResponse>> cartAmount;
    private MutableLiveData<ObjectResponse<DetailSupplierResponse>> detailSupplier;

    @Inject
    public DetailSupplierViewModel(Context context, Repository repository, ISharePreference mSharePreference) {
        super(context, mSharePreference);
        this.repository = repository;
    }

    public MutableLiveData<ObjectResponse<CartAmountResponse>> getCartAmount() {
        if (cartAmount == null) cartAmount = new MutableLiveData<>();
        return cartAmount;
    }

    public MutableLiveData<ObjectResponse<DetailSupplierResponse>> getDetailSupplier() {
        if (detailSupplier == null) detailSupplier = new MutableLiveData<>();
        return detailSupplier;
    }

    public void getCartAmountNoAuth() {
        mCompositeDisposable.add(
                repository.getCartAmountNoAuth(mSharePreference.getDeviceTokenId())
                        .doOnSubscribe(disposable -> {

                        })
                        .doFinally(() -> {

                        })
                        .subscribe(
                                response -> {
                                    getCartAmount().setValue(new ObjectResponse<CartAmountResponse>().success(response.getData()));
                                },
                                throwable -> {
                                    getCartAmount().setValue(new ObjectResponse<CartAmountResponse>().error(throwable));
                                }
                        )
        );
    }

    public void getCartAmountWithAuth() {
        mCompositeDisposable.add(
                repository.getCartAmountWithAuth(mSharePreference.getAccessToken(), mSharePreference.getDeviceTokenId())
                        .doOnSubscribe(disposable -> {

                        })
                        .doFinally(() -> {

                        })
                        .subscribe(
                                response -> {
                                    getCartAmount().setValue(new ObjectResponse<CartAmountResponse>().success(response.getData()));
                                },
                                throwable -> {
                                    getCartAmount().setValue(new ObjectResponse<CartAmountResponse>().error(throwable));
                                }
                        )
        );
    }

    public void setDetailSupplier(int supplierId) {
        mCompositeDisposable.add(
                repository.getDetailSupplier(mSharePreference.getAccessToken(), supplierId)
                        .doOnSubscribe(disposable -> {
                        })
                        .doFinally(() -> {
                        })
                        .subscribe(
                                response -> {
                                    getDetailSupplier().setValue(new ObjectResponse<DetailSupplierResponse>().success(response.getData()));
                                },
                                throwable -> {
                                    getDetailSupplier().setValue(new ObjectResponse<DetailSupplierResponse>().error(throwable));
                                }
                        )
        );
    }

}
