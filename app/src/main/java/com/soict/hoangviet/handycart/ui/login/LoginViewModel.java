package com.soict.hoangviet.handycart.ui.login;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.utils.CommonExtensionUtil;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class LoginViewModel extends BaseViewModel {
    private Context context;
    private Repository repository;
    private MutableLiveData<String> validateLogin;
    private MutableLiveData<String> email;
    private MutableLiveData<String> password;

    @Inject
    public LoginViewModel(Context context, CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference) {
        super(mCompositeDisposable, mSharePreference);
        this.context = context;
        this.repository = repository;
    }

    public MutableLiveData<String> getValidateLogin() {
        if (validateLogin == null) {
            validateLogin = new MutableLiveData<>();
        }
        return validateLogin;
    }

    public MutableLiveData<String> getEmail() {
        if (email == null) {
            email = new MutableLiveData<>();
        }
        return email;
    }

    public MutableLiveData<String> getPassword() {
        if (password == null) {
            password = new MutableLiveData<>();
        }
        return password;
    }

    private void setOnErrorLogin(String error) {
        validateLogin.setValue(error);
    }

    public void onLoginClick(View view) {
        if (TextUtils.isEmpty(getEmail().getValue())) {
            validateLogin.setValue(context.getString(R.string.login_validate_email_empty));
            return;
        }
        if (!(CommonExtensionUtil.isValidEmail(getEmail().getValue()) || CommonExtensionUtil.isValidPhoneNumber(getEmail().getValue()))) {
            validateLogin.setValue(context.getString(R.string.login_validate_email_error));
            return;
        }
        if (TextUtils.isEmpty(getPassword().getValue())) {
            validateLogin.setValue(context.getString(R.string.login_validate_password_empty));
            return;
        }
        if (!CommonExtensionUtil.isValidPassword(getPassword().getValue())) {
            validateLogin.setValue(context.getString(R.string.login_validate_password_error));
            return;
        }
    }
}
