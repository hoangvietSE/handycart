package com.soict.hoangviet.handycart.data.network;


import com.soict.hoangviet.handycart.base.ListResponse;
import com.soict.hoangviet.handycart.entity.BannerResponse;
import com.soict.hoangviet.handycart.entity.SearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search")
    @Headers({"lang: vi", "Content-Type: application/json"})
    Single<ListResponse<SearchResponse>> search(@Query("s") String keyword,
                                                @Query("page") int pageIndex);

    @GET(ApiConstant.banner)
    @Headers("Content-Type: application/json")
    Single<BannerResponse> getListBanners();
}
