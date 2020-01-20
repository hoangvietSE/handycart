package com.soict.hoangviet.handycart.ui.profile;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.utils.ToastUtil;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ProfileViewModel extends BaseViewModel {

    private Repository repository;
    private MutableLiveData<String> isLogin;

    @Inject
    public ProfileViewModel(CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference, Repository repository) {
        super(mCompositeDisposable, mSharePreference);
        this.repository = repository;
    }

    public MutableLiveData<String> getIsLogin() {
        if(isLogin==null)isLogin = new MutableLiveData<>();
        return isLogin;
    }

    public boolean isLogin(){
        if(mSharePreference.isLogin()){
            return true;
        }else{
            return false;
        }
    }

    public void loginFunction(){
        if(isLogin()){
            getIsLogin().setValue("Đăng xuất");
        }else{
            getIsLogin().setValue("Đăng nhập");
        }
    }

    public void onRowClick(View view){
        switch (view.getId()){
            case R.id.row_login:

        }
    }
}
