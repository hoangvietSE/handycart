package com.beetech.ec.tienichmuasam.ui.main;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.base.ListResponse;
import com.beetech.ec.tienichmuasam.data.network.repository.Repository;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;
import com.beetech.ec.tienichmuasam.entity.response.CategoryResponse;
import com.beetech.ec.tienichmuasam.utils.Define;

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
