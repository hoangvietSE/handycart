package com.beetech.ec.tienichmuasam.ui.favorite.supplier;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.base.ListLoadmoreReponse;
import com.beetech.ec.tienichmuasam.base.ObjectResponse;
import com.beetech.ec.tienichmuasam.data.network.repository.Repository;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;
import com.beetech.ec.tienichmuasam.entity.response.SupplierFavoriteResponse;
import com.beetech.ec.tienichmuasam.eventbus.FavoriteSupplierEvent;
import com.beetech.ec.tienichmuasam.utils.Define;
import com.beetech.ec.tienichmuasam.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import javax.inject.Inject;

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
