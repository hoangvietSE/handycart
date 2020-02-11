package com.beetech.ec.tienichmuasam.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    private ToastUtil(){}
    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
