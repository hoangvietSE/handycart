package com.soict.hoangviet.handycart.ui.master;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListResponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.response.CategoryResponse;
import com.soict.hoangviet.handycart.utils.Define;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MasterViewModel extends BaseViewModel {
    private Repository repository;

    @Inject
    public MasterViewModel(Context context, ISharePreference mSharePreference, Repository repository) {
        super(context, mSharePreference);
        this.repository = repository;
    }

    private MutableLiveData<ListResponse<CategoryResponse>> listCategory;

    public MutableLiveData<ListResponse<CategoryResponse>> getListCategory() {
        if (listCategory == null) listCategory = new MutableLiveData<>();
        return listCategory;
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
