package com.soict.hoangviet.handycart.utils;

import android.content.Context;
import android.widget.Toast;

import com.soict.hoangviet.handycart.application.BaseApplication;

import javax.inject.Inject;

public class ToastUtil {
    private ToastUtil(){}
    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
