package com.soict.hoangviet.handycart.data.network;


import com.soict.hoangviet.handycart.base.BaseResponse;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.base.ListResponse;
import com.soict.hoangviet.handycart.base.ObjectResponse;
import com.soict.hoangviet.handycart.entity.request.CartRequest;
import com.soict.hoangviet.handycart.entity.request.CartTransactionDeleteRequest;
import com.soict.hoangviet.handycart.entity.request.CartTransactionRequest;
import com.soict.hoangviet.handycart.entity.request.DeleteRequest;
import com.soict.hoangviet.handycart.entity.request.FavoriteProductRequest;
import com.soict.hoangviet.handycart.entity.request.FavoriteSupplierRequest;
import com.soict.hoangviet.handycart.entity.request.LoginRequest;
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
import com.soict.hoangviet.handycart.entity.response.NotificationResponse;
import com.soict.hoangviet.handycart.entity.response.ProductFavoriteResponse;
import com.soict.hoangviet.handycart.entity.response.SearchProductResponse;
import com.soict.hoangviet.handycart.entity.response.SupplierFavoriteResponse;
import com.soict.hoangviet.handycart.utils.Define;

import java.util.HashMap;

import dagger.Module;
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
}
