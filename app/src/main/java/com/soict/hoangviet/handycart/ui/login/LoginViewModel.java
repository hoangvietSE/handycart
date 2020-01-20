package com.soict.hoangviet.handycart.ui.login;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ObjectResponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.data.sharepreference.SharePreference;
import com.soict.hoangviet.handycart.entity.request.LoginRequest;
import com.soict.hoangviet.handycart.entity.response.LoginResponse;
import com.soict.hoangviet.handycart.utils.CommonExtensionUtil;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class LoginViewModel extends BaseViewModel {
    private Context context;
    private Repository repository;
    private MutableLiveData<String> validateLoginEmail;
    private MutableLiveData<String> validateLoginPassword;
    private MutableLiveData<String> email;
    private MutableLiveData<String> password;
    private MutableLiveData<ObjectResponse<LoginResponse>> login;

    @Inject
    public LoginViewModel(Context context, Repository repository, CompositeDisposable mCompositeDisposable, ISharePreference mSharePreference) {
        super(mCompositeDisposable, mSharePreference);
        this.context = context;
        this.repository = repository;
    }

    public MutableLiveData<String> getValidateLoginEmail() {
        if (validateLoginEmail == null) {
            validateLoginEmail = new MutableLiveData<>();
        }
        return validateLoginEmail;
    }

    public MutableLiveData<String> getValidateLoginPassword() {
        if (validateLoginPassword == null) {
            validateLoginPassword = new MutableLiveData<>();
        }
        return validateLoginPassword;
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

    public MutableLiveData<ObjectResponse<LoginResponse>> getLogin() {
        if (login == null) login = new MutableLiveData<>();
        return login;
    }

    public void setLogin(MutableLiveData<ObjectResponse<LoginResponse>> login) {
        this.login = login;
    }

    public void onLoginClick(View view) {
        if (TextUtils.isEmpty(getEmail().getValue())) {
            validateLoginEmail.setValue(context.getString(R.string.login_validate_email_empty));
            return;
        }
        if (!(CommonExtensionUtil.isValidEmail(getEmail().getValue()) || CommonExtensionUtil.isValidPhoneNumber(getEmail().getValue()))) {
            validateLoginEmail.setValue(context.getString(R.string.login_validate_email_error));
            return;
        }
        if (TextUtils.isEmpty(getPassword().getValue())) {
            validateLoginPassword.setValue(context.getString(R.string.login_validate_password_empty));
            return;
        }
        if (!CommonExtensionUtil.isValidPassword(getPassword().getValue())) {
            validateLoginPassword.setValue(context.getString(R.string.login_validate_password_error));
            return;
        }
        LoginRequest loginRequest = new LoginRequest(email.getValue(), password.getValue());
        repository.login(loginRequest)
                .doOnSubscribe(disposable -> {
                    getLogin().setValue(new ObjectResponse<LoginResponse>().loading());
                })
                .doFinally(() -> {
                })
                .subscribe(
                        response -> {
                            mSharePreference.setLoginData(response.getData());
                            getLogin().setValue(new ObjectResponse<LoginResponse>().success(response.getData()));
                        },
                        throwable -> {
                            getLogin().setValue(new ObjectResponse<LoginResponse>().error(throwable));
                        }
                );
    }
}
