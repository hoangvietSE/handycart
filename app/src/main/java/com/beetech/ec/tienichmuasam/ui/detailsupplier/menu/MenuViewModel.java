package com.beetech.ec.tienichmuasam.ui.detailsupplier.menu;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.base.ListLoadmoreReponse;
import com.beetech.ec.tienichmuasam.data.network.repository.Repository;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;
import com.beetech.ec.tienichmuasam.entity.response.ItemMenuDetailSupplierResponse;

import javax.inject.Inject;

public class MenuViewModel extends BaseViewModel {
    private Repository repository;
    private MutableLiveData<ListLoadmoreReponse<ItemMenuDetailSupplierResponse>> menuProduct;
    private int pageIndex = 1;

    @Inject
    public MenuViewModel(Context context, Repository repository, ISharePreference mSharePreference) {
        super(context, mSharePreference);
        this.repository = repository;
    }

    public MutableLiveData<ListLoadmoreReponse<ItemMenuDetailSupplierResponse>> getMenuProduct() {
        if (menuProduct == null) menuProduct = new MutableLiveData<>();
        return menuProduct;
    }

    public void setMenuDetail(int supplierId) {
        mCompositeDisposable.add(
                repository.getMenuProduct(supplierId, pageIndex)
                        .doOnSubscribe(disposable -> {
                        })
                        .doFinally(() -> {
                        })
                        .subscribe(
                                response -> {
                                    pageIndex++;
                                    getMenuProduct().setValue(new ListLoadmoreReponse<ItemMenuDetailSupplierResponse>().success(
                                            response.getData(),
                                            false,
                                            pageIndex <= response.getTotalPage()));
                                },
                                throwable -> {
                                    getMenuProduct().setValue(new ListLoadmoreReponse<ItemMenuDetailSupplierResponse>().error(throwable));
                                }
                        )
        );
    }
}
