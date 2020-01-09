package com.soict.hoangviet.handycart.ui.home;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.BannerResponse;
import com.soict.hoangviet.handycart.entity.HomeProductResponse;
import com.soict.hoangviet.handycart.entity.SearchResponse;
import com.soict.hoangviet.handycart.utils.Define;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HomeViewModel extends BaseViewModel {
    private Repository repository;
    private MutableLiveData<ListLoadmoreReponse<SearchResponse>> search = new MutableLiveData<>();
    private MutableLiveData<BannerResponse> listBanners = new MutableLiveData<>();
    private MutableLiveData<ListLoadmoreReponse<HomeProductResponse>> listHomeProduct;
    private int pageIndex = 1;

    @Inject
    public HomeViewModel(Repository repository, CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference) {
        super(mCompositeDisposable, mSharePreference);
        this.repository = repository;
    }

    public MutableLiveData<ListLoadmoreReponse<SearchResponse>> getSearch() {
        return search;
    }

    public MutableLiveData<BannerResponse> getListBanners() {
        if (listBanners == null) listBanners = new MutableLiveData<>();
        return listBanners;
    }

    public MutableLiveData<ListLoadmoreReponse<HomeProductResponse>> getListHomeProduct() {
        if (listHomeProduct == null) listHomeProduct = new MutableLiveData<>();
        return listHomeProduct;
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

    public void setListHomeProduct() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("category_id", Define.Api.BaseResponse.DEFAULT_INDEX);
        data.put("page", Define.Api.BaseResponse.DEFAULT_INDEX);
        data.put("limit", Define.Api.BaseResponse.LIMIT);
        mCompositeDisposable.add(
                repository.getListHomeProduct(data)
                        .doOnSubscribe(disposable -> {
                        })
                        .doFinally(() -> {

                        })
                        .subscribe(response -> {
                                    pageIndex++;
                                    getListHomeProduct().setValue(new ListLoadmoreReponse<HomeProductResponse>().success(
                                            response.getData(),
                                            false,
                                            pageIndex <= response.getTotalPage()
                                    ));
                                },
                                throwable -> {
                                    getListHomeProduct().setValue(new ListLoadmoreReponse<HomeProductResponse>().error(throwable));
                                })
        );
    }

    public void search(boolean isRefresh) {
        if (isRefresh) {
            pageIndex = 1;
        }
        mCompositeDisposable.add(
                repository.search(pageIndex)
                        .doOnSubscribe(disposable -> {
                            if (isRefresh) {
                                search.setValue(new ListLoadmoreReponse<SearchResponse>().loading());
                            }
                        })
                        .subscribe(
                                response -> {
                                    pageIndex++;
                                    search.setValue(new ListLoadmoreReponse<SearchResponse>().success(response.getData(), isRefresh,
                                            pageIndex <= response.getTotalPage()));
                                },
                                throwable -> {
                                    search.setValue(new ListLoadmoreReponse<SearchResponse>().error(throwable));
                                }
                        )
        );
    }
}
