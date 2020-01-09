package com.soict.hoangviet.handycart.data.network.repository;

import com.soict.hoangviet.handycart.base.ListResponse;
import com.soict.hoangviet.handycart.data.network.ApiInterface;
import com.soict.hoangviet.handycart.entity.BannerResponse;
import com.soict.hoangviet.handycart.entity.HomeProductResponse;
import com.soict.hoangviet.handycart.entity.HomeSupplierResponse;
import com.soict.hoangviet.handycart.entity.SearchResponse;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    private final ApiInterface apiInterface;

    @Inject
    Repository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Single<ListResponse<SearchResponse>> search(int pageIndex) {
        return apiInterface.search("h", pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BannerResponse> getListBanners() {
        return apiInterface.getListBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ListResponse<HomeProductResponse>> getListHomeProductNoAuth(HashMap<String, Object> data){
        return apiInterface.getListHomeProductNoAuth(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ListResponse<HomeSupplierResponse>> getListHomeSupplierNoAuth(HashMap<String, Object> data){
        return apiInterface.getListHomeSupplierNoAuth(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
