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

    private SharedPreferences getSharePreference(String fileName) {
        if (context != null) {
            return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        return null;
    }

    private <T> String toJsonFromObject(T object) {
        String rawString = new Gson().toJson(object);
        return rawString;
    }

    private <T> T toGsonFromJson(String json, Class<T> anonymousClass) {
        return new Gson().fromJson(json, anonymousClass);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String fileName, String key, Class<T> anonymousClass) {
        if (anonymousClass == String.class) {
            return (T) getSharePreference(fileName).getString(key, "");
        } else if (anonymousClass == Boolean.class) {
            return (T) Boolean.valueOf(getSharePreference(fileName).getBoolean(key, false));
        } else if (anonymousClass == Float.class) {
            return (T) Float.valueOf(getSharePreference(fileName).getFloat(key, 0));
        } else if (anonymousClass == Integer.class) {
            return (T) Integer.valueOf(getSharePreference(fileName).getInt(key, 0));
        } else if (anonymousClass == Long.class) {
            return (T) Long.valueOf(getSharePreference(fileName).getLong(key, 0));
        } else {
            return toGsonFromJson(getSharePreference(fileName).getString(key, ""), anonymousClass);
        }
    }

    public <T> void put(String fileName, String key, T data) {
        SharedPreferences.Editor editor = getSharePreference(fileName).edit();
        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        } else {
            editor.putString(key, toJsonFromObject(data));
        }
        editor.apply();
    }

    @Override
    public void setDeviceTokenId(String deviceTokenId) {
        put(Define.PREF_DEVICE, Define.Api.Key.DEVICE_TOKEN_ID, deviceTokenId);
    }

    @Override
    public String getDeviceTokenId() {
        return get(Define.PREF_DEVICE, Define.Api.Key.DEVICE_TOKEN_ID, String.class);
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        put(Define.PREF_FILE_NAME, Define.Api.Key.IS_LOGIN, isLogin);
    }

    @Override
    public boolean isLogin() {
        return get(Define.PREF_FILE_NAME, Define.Api.Key.IS_LOGIN, Boolean.class);
    }

    @Override
    public void setLoginData(LoginResponse loginResponse) {
        put(Define.PREF_FILE_NAME, Define.Api.Key.LOGIN_RESPONSE, loginResponse);
        setLoginStatus(true);
    }

    @Override
    public LoginResponse getLoginResponse() {
        return get(Define.PREF_FILE_NAME, Define.Api.Key.LOGIN_RESPONSE, LoginResponse.class);
    }

    @Override
    public String getAccessToken() {
        if (getLoginResponse() != null) {
            return Define.Api.Query.BEARER + getLoginResponse().getAccessToken();
        } else {
            return "";
        }
    }

    @Override
    public int getUserId() {
        if (getLoginResponse() != null) {
            return getLoginResponse().getId();
        }
        return -1;
    }

    @Override
    public void clearAllPreference() {
        getSharePreference(Define.PREF_FILE_NAME).edit().clear().apply();
    }

    @Override
    public String getCurrentLanguage() {
        return get(Define.PREF_LANGUAGE, Define.Api.Key.LANGUAGE, String.class);
    }

    @Override
    public void setCurrentLanguage(String codeLocale) {
        put(Define.PREF_LANGUAGE, Define.Api.Key.LANGUAGE, codeLocale);
    }
}
