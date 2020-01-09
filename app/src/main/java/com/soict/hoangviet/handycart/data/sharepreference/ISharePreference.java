package com.soict.hoangviet.handycart.data.sharepreference;

import android.content.SharedPreferences;

public interface ISharePreference {
    void setDeviceTokenId(String value);

    String getDeviceTokenId();

    void setLoginStatus(boolean isLogin);

    boolean isLogin();

}
