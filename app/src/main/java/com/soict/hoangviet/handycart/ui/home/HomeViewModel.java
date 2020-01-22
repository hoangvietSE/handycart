package com.soict.hoangviet.handycart.ui.home;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.base.ObjectResponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.request.FavoriteRequest;
import com.soict.hoangviet.handycart.entity.response.BannerResponse;
import com.soict.hoangviet.handycart.entity.response.HomeProductResponse;
import com.soict.hoangviet.handycart.entity.response.HomeSupplierResponse;
import com.soict.hoangviet.handycart.utils.Define;
import com.soict.hoangviet.handycart.utils.ToastUtil;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HomeViewModel extends BaseViewModel {
    private Context context;
    private Repository repository;
    private MutableLiveData<BannerResponse> listBanners = new MutableLiveData<>();
    private MutableLiveData<ListLoadmoreReponse<HomeProductResponse>> listHomeProduct;
    private MutableLiveData<ListLoadmoreReponse<HomeSupplierResponse>> listHomeSupplier;
    private MutableLiveData<ObjectResponse<HomeProductResponse>> favoriteProduct;
    private MutableLiveData<ObjectResponse<HomeProductResponse>> favoriteProductDelete;
    private int pageIndexProduct = 1;
    private int pageIndexSupplier = 1;

    @Inject
    public HomeViewModel(Context context, Repository repository, CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference) {
        super(mCompositeDisposable, mSharePreference);
        this.repository = repository;
        this.context = context;
    }

    public MutableLiveData<BannerResponse> getListBanners() {
        if (listBanners == null) listBanners = new MutableLiveData<>();
        return listBanners;
    }

    public MutableLiveData<ListLoadmoreReponse<HomeProductResponse>> getListHomeProduct() {
        if (listHomeProduct == null) listHomeProduct = new MutableLiveData<>();
        return listHomeProduct;
    }

    public MutableLiveData<ListLoadmoreReponse<HomeSupplierResponse>> getListHomeSupplier() {
        if (listHomeSupplier == null) listHomeSupplier = new MutableLiveData<>();
        return listHomeSupplier;
    }

    public MutableLiveData<ObjectResponse<HomeProductResponse>> getFavoriteProduct() {
        if (favoriteProduct == null) favoriteProduct = new MutableLiveData<>();
        return favoriteProduct;
    }

    public void setFavoriteProduct(MutableLiveData<ObjectResponse<HomeProductResponse>> favoriteProduct) {
        this.favoriteProduct = favoriteProduct;
    }

    public MutableLiveData<ObjectResponse<HomeProductResponse>> getFavoriteProductDelete() {
        if (favoriteProductDelete == null) favoriteProductDelete = new MutableLiveData<>();
        return favoriteProductDelete;
    }

    public void setFavoriteProductDelete(MutableLiveData<ObjectResponse<HomeProductResponse>> favoriteProductDelete) {
        this.favoriteProductDelete = favoriteProductDelete;
    }

    public void addToFavorite(HomeProductResponse data) {
        FavoriteRequest favoriteRequest = new FavoriteRequest(data.getId());
        mCompositeDisposable.add(
                repository.addToFavorite(mSharePreference.getAccessToken(), favoriteRequest)
                        .doOnSubscribe(disposable -> {

                        })
                        .subscribe(
                                response -> {
                                    ToastUtil.show(context, response.getMsg());
                                    getFavoriteProduct().setValue(new ObjectResponse<HomeProductResponse>().success(data));
                                },
                                throwable -> {
                                    getFavoriteProduct().setValue(new ObjectResponse<HomeProductResponse>().error(throwable));
                                })

        );
    }

    public void setListBanners() {
        mCompositeDisposable.add(
                repository.getListBanners()
                        .doOnSubscribe(disposable -> {
                        })
                        .subscribe(
                                bannerResponse -> {
                                    getListBanners().setValue(bannerResponse);
                                },
                                throwable -> {

                                })

        );
    }

    public void setListHomeProductNoAuth(boolean isRefreshing) {
        HashMap<String, Object> data = new HashMap<>();
        data.put(Define.Api.Query.CATEGORY, Define.Api.BaseResponse.DEFAULT_INDEX);
        data.put(Define.Api.Query.PAGE, Define.Api.BaseResponse.DEFAULT_INDEX);
        data.put(Define.Api.Query.LIMIT, Define.Api.BaseResponse.DEFAULT_LIMIT);
        mCompositeDisposable.add(
                repository.getListHomeProductNoAuth(data)
                        .doOnSubscribe(disposable -> {
                        })
                        .doFinally(() -> {

                        })
                        .subscribe(response -> {
                                    pageIndexProduct++;
                                    getListHomeProduct().setValue(new ListLoadmoreReponse<HomeProductResponse>().success(
                                            response.getData(),
                                            isRefreshing,
                                            pageIndexProduct <= response.getTotalPage()
                                    ));
                                },
                                throwable -> {
                                    getListHomeProduct().setValue(new ListLoadmoreReponse<HomeProductResponse>().error(throwable));
                                })
        );
    }

    public void setListHomeSupplierNoAuth(boolean isRefreshing) {
        HashMap<String, Object> data = new HashMap<>();
        data.put(Define.Api.Query.PAGE, Define.Api.BaseResponse.DEFAULT_INDEX);
        data.put(Define.Api.Query.LIMIT, Define.Api.BaseResponse.DEFAULT_LIMIT);
        mCompositeDisposable.add(
                repository.getListHomeSupplierNoAuth(data)
                        .doOnSubscribe(disposable -> {
                        })
                        .doFinally(() -> {

                        })
                        .subscribe(response -> {
                                    pageIndexSupplier++;
                                    getListHomeSupplier().setValue(new ListLoadmoreReponse<HomeSupplierResponse>().success(
                                            response.getData(),
                                            isRefreshing,
                                            pageIndexSupplier <= response.getTotalPage()
                                    ));
                                },
                                throwable -> {
                                    getListHomeProduct().setValue(new ListLoadmoreReponse<HomeProductResponse>().error(throwable));
                                })
        );
    }

    public void setListHomeProductWithAuth(boolean isRefreshing) {
        HashMap<String, Object> data = new HashMap<>();
        data.put(Define.Api.Query.CATEGORY, Define.Api.BaseResponse.DEFAULT_INDEX);
        data.put(Define.Api.Query.PAGE, Define.Api.BaseResponse.DEFAULT_INDEX);
        data.put(Define.Api.Query.LIMIT, Define.Api.BaseResponse.DEFAULT_LIMIT);
        mCompositeDisposable.add(
                repository.getListHomeProductWithAuth(mSharePreference.getAccessToken(), data)
                        .doOnSubscribe(disposable -> {
                        })
                        .doFinally(() -> {

                        })
                        .subscribe(response -> {
                                    pageIndexProduct++;
                                    getListHomeProduct().setValue(new ListLoadmoreReponse<HomeProductResponse>().success(
                                            response.getData(),
                                            isRefreshing,
                                            pageIndexProduct <= response.getTotalPage()
                                    ));
                                },
                                throwable -> {
                                    getListHomeProduct().setValue(new ListLoadmoreReponse<HomeProductResponse>().error(throwable));
                                })
        );
    }

    public void setListHomeSupplierWithAuth(boolean isRefreshing) {
        HashMap<String, Object> data = new HashMap<>();
        data.put(Define.Api.Query.PAGE, Define.Api.BaseResponse.DEFAULT_INDEX);
        data.put(Define.Api.Query.LIMIT, Define.Api.BaseResponse.DEFAULT_LIMIT);
        mCompositeDisposable.add(
                repository.getListHomeSupplierWithAuth(mSharePreference.getAccessToken(), data)
                        .doOnSubscribe(disposable -> {
                        })
                        .doFinally(() -> {

                        })
                        .subscribe(response -> {
                                    pageIndexSupplier++;
                                    getListHomeSupplier().setValue(new ListLoadmoreReponse<HomeSupplierResponse>().success(
                                            response.getData(),
                                            isRefreshing,
                                            pageIndexSupplier <= response.getTotalPage()
                                    ));
                                },
                                throwable -> {
                                    getListHomeProduct().setValue(new ListLoadmoreReponse<HomeProductResponse>().error(throwable));
                                })
        );
    }

    public void deleteFromFavorite(HomeProductResponse data) {
        FavoriteRequest favoriteRequest = new FavoriteRequest(data.getId());
        mCompositeDisposable.add(
                repository.deleteFromFavorite(mSharePreference.getAccessToken(), data.getId(), favoriteRequest)
                        .doOnSubscribe(disposable -> {

                        })
                        .subscribe(
                                response -> {
                                    ToastUtil.show(context, response.getMsg());
                                    getFavoriteProductDelete().setValue(new ObjectResponse<HomeProductResponse>().success(data));
                                },
                                throwable -> {
                                    getFavoriteProductDelete().setValue(new ObjectResponse<HomeProductResponse>().error(throwable));
                                })

        );
    }
}
