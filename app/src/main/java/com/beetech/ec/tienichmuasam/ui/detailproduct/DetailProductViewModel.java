package com.beetech.ec.tienichmuasam.ui.detailproduct;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.base.ObjectResponse;
import com.beetech.ec.tienichmuasam.data.network.repository.Repository;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;
import com.beetech.ec.tienichmuasam.entity.response.DetailProductResponse;

import javax.inject.Inject;

public class DetailProductViewModel extends BaseViewModel {

    private Repository repository;
    private MutableLiveData<ObjectResponse<DetailProductResponse>> detailProduct;
    private MutableLiveData<Integer> quantity;

    public MutableLiveData<ObjectResponse<DetailProductResponse>> getDetailProduct() {
        if (detailProduct == null) detailProduct = new MutableLiveData<>();
        return detailProduct;
    }

    public MutableLiveData<Integer> getQuantity() {
        if (quantity == null) quantity = new MutableLiveData<>();
        return quantity;
    }

    @Inject
    public DetailProductViewModel(Context context, Repository repository, ISharePreference mSharePreference) {
        super(context, mSharePreference);
        this.repository = repository;
    }

    public void setDetailProduct(int productId) {
        mCompositeDisposable.add(
                repository.getDetailProduct(productId)
                        .doOnSubscribe(disposable -> {
//                            getDetailProduct().setValue(new ObjectResponse<DetailProductResponse>().loading());
                        })
                        .subscribe(response -> {
                            getDetailProduct().setValue(new ObjectResponse<DetailProductResponse>().success(response.getData()));
                        }, throwable -> {
                            getDetailProduct().setValue(new ObjectResponse<DetailProductResponse>().error(throwable));
                        })
        );
    }


}
