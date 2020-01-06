package com.soict.hoangviet.handycart.data.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.soict.hoangviet.handycart.utils.Define;

public class SharePreference implements ISharePreference {
    private Context context;
    private SharedPreferences mPrefs = getSharePreference();

    public SharePreference(Context context) {
        this.context = context;
    }

    @Override
    public SharedPreferences getSharePreference() {
        if(context!=null){
            return context.getSharedPreferences(Define.PREF_FILE_NAME, Context.MODE_PRIVATE);
        }
        return null;
    }
}
