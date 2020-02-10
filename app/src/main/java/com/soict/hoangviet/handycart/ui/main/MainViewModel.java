package com.soict.hoangviet.handycart.ui.main;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListResponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.response.CategoryResponse;
import com.soict.hoangviet.handycart.utils.Define;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {
    private Repository repository;

    @Inject
    public MainViewModel(Context context, ISharePreference mSharePreference, Repository repository) {
        super(context, mSharePreference);
        this.repository = repository;
    }

    private MutableLiveData<ListResponse<CategoryResponse>> listCategory;
    private MutableLiveData<Boolean> notificationBadge;

    public MutableLiveData<ListResponse<CategoryResponse>> getListCategory() {
        if (listCategory == null) listCategory = new MutableLiveData<>();
        return listCategory;
    }

    public MutableLiveData<Boolean> getNotificationBadge() {
        if (notificationBadge == null) notificationBadge = new MutableLiveData<>();
        return notificationBadge;
    }

    public void setListCategory() {
        mCompositeDisposable.add(
                repository.getCategory(Define.Api.BaseResponse.DEFAULT_LIMIT)
                        .doOnSubscribe(disposable -> {
                        })
                        .doFinally(() -> {
                        })
                        .subscribe(response -> {
                                    getListCategory().setValue(response);
                                },
                                throwable -> {
                                })
        );
    }
}
