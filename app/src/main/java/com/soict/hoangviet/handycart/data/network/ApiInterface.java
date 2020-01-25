package com.soict.hoangviet.handycart.data.network;


import com.soict.hoangviet.handycart.base.BaseResponse;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.base.ListResponse;
import com.soict.hoangviet.handycart.base.ObjectResponse;
import com.soict.hoangviet.handycart.entity.request.DeleteRequest;
import com.soict.hoangviet.handycart.entity.request.LoginRequest;
import com.soict.hoangviet.handycart.entity.response.BannerResponse;
import com.soict.hoangviet.handycart.entity.response.CategoryResponse;
import com.soict.hoangviet.handycart.entity.response.HomeProductResponse;
import com.soict.hoangviet.handycart.entity.response.HomeSupplierResponse;
import com.soict.hoangviet.handycart.entity.response.LoginResponse;
import com.soict.hoangviet.handycart.entity.response.ProductFavoriteResponse;
import com.soict.hoangviet.handycart.entity.response.SearchProductResponse;
import com.soict.hoangviet.handycart.entity.response.SupplierFavoriteResponse;
import com.soict.hoangviet.handycart.utils.Define;

import java.util.HashMap;

import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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
            @Body RequestBody requestBody
    );

    @POST(ApiConstant.PRODUCT_FAVORITE)
    Single<BaseResponse> deleteFromFavorite(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Body RequestBody requestBody
    );

    @POST(ApiConstant.SUPPLIER_FAVORITE)
    Single<BaseResponse> addSupplierToFavorite(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Body RequestBody requestBody
    );

    @DELETE(ApiConstant.SUPPLIER_FAVORITE_DELETE)
    Single<BaseResponse> deleteSupplierFromFavorite(
            @Header(Define.Api.Query.AUTHORIZATION) String accessToken,
            @Path(Define.Api.Query.ID) int id
    );

}
