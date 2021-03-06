package com.beetech.ec.tienichmuasam.ui.favorite.product;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.base.ListLoadmoreReponse;
import com.beetech.ec.tienichmuasam.base.ObjectResponse;
import com.beetech.ec.tienichmuasam.data.network.repository.Repository;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;
import com.beetech.ec.tienichmuasam.entity.request.FavoriteProductRequest;
import com.beetech.ec.tienichmuasam.entity.response.ProductFavoriteResponse;
import com.beetech.ec.tienichmuasam.eventbus.FavoriteProductEvent;
import com.beetech.ec.tienichmuasam.utils.Define;
import com.beetech.ec.tienichmuasam.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import javax.inject.Inject;

public class ProductFavoriteViewModel extends BaseViewModel {

    private Repository repository;
    private int pageIndex;
    private MutableLiveData<ListLoadmoreReponse<ProductFavoriteResponse>> productFavorite;
    private MutableLiveData<ObjectResponse<Integer>> productFavoriteDelete;

    public MutableLiveData<ListLoadmoreReponse<ProductFavoriteResponse>> getProductFavorite() {
        if (productFavorite == null) productFavorite = new MutableLiveData<>();
        return productFavorite;
    }

    public MutableLiveData<ObjectResponse<Integer>> getProductFavoriteDelete() {
        if (productFavoriteDelete == null) productFavoriteDelete = new MutableLiveData<>();
        return productFavoriteDelete;
    }

    @Inject
    public ProductFavoriteViewModel(Context context, ISharePreference mSharePreference, Repository repository) {
        super(context, mSharePreference);
        this.repository = repository;
    }

    public boolean isLogin() {
        return mSharePreference.isLogin();
    }

    public void setListProductFavorite(boolean isRefreshing) {
        if(mSharePreference.isLogin()){
            if (isRefreshing) pageIndex = 1;
            HashMap<String, Object> data = new HashMap<>();
            data.put(Define.Api.Query.PAGE, pageIndex);
            data.put(Define.Api.Query.LIMIT, Define.Api.BaseResponse.DEFAULT_LIMIT);
            mCompositeDisposable.add(
                    repository.getListProductFavorite(mSharePreference.getAccessToken(), data)
                            .doOnSubscribe(disposable -> {
//                            getProductFavorite().setValue(new ListLoadmoreReponse<ProductFavoriteResponse>().loading());
                            })
                            .doFinally(() -> {
                            })
                            .subscribe(
                                    response -> {
                                        pageIndex++;
                                        getProductFavorite().setValue(new ListLoadmoreReponse<ProductFavoriteResponse>().success(
                                                response.getData(),
                                                isRefreshing,
                                                pageIndex <= response.getTotalPage()
                                        ));
                                    },
                                    throwable -> {
                                        getProductFavorite().setValue(new ListLoadmoreReponse<ProductFavoriteResponse>().error(throwable));
                                    }
                            )

            );
        }
    }

    public void deleteProductFromFavorite(int position, int id) {
        FavoriteProductRequest favoriteProductRequest = new FavoriteProductRequest(id);
        mCompositeDisposable.add(
                repository.deleteFromFavorite(mSharePreference.getAccessToken(), id, favoriteProductRequest)
                        .doOnSubscribe(disposable -> {

                        })
                        .subscribe(
                                response -> {
                                    ToastUtil.show(context, response.getMsg());
                                    getProductFavoriteDelete().setValue(new ObjectResponse<Integer>().success(Integer.valueOf(position)));
                                    EventBus.getDefault().postSticky(new FavoriteProductEvent(true));
                                },
                                throwable -> {
                                    getProductFavoriteDelete().setValue(new ObjectResponse<Integer>().error(throwable));
                                })

        );
    }
}
