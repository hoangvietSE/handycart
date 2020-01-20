package com.soict.hoangviet.handycart.ui.favorite.supplier;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.response.ProductFavoriteResponse;
import com.soict.hoangviet.handycart.entity.response.SupplierFavoriteResponse;
import com.soict.hoangviet.handycart.utils.Define;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SupplierFavoriteViewModel extends BaseViewModel {
    private Repository repository;
    private int pageIndex = 1;
    private MutableLiveData<ListLoadmoreReponse<SupplierFavoriteResponse>> supplierFavorite;

    public MutableLiveData<ListLoadmoreReponse<SupplierFavoriteResponse>> getSupplierFavorite() {
        if (supplierFavorite == null) supplierFavorite = new MutableLiveData<>();
        return supplierFavorite;
    }

    @Inject
    public SupplierFavoriteViewModel(CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference, Repository repository) {
        super(mCompositeDisposable, mSharePreference);
        this.repository = repository;
    }

    public boolean isLogin() {
        return mSharePreference.isLogin();
    }

    public void setListSupplierFavorite(boolean isRefreshing) {
        if (isRefreshing) pageIndex = 1;
        HashMap<String, Object> data = new HashMap<>();
        data.put(Define.Api.Query.PAGE, pageIndex);
        data.put(Define.Api.Query.LIMIT, Define.Api.BaseResponse.DEFAULT_LIMIT);
        mCompositeDisposable.add(
                repository.getListSupplierFavorite(mSharePreference.getAccessToken(), data)
                        .doOnSubscribe(disposable -> {
//                            getProductFavorite().setValue(new ListLoadmoreReponse<ProductFavoriteResponse>().loading());
                        })
                        .doFinally(() -> {
                        })
                        .subscribe(
                                response -> {
                                    pageIndex++;
                                    getSupplierFavorite().setValue(new ListLoadmoreReponse<SupplierFavoriteResponse>().success(
                                            response.getData(),
                                            isRefreshing,
                                            pageIndex <= response.getTotalPage()
                                    ));
                                },
                                throwable -> {
                                    getSupplierFavorite().setValue(new ListLoadmoreReponse<SupplierFavoriteResponse>().error(throwable));
                                }
                        )

        );
    }


}
