package com.beetech.ec.tienichmuasam.ui.detailsupplier;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.base.ObjectResponse;
import com.beetech.ec.tienichmuasam.data.network.repository.Repository;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;
import com.beetech.ec.tienichmuasam.entity.response.CartAmountResponse;
import com.beetech.ec.tienichmuasam.entity.response.DetailSupplierResponse;

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
