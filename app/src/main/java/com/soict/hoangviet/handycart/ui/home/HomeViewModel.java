package com.soict.hoangviet.handycart.ui.home;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.base.ObjectResponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.request.CartRequest;
import com.soict.hoangviet.handycart.entity.request.FavoriteProductRequest;
import com.soict.hoangviet.handycart.entity.request.FavoriteSupplierRequest;
import com.soict.hoangviet.handycart.entity.response.BannerResponse;
import com.soict.hoangviet.handycart.entity.response.CartAmountResponse;
import com.soict.hoangviet.handycart.entity.response.CartResponse;
import com.soict.hoangviet.handycart.entity.response.HomeProductResponse;
import com.soict.hoangviet.handycart.entity.response.HomeSupplierResponse;
import com.soict.hoangviet.handycart.eventbus.FavoriteProductEvent;
import com.soict.hoangviet.handycart.eventbus.FavoriteSupplierEvent;
import com.soict.hoangviet.handycart.utils.Define;
import com.soict.hoangviet.handycart.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HomeViewModel extends BaseViewModel {
    private Repository repository;
    private MutableLiveData<BannerResponse> listBanners = new MutableLiveData<>();
    private MutableLiveData<ListLoadmoreReponse<HomeProductResponse>> listHomeProduct;
    private MutableLiveData<ListLoadmoreReponse<HomeSupplierResponse>> listHomeSupplier;
    private MutableLiveData<ObjectResponse<HomeProductResponse>> favoriteProduct;
    private MutableLiveData<ObjectResponse<HomeProductResponse>> favoriteProductDelete;
    private MutableLiveData<ObjectResponse<HomeSupplierResponse>> favoriteSupplier;
    private MutableLiveData<ObjectResponse<HomeSupplierResponse>> favoriteSupplierDelete;
    private MutableLiveData<ObjectResponse<CartAmountResponse>> cartAmount;
    private MutableLiveData<ObjectResponse<CartResponse>> cartTransaction;
    private int pageIndexProduct = 1;
    private int pageIndexSupplier = 1;

    @Inject
    public HomeViewModel(Context context, Repository repository, ISharePreference mSharePreference) {
        super(context, mSharePreference);
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

    public MutableLiveData<ObjectResponse<HomeProductResponse>> getFavoriteProductDelete() {
        if (favoriteProductDelete == null) favoriteProductDelete = new MutableLiveData<>();
        return favoriteProductDelete;
    }

    public MutableLiveData<ObjectResponse<HomeSupplierResponse>> getFavoriteSupplier() {
        if (favoriteSupplier == null) favoriteSupplier = new MutableLiveData<>();
        return favoriteSupplier;
    }

    public MutableLiveData<ObjectResponse<HomeSupplierResponse>> getFavoriteSupplierDelete() {
        if (favoriteSupplierDelete == null) favoriteSupplierDelete = new MutableLiveData<>();
        return favoriteSupplierDelete;
    }

    public MutableLiveData<ObjectResponse<CartAmountResponse>> getCartAmount() {
        if (cartAmount == null) cartAmount = new MutableLiveData<>();
        return cartAmount;
    }

    public MutableLiveData<ObjectResponse<CartResponse>> getCartTransaction() {
        if (cartTransaction == null) cartTransaction = new MutableLiveData<>();
        return cartTransaction;
    }

    public void addToFavorite(HomeProductResponse data) {
        FavoriteProductRequest favoriteProductRequest = new FavoriteProductRequest(data.getId());
        mCompositeDisposable.add(
                repository.addToFavorite(mSharePreference.getAccessToken(), favoriteProductRequest)
                        .doOnSubscribe(disposable -> {

                        })
                        .subscribe(
                                response -> {
                                    ToastUtil.show(context, response.getMsg());
                                    getFavoriteProduct().setValue(new ObjectResponse<HomeProductResponse>().success(data));
                                    EventBus.getDefault().postSticky(new FavoriteProductEvent(false));
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
        FavoriteProductRequest favoriteProductRequest = new FavoriteProductRequest(data.getId());
        mCompositeDisposable.add(
                repository.deleteFromFavorite(mSharePreference.getAccessToken(), data.getId(), favoriteProductRequest)
                        .doOnSubscribe(disposable -> {

                        })
                        .subscribe(
                                response -> {
                                    ToastUtil.show(context, response.getMsg());
                                    getFavoriteProductDelete().setValue(new ObjectResponse<HomeProductResponse>().success(data));
                                    EventBus.getDefault().postSticky(new FavoriteProductEvent(false));
                                },
                                throwable -> {
                                    getFavoriteProductDelete().setValue(new ObjectResponse<HomeProductResponse>().error(throwable));
                                })

        );
    }

    public void addSupplierToFavorite(HomeSupplierResponse data) {
        FavoriteSupplierRequest favoriteSupplierRequest = new FavoriteSupplierRequest(data.getId());
        mCompositeDisposable.add(
                repository.addSupplierToFavorite(mSharePreference.getAccessToken(), favoriteSupplierRequest)
                        .doOnSubscribe(disposable -> {

                        })
                        .subscribe(
                                response -> {
                                    ToastUtil.show(context, response.getMsg());
                                    getFavoriteSupplier().setValue(new ObjectResponse<HomeSupplierResponse>().success(data));
                                    EventBus.getDefault().postSticky(new FavoriteSupplierEvent(false));
                                },
                                throwable -> {
                                    getFavoriteSupplier().setValue(new ObjectResponse<HomeSupplierResponse>().error(throwable));
                                })

        );
    }

    public void deleteSupplierFromFavorite(HomeSupplierResponse data) {
        mCompositeDisposable.add(
                repository.deleteSupplierFromFavorite(mSharePreference.getAccessToken(), data.getId())
                        .doOnSubscribe(disposable -> {
                        })
                        .subscribe(
                                response -> {
                                    ToastUtil.show(context, response.getMsg());
                                    getFavoriteSupplierDelete().setValue(new ObjectResponse<HomeSupplierResponse>().success(data));
                                    EventBus.getDefault().postSticky(new FavoriteSupplierEvent(false));
                                },
                                throwable -> {
                                    getFavoriteSupplierDelete().setValue(new ObjectResponse<HomeSupplierResponse>().error(throwable));
                                })

        );
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

    public void addToCartNoAuth(int productId, int productQuantity) {
        CartRequest cartRequest = new CartRequest(mSharePreference.getDeviceTokenId(), productId, productQuantity);
        mCompositeDisposable.add(
                repository.addToCartNoAuth(cartRequest)
                        .doOnSubscribe(disposable -> {
                            getCartTransaction().setValue(new ObjectResponse<CartResponse>().loading());
                        })
                        .doFinally(() -> {

                        })
                        .subscribe(
                                response -> {
                                    getCartTransaction().setValue(new ObjectResponse<CartResponse>().success(response.getData()));
                                },
                                throwable -> {
                                    getCartTransaction().setValue(new ObjectResponse<CartResponse>().error(throwable));
                                }
                        )
        );
    }

    public void addToCartWithAuth(int productId, int productQuantity) {
        CartRequest cartRequest = new CartRequest(mSharePreference.getDeviceTokenId(), productId, productQuantity);
        mCompositeDisposable.add(
                repository.addToCartWithAuth(mSharePreference.getAccessToken(), cartRequest)
                        .doOnSubscribe(disposable -> {
                            getCartTransaction().setValue(new ObjectResponse<CartResponse>().loading());
                        })
                        .doFinally(() -> {

                        })
                        .subscribe(
                                response -> {
                                    getCartTransaction().setValue(new ObjectResponse<CartResponse>().success(response.getData()));
                                },
                                throwable -> {
                                    getCartTransaction().setValue(new ObjectResponse<CartResponse>().error(throwable));
                                }
                        )
        );
    }


}
