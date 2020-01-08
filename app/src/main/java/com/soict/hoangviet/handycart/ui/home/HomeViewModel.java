package com.soict.hoangviet.handycart.ui.home;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.BannerResponse;
import com.soict.hoangviet.handycart.entity.SearchResponse;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HomeViewModel extends BaseViewModel {
    private Repository repository;
    private MutableLiveData<ListLoadmoreReponse<SearchResponse>> search = new MutableLiveData<>();
    private MutableLiveData<BannerResponse> listBanners = new MutableLiveData<>();
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

    public void setListBanners() {
        mCompositeDisposable.add(
                repository.getListBanners()
                        .doOnSubscribe(disposable -> {
                        })
                        .subscribe(
                                bannerResponse -> {
                                    listBanners.setValue(bannerResponse);
                                },
                                throwable -> {

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
