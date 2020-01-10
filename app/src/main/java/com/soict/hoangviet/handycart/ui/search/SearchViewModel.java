package com.soict.hoangviet.handycart.ui.search;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.SearchProductResponse;
import com.soict.hoangviet.handycart.utils.Define;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SearchViewModel extends BaseViewModel {
    private Repository repository;
    private int pageIndex = 1;

    @Inject
    public SearchViewModel(CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference, Repository repository) {
        super(mCompositeDisposable, mSharePreference);
        this.repository = repository;
    }

    private MutableLiveData<String> keyWord;
    private MutableLiveData<ListLoadmoreReponse<SearchProductResponse>> search;
    private MutableLiveData<String> resultNumber;

    public MutableLiveData<String> getKeyWord() {
        if (keyWord == null) keyWord = new MutableLiveData<>();
        return keyWord;
    }

    public MutableLiveData<ListLoadmoreReponse<SearchProductResponse>> getSearch() {
        if (search == null) search = new MutableLiveData<>();
        return search;
    }

    public MutableLiveData<String> getResultNumber() {
        if (resultNumber == null) resultNumber = new MutableLiveData<>();
        return resultNumber;
    }

    public void onSearch(boolean isRefresh) {
        if (isRefresh) pageIndex = 1;
        HashMap<String, Object> data = new HashMap<>();
        if (getKeyWord().getValue() == null) {
            data.put(Define.Api.Query.KEYWORD_SEARCH, "");
        } else {
            data.put(Define.Api.Query.KEYWORD_SEARCH, getKeyWord().getValue());
        }
        data.put(Define.Api.Query.LIMIT, Define.Api.BaseResponse.DEFAULT_LIMIT);
        data.put(Define.Api.Query.PAGE, pageIndex);
        mCompositeDisposable.add(
                repository.getListSearchProduct(data)
                        .doOnSubscribe(disposable -> {
                            if (pageIndex == 1) {
                                getSearch().setValue(new ListLoadmoreReponse<SearchProductResponse>().loading());
                            }
                        })
                        .doFinally(() -> {
                        })
                        .subscribe(response -> {
                                    pageIndex++;
                                    getSearch().setValue(new ListLoadmoreReponse<SearchProductResponse>().success(
                                            response.getData(),
                                            isRefresh,
                                            pageIndex <= response.getTotalPage()
                                    ));
                                    if (getKeyWord().getValue() == null) {
                                        getResultNumber().setValue(
                                                "Có " + response.getTotalItem() + " kết quả tìm kiếm cho \"\"");
                                    } else {
                                        getResultNumber().setValue(
                                                "Có " + response.getTotalItem() + " kết quả tìm kiếm cho \"" + getKeyWord().getValue() + "\"");
                                    }
                                },
                                throwable -> {
                                    getSearch().setValue(new ListLoadmoreReponse<SearchProductResponse>().error(throwable));
                                })
        );
    }

    public void onCloseClick(View view) {
        getKeyWord().setValue("");
        getSearch().setValue(new ListLoadmoreReponse<SearchProductResponse>().success(
                new ArrayList<>(),
                true,
                false
        ));
    }
}
