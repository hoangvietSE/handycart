package com.soict.hoangviet.handycart.data.network;


import com.soict.hoangviet.handycart.base.ListResponse;
import com.soict.hoangviet.handycart.entity.BannerResponse;
import com.soict.hoangviet.handycart.entity.CategoryResponse;
import com.soict.hoangviet.handycart.entity.HomeProductResponse;
import com.soict.hoangviet.handycart.entity.HomeSupplierResponse;
import com.soict.hoangviet.handycart.entity.SearchProductResponse;
import com.soict.hoangviet.handycart.utils.Define;

import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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

}
