package com.soict.hoangviet.handycart.data.network;


import com.soict.hoangviet.handycart.base.ListResponse;
import com.soict.hoangviet.handycart.base.ObjectResponse;
import com.soict.hoangviet.handycart.entity.response.BannerResponse;
import com.soict.hoangviet.handycart.entity.response.CategoryResponse;
import com.soict.hoangviet.handycart.entity.response.HomeProductResponse;
import com.soict.hoangviet.handycart.entity.response.HomeSupplierResponse;
import com.soict.hoangviet.handycart.entity.response.LoginResponse;
import com.soict.hoangviet.handycart.entity.response.SearchProductResponse;
import com.soict.hoangviet.handycart.utils.Define;

import java.util.HashMap;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET(ApiConstant.BANNER)
    @Headers("Content-Type: application/json")
    Single<BannerResponse> getListBanners();

    @GET(ApiConstant.PRODUCT_CATEGORY)
    @Headers("Content-Type: application/json")
    Single<ListResponse<HomeProductResponse>> getListHomeProductNoAuth(@QueryMap HashMap<String, Object> data);

    @GET(ApiConstant.SUPPLIER)
    @Headers("Content-Type: application/json")
    Single<ListResponse<HomeSupplierResponse>> getListHomeSupplierNoAuth(@QueryMap HashMap<String, Object> data);

    @GET(ApiConstant.SEARCH)
    @Headers("Content-Type: application/json")
    Single<ListResponse<SearchProductResponse>> getListSearchProduct(@QueryMap HashMap<String, Object> data);

    @GET(ApiConstant.CATEGORY)
    @Headers("Content-Type: application/json")
    Single<ListResponse<CategoryResponse>> getCategory(@Query(Define.Api.Query.LIMIT) int limit);

    @POST(ApiConstant.LOGIN)
    @Headers("Content-Type: application/json")
    Single<ObjectResponse<LoginResponse>> login(@Body RequestBody requestBody);

}
