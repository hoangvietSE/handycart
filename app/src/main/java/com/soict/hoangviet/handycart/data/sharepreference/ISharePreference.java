package com.soict.hoangviet.handycart.data.sharepreference;

import android.content.SharedPreferences;

import com.soict.hoangviet.handycart.entity.response.LoginResponse;

public interface ISharePreference {
    void setDeviceTokenId(String value);

    String getDeviceTokenId();

    void setLoginStatus(boolean isLogin);

    boolean isLogin();

    void setLoginData(LoginResponse loginResponse);

    LoginResponse getLoginResponse();

    String getAccessToken();

    int getUserId();

    void clearAllPreference();

}
