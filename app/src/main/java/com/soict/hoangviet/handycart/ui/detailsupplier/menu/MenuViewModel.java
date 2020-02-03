package com.soict.hoangviet.handycart.ui.detailsupplier.menu;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.response.ItemMenuDetailSupplierResponse;

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
