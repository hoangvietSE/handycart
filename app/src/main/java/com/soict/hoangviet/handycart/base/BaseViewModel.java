package com.soict.hoangviet.handycart.base;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {
    protected Context context;
    protected CompositeDisposable mCompositeDisposable;
    protected ISharePreference mSharePreference;

    public BaseViewModel(Context context, ISharePreference mSharePreference) {
        this.mCompositeDisposable = new CompositeDisposable();
        this.mSharePreference = mSharePreference;
        this.context = context;
    }

    public ISharePreference getmSharePreference() {
        return mSharePreference;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
    }
}
