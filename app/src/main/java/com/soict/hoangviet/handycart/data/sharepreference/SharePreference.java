package com.soict.hoangviet.handycart.data.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.soict.hoangviet.handycart.entity.response.LoginResponse;
import com.soict.hoangviet.handycart.utils.Define;

public class SharePreference implements ISharePreference {
    private Context context;

    public SharePreference(Context context) {
        this.context = context;
    }

    private SharedPreferences getSharePreference() {
        if (context != null) {
            return context.getSharedPreferences(Define.PREF_FILE_NAME, Context.MODE_PRIVATE);
        }
        return null;
    }

    private <T> String toJsonFromObject(T object) {
        String rawString = new Gson().toJson(object);
        return rawString;
    }

    private <T> T toGsonFromJson(String json, Class<T> tClass) {
        return new Gson().fromJson(json, tClass);
    }

    private void setString(String key, String value) {
        getSharePreference().edit().putString(key, value).apply();
    }

    private String getString(String key) {
        return getSharePreference().getString(key, "");
    }

    private void setBoolean(String key, boolean value) {
        getSharePreference().edit().putBoolean(key, value);
    }

    private boolean getBoolean(String key) {
        return getSharePreference().getBoolean(key, false);
    }

    @Override
    public void setDeviceTokenId(String deviceTokenId) {
        setString(Define.Api.Key.DEVICE_TOKEN_ID, deviceTokenId);
    }

    @Override
    public String getDeviceTokenId() {
        return getString(Define.Api.Key.DEVICE_TOKEN_ID);
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        setBoolean(Define.Api.Key.IS_LOGIN, isLogin);
    }

    @Override
    public boolean isLogin() {
        return getBoolean(Define.Api.Key.IS_LOGIN);
    }

    @Override
    public void setLoginData(LoginResponse loginResponse) {
        setString(Define.Api.Key.LOGIN_RESPONSE, toJsonFromObject(loginResponse));
    }

    @Override
    public LoginResponse getLoginResponse() {
        return toGsonFromJson(getString(Define.Api.Key.LOGIN_RESPONSE), LoginResponse.class);
    }
}
