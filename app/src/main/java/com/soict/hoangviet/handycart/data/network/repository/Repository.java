package com.soict.hoangviet.handycart.data.network.repository;

import com.google.gson.Gson;
import com.soict.hoangviet.handycart.base.BaseResponse;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.base.ListResponse;
import com.soict.hoangviet.handycart.base.ObjectResponse;
import com.soict.hoangviet.handycart.data.network.ApiInterface;
import com.soict.hoangviet.handycart.entity.request.CartRequest;
import com.soict.hoangviet.handycart.entity.request.FavoriteProductRequest;
import com.soict.hoangviet.handycart.entity.request.FavoriteSupplierRequest;
import com.soict.hoangviet.handycart.entity.request.LoginRequest;
import com.soict.hoangviet.handycart.entity.request.DeleteRequest;
import com.soict.hoangviet.handycart.entity.response.BannerResponse;
import com.soict.hoangviet.handycart.entity.response.CartAmountResponse;
import com.soict.hoangviet.handycart.entity.response.CartDetailResponse;
import com.soict.hoangviet.handycart.entity.response.CartResponse;
import com.soict.hoangviet.handycart.entity.response.CategoryResponse;
import com.soict.hoangviet.handycart.entity.response.DetailProductResponse;
import com.soict.hoangviet.handycart.entity.response.DetailSupplierResponse;
import com.soict.hoangviet.handycart.entity.response.HomeProductResponse;
import com.soict.hoangviet.handycart.entity.response.HomeSupplierResponse;
import com.soict.hoangviet.handycart.entity.response.ItemMenuDetailSupplierResponse;
import com.soict.hoangviet.handycart.entity.response.LoginResponse;
import com.soict.hoangviet.handycart.entity.response.ProductFavoriteResponse;
import com.soict.hoangviet.handycart.entity.response.SearchProductResponse;
import com.soict.hoangviet.handycart.entity.response.SupplierFavoriteResponse;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Repository {
    private final ApiInterface apiInterface;

    @Inject
    Repository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    private RequestBody createRequestBody(Object request) {
        String json = new Gson().toJson(request);
        return RequestBody.create(MultipartBody.FORM, json);
    }

    public Single<ListResponse<SearchProductResponse>> getListSearchProduct(HashMap<String, Object> data) {
        return apiInterface.getListSearchProduct(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BannerResponse> getListBanners() {
        return apiInterface.getListBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ListResponse<HomeProductResponse>> getListHomeProductNoAuth(HashMap<String, Object> data) {
        return apiInterface.getListHomeProductNoAuth(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ListResponse<HomeSupplierResponse>> getListHomeSupplierNoAuth(HashMap<String, Object> data) {
        return apiInterface.getListHomeSupplierNoAuth(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ListResponse<HomeProductResponse>> getListHomeProductWithAuth(String accessToken, HashMap<String, Object> data) {
        return apiInterface.getListHomeProductWithAuth(accessToken, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ListResponse<HomeSupplierResponse>> getListHomeSupplierWithAuth(String accessToken, HashMap<String, Object> data) {
        return apiInterface.getListHomeSupplierWithAuth(accessToken, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ListResponse<CategoryResponse>> getCategory(int limit) {
        return apiInterface.getCategory(limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<LoginResponse>> login(LoginRequest loginRequest) {
        return apiInterface.login((loginRequest))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ListLoadmoreReponse<ProductFavoriteResponse>> getListProductFavorite(String accessToken, HashMap<String, Object> data) {
        return apiInterface.getListProductFavorite(accessToken, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ListLoadmoreReponse<SupplierFavoriteResponse>> getListSupplierFavorite(String accessToken, HashMap<String, Object> data) {
        return apiInterface.getListSupplierFavorite(accessToken, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable logOut(String accessToken, int id, DeleteRequest deleteRequest) {
        return apiInterface.logOut(
                accessToken,
                id,
                (deleteRequest)
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //Favorite
    public Single<BaseResponse> addToFavorite(String accessToken, FavoriteProductRequest favoriteProductRequest) {
        return apiInterface.addToFavorite(accessToken, favoriteProductRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseResponse> deleteFromFavorite(String accessToken, int id, FavoriteProductRequest favoriteProductRequest) {
        return apiInterface.deleteFromFavorite(accessToken, favoriteProductRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseResponse> addSupplierToFavorite(String accessToken, FavoriteSupplierRequest favoriteSupplierRequest) {
        return apiInterface.addSupplierToFavorite(accessToken, favoriteSupplierRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseResponse> deleteSupplierFromFavorite(String accessToken, int supplierId) {
        return apiInterface.deleteSupplierFromFavorite(accessToken, supplierId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<CartAmountResponse>> getCartAmountNoAuth(String deviceId) {
        return apiInterface.getCartAmountNoAuth(deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<CartAmountResponse>> getCartAmountWithAuth(String accessToken, String deviceId) {
        return apiInterface.getCartAmountWithAuth(accessToken, deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<CartResponse>> addToCartNoAuth(CartRequest cartRequest) {
        return apiInterface.addToCartNoAuth(cartRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<CartResponse>> addToCartWithAuth(String accessToken, CartRequest cartRequest) {
        return apiInterface.addToCartWithAuth(accessToken, cartRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<DetailProductResponse>> getDetailProduct(int productId) {
        return apiInterface.getDetailProduct(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<CartDetailResponse>> getCartDetailWithAuth(String accessToken, String deviceId) {
        return apiInterface.getCartDetailWithAuth(accessToken, deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<CartDetailResponse>> getCartDetailNoAuth(String deviceId) {
        return apiInterface.getCartDetailNoAuth(deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<DetailSupplierResponse>> getDetailSupplier(String accessToken, int supplierId) {
        return apiInterface.getDetailSupplier(accessToken, supplierId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ListResponse<ItemMenuDetailSupplierResponse>> getMenuProduct(int supplierId, int pageIndex) {
        return apiInterface.getMenuProduct(supplierId, pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
