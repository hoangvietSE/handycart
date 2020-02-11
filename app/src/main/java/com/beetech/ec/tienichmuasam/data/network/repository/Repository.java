package com.beetech.ec.tienichmuasam.data.network.repository;

import com.google.gson.Gson;
import com.beetech.ec.tienichmuasam.base.BaseResponse;
import com.beetech.ec.tienichmuasam.base.ListLoadmoreReponse;
import com.beetech.ec.tienichmuasam.base.ListResponse;
import com.beetech.ec.tienichmuasam.base.ObjectResponse;
import com.beetech.ec.tienichmuasam.data.network.ApiInterface;
import com.beetech.ec.tienichmuasam.entity.request.CartRequest;
import com.beetech.ec.tienichmuasam.entity.request.CartTransactionDeleteRequest;
import com.beetech.ec.tienichmuasam.entity.request.CartTransactionRequest;
import com.beetech.ec.tienichmuasam.entity.request.FavoriteProductRequest;
import com.beetech.ec.tienichmuasam.entity.request.FavoriteSupplierRequest;
import com.beetech.ec.tienichmuasam.entity.request.LoginRequest;
import com.beetech.ec.tienichmuasam.entity.request.DeleteRequest;
import com.beetech.ec.tienichmuasam.entity.response.BannerResponse;
import com.beetech.ec.tienichmuasam.entity.response.CartAmountResponse;
import com.beetech.ec.tienichmuasam.entity.response.CartDetailResponse;
import com.beetech.ec.tienichmuasam.entity.response.CartResponse;
import com.beetech.ec.tienichmuasam.entity.response.CategoryResponse;
import com.beetech.ec.tienichmuasam.entity.response.DetailProductResponse;
import com.beetech.ec.tienichmuasam.entity.response.DetailSupplierResponse;
import com.beetech.ec.tienichmuasam.entity.response.HomeProductResponse;
import com.beetech.ec.tienichmuasam.entity.response.HomeSupplierResponse;
import com.beetech.ec.tienichmuasam.entity.response.ItemMenuDetailSupplierResponse;
import com.beetech.ec.tienichmuasam.entity.response.LoginResponse;
import com.beetech.ec.tienichmuasam.entity.response.NotificationReadResponse;
import com.beetech.ec.tienichmuasam.entity.response.NotificationResponse;
import com.beetech.ec.tienichmuasam.entity.response.ProductFavoriteResponse;
import com.beetech.ec.tienichmuasam.entity.response.SearchProductResponse;
import com.beetech.ec.tienichmuasam.entity.response.SupplierFavoriteResponse;

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

    public Single<ObjectResponse<CartDetailResponse>> updateCartDetailWithAuth(String accessToken, CartTransactionRequest request) {
        return apiInterface.updateCartDetailWithAuth(accessToken, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<CartDetailResponse>> updateCartDetailNoAuth(CartTransactionRequest request) {
        return apiInterface.updateCartDetailNoAuth(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<CartDetailResponse>> deleteItemCartWithAuth(String accessToken, CartTransactionDeleteRequest request) {
        return apiInterface.deleteItemCartWithAuth(accessToken, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<CartDetailResponse>> deleteItemCartNoAuth(CartTransactionDeleteRequest request) {
        return apiInterface.deleteItemCartNoAuth(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ListResponse<NotificationResponse>> getNotificationWithAuth(String accessToken, HashMap<String, Object> data) {
        return apiInterface.getNotificationWithAuth(accessToken, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ListResponse<NotificationResponse>> getNotificationNoAuth(HashMap<String, Object> data) {
        return apiInterface.getNotificationNoAuth(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<NotificationReadResponse>> updateNotificationWithAuth(String accessToken, int notificationId, String deviceId) {
        return apiInterface.updateNotificationnWithAuth(accessToken, notificationId, deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ObjectResponse<NotificationReadResponse>> updateNotificationNoAuth(int notificationId, String deviceId) {
        return apiInterface.updateNotificationNoAuth(notificationId, deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
