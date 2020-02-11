package com.beetech.ec.tienichmuasam.data.sharepreference;

import com.beetech.ec.tienichmuasam.entity.response.LoginResponse;

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

    String getCurrentLanguage();

    void setCurrentLanguage(String codeLocale);

}
