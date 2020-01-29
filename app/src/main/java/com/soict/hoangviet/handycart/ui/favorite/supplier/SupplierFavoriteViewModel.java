package com.soict.hoangviet.handycart.ui.favorite.supplier;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.base.ObjectResponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.response.HomeSupplierResponse;
import com.soict.hoangviet.handycart.entity.response.ProductFavoriteResponse;
import com.soict.hoangviet.handycart.entity.response.SupplierFavoriteResponse;
import com.soict.hoangviet.handycart.eventbus.FavoriteSupplierEvent;
import com.soict.hoangviet.handycart.utils.Define;
import com.soict.hoangviet.handycart.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SupplierFavoriteViewModel extends BaseViewModel {
    private Repository repository;
    private int pageIndex = 1;
    private MutableLiveData<ListLoadmoreReponse<SupplierFavoriteResponse>> supplierFavorite;
    private MutableLiveData<ObjectResponse<Integer>> supplierFavoriteDelete;

    public MutableLiveData<ListLoadmoreReponse<SupplierFavoriteResponse>> getSupplierFavorite() {
        if (supplierFavorite == null) supplierFavorite = new MutableLiveData<>();
        return supplierFavorite;
    }

    public MutableLiveData<ObjectResponse<Integer>> getSupplierFavoriteDelete() {
        if (supplierFavoriteDelete == null) supplierFavoriteDelete = new MutableLiveData<>();
        return supplierFavoriteDelete;
    }

    @Inject
    public SupplierFavoriteViewModel(Context context, ISharePreference mSharePreference, Repository repository) {
        super(context, mSharePreference);
        this.repository = repository;
    }

    public boolean isLogin() {
        return mSharePreference.isLogin();
    }

    public void setListSupplierFavorite(boolean isRefreshing) {
        if(mSharePreference.isLogin()){
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

    public void deleteSupplierFromFavorite(int position, int id) {
        mCompositeDisposable.add(
                repository.deleteSupplierFromFavorite(mSharePreference.getAccessToken(), id)
                        .doOnSubscribe(disposable -> {
                        })
                        .subscribe(
                                response -> {
                                    ToastUtil.show(context, response.getMsg());
                                    getSupplierFavoriteDelete().setValue(new ObjectResponse<Integer>().success(Integer.valueOf(position)));
                                    EventBus.getDefault().postSticky(new FavoriteSupplierEvent(true));
                                },
                                throwable -> {
                                    getSupplierFavoriteDelete().setValue(new ObjectResponse<Integer>().error(throwable));
                                })

        );
    }
}
