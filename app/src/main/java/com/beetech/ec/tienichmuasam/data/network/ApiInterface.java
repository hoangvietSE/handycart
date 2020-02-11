package com.beetech.ec.tienichmuasam.data.network;


import com.beetech.ec.tienichmuasam.base.BaseResponse;
import com.beetech.ec.tienichmuasam.base.ListLoadmoreReponse;
import com.beetech.ec.tienichmuasam.base.ListResponse;
import com.beetech.ec.tienichmuasam.base.ObjectResponse;
import com.beetech.ec.tienichmuasam.entity.request.CartRequest;
import com.beetech.ec.tienichmuasam.entity.request.CartTransactionDeleteRequest;
import com.beetech.ec.tienichmuasam.entity.request.CartTransactionRequest;
import com.beetech.ec.tienichmuasam.entity.request.DeleteRequest;
import com.beetech.ec.tienichmuasam.entity.request.FavoriteProductRequest;
import com.beetech.ec.tienichmuasam.entity.request.FavoriteSupplierRequest;
import com.beetech.ec.tienichmuasam.entity.request.LoginRequest;
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
import com.beetech.ec.tienichmuasam.utils.Define;

import java.util.HashMap;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    //Home
    @GET(ApiConstant.BANNER)
    Single<BannerResponse> getListBanners();

    @GET(ApiConstant.PRODUCT_CATEGORY)
    Single<ListResponse<HomeProductResponse>> getListHomeProductNoAuth(@QueryMap HashMap<String, Object> data);

    @GET(ApiConstant.SUPPLIER)
    Single<ListResponse<HomeSupplierResponse>> getListHomeSupplierNoAuth(@QueryMap HashMap<String, Object> data);

    @GET(ApiConstant.PRODUCT_CATEGORY)
    Single<ListResponse<HomeProductResponse>> getListHomeProductWithAuth(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @QueryMap HashMap<String, Object> data);

    @GET(ApiConstant.SUPPLIER)
    Single<ListResponse<HomeSupplierResponse>> getListHomeSupplierWithAuth(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @QueryMap HashMap<String, Object> data);

    @GET(ApiConstant.CATEGORY)
    Single<ListResponse<CategoryResponse>> getCategory(@Query(Define.Api.Query.LIMIT) int limit);


    //Search
    @GET(ApiConstant.SEARCH)
    Single<ListResponse<SearchProductResponse>> getListSearchProduct(@QueryMap HashMap<String, Object> data);

    @POST(ApiConstant.LOGIN)
    Single<ObjectResponse<LoginResponse>> login(@Body LoginRequest requestBody);

    //Favorite
    @GET(ApiConstant.PRODUCT_FAVORITE)
    Single<ListLoadmoreReponse<ProductFavoriteResponse>> getListProductFavorite(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @QueryMap HashMap<String, Object> data);

    @GET(ApiConstant.SUPPLIER_FAVORITE)
    Single<ListLoadmoreReponse<SupplierFavoriteResponse>> getListSupplierFavorite(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @QueryMap HashMap<String, Object> data);

    @POST(ApiConstant.LOGOUT)
    Completable logOut(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Path(Define.Api.Query.ID) int id,
            @Body DeleteRequest requestBody);

    //Favorite
    @POST(ApiConstant.PRODUCT_FAVORITE)
    Single<BaseResponse> addToFavorite(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Body FavoriteProductRequest favoriteProductRequest
    );

    @POST(ApiConstant.PRODUCT_FAVORITE)
    Single<BaseResponse> deleteFromFavorite(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Body FavoriteProductRequest favoriteProductRequest
    );

    @POST(ApiConstant.SUPPLIER_FAVORITE)
    Single<BaseResponse> addSupplierToFavorite(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Body FavoriteSupplierRequest favoriteSupplierRequest
    );

    @DELETE(ApiConstant.SUPPLIER_FAVORITE_DELETE)
    Single<BaseResponse> deleteSupplierFromFavorite(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Path(Define.Api.Query.ID) int id
    );

    @GET(ApiConstant.CART_AMOUNT)
    Single<ObjectResponse<CartAmountResponse>> getCartAmountNoAuth(
            @Query(Define.Api.Query.DEVICE_ID) String deviceId
    );

    @GET(ApiConstant.CART_AMOUNT)
    Single<ObjectResponse<CartAmountResponse>> getCartAmountWithAuth(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Query(Define.Api.Query.DEVICE_ID) String deviceId
    );

    @POST(ApiConstant.CART)
    Single<ObjectResponse<CartResponse>> addToCartNoAuth(
            @Body CartRequest cartRequest
    );

    @POST(ApiConstant.CART)
    Single<ObjectResponse<CartResponse>> addToCartWithAuth(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Body CartRequest cartRequest
    );

    @GET(ApiConstant.PRODUCT)
    Single<ObjectResponse<DetailProductResponse>> getDetailProduct(
            @Path(Define.Api.Query.ID) int productId
    );

    @GET(ApiConstant.CART)
    Single<ObjectResponse<CartDetailResponse>> getCartDetailWithAuth(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Query(Define.Api.Query.DEVICE_ID) String deviceId
    );

    @GET(ApiConstant.CART)
    Single<ObjectResponse<CartDetailResponse>> getCartDetailNoAuth(
            @Query(Define.Api.Query.DEVICE_ID) String deviceId
    );

    @GET(ApiConstant.DETAIL_SUPPLIER)
    Single<ObjectResponse<DetailSupplierResponse>> getDetailSupplier(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Path(Define.Api.Query.ID) int id
    );

    @GET(ApiConstant.MENU_PRODUCT)
    Single<ListResponse<ItemMenuDetailSupplierResponse>> getMenuProduct(
            @Path(Define.Api.Query.ID) int id,
            @Query(Define.Api.Query.PAGE) int page
    );

    @PUT(ApiConstant.CART)
    Single<ObjectResponse<CartDetailResponse>> updateCartDetailWithAuth(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Body CartTransactionRequest request
    );

    @PUT(ApiConstant.CART)
    Single<ObjectResponse<CartDetailResponse>> updateCartDetailNoAuth(
            @Body CartTransactionRequest request
    );

    @HTTP(method = "DELETE", path = ApiConstant.CART, hasBody = true)
    Single<ObjectResponse<CartDetailResponse>> deleteItemCartWithAuth(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Body CartTransactionDeleteRequest request
    );

    @HTTP(method = "DELETE", path = ApiConstant.CART, hasBody = true)
    Single<ObjectResponse<CartDetailResponse>> deleteItemCartNoAuth(
            @Body CartTransactionDeleteRequest request
    );

    @GET(ApiConstant.NOTIFICATIONS)
    Single<ListResponse<NotificationResponse>> getNotificationWithAuth(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @QueryMap HashMap<String, Object> data
    );

    @GET(ApiConstant.NOTIFICATIONS)
    Single<ListResponse<NotificationResponse>> getNotificationNoAuth(
            @QueryMap HashMap<String, Object> data
    );

    @GET(ApiConstant.NOTIFICATIONS_READ)
    Single<ObjectResponse<NotificationReadResponse>> updateNotificationnWithAuth(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Path(Define.Api.Query.ID) int notificationId,
            @Query(Define.Api.Query.DEVICE_ID) String deviceId
    );

    @GET(ApiConstant.NOTIFICATIONS_READ)
    Single<ObjectResponse<NotificationReadResponse>> updateNotificationNoAuth(
            @Path(Define.Api.Query.ID) int notificationId,
            @Query(Define.Api.Query.DEVICE_ID) String deviceId
    );
}
