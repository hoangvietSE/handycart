package com.soict.hoangviet.handycart.ui.main;

import android.content.Context;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainViewModel extends BaseViewModel {

    @Inject
    public MainViewModel(Context context, ISharePreference mSharePreference) {
        super(context, mSharePreference);
    }
}
