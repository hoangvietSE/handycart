package com.soict.hoangviet.handycart.ui.detailproduct;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ObjectResponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.response.DetailProductResponse;

import javax.inject.Inject;

public class DetailProductViewModel extends BaseViewModel {

    private Repository repository;
    private MutableLiveData<ObjectResponse<DetailProductResponse>> detailProduct;

    public MutableLiveData<ObjectResponse<DetailProductResponse>> getDetailProduct() {
        if (detailProduct == null) detailProduct = new MutableLiveData<>();
        return detailProduct;
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
                            getDetailProduct().setValue(new ObjectResponse<DetailProductResponse>().loading());
                        })
                        .subscribe(response -> {
                            getDetailProduct().setValue(new ObjectResponse<DetailProductResponse>().success(response.getData()));
                        }, throwable -> {
                            getDetailProduct().setValue(new ObjectResponse<DetailProductResponse>().error(throwable));
                        })
        );
    }


}
