package com.beetech.ec.tienichmuasam.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.databinding.DialogLoadingBinding;


public class LoadingUtil {
    private LoadingUtil() {
    }

    private static boolean shown = false;

    private AlertDialog dialog = null;

    private DialogLoadingBinding binding;

    private static LoadingUtil instance = null;

    private Context context;

    public static LoadingUtil getInstance(Context context) {
        instance = new LoadingUtil(context);
        return instance;
    }

    private LoadingUtil(Context context) {
        this.context = context;
        if (context != null && !LoadingUtil.isShown()) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_loading, null, false);
            View dialogView = binding.getRoot();
            dialogBuilder.setView(dialogView);
            dialogBuilder.setCancelable(false);
            dialog = dialogBuilder.create();
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener((dialog, keyCode, event) -> {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // we cannot close dialog when we press back button
                }
                return false;
            });
        }
    }

    public void show() {
        if (!((Activity) context).isFinishing()) {
            if (!LoadingUtil.isShown() && dialog != null) {
                forceShown();
                dialog.show();
            }
        }
    }

    public void hidden() {
        if (LoadingUtil.isShown() && dialog != null && dialog.isShowing()) {
            initialize();
            dialog.dismiss();
        }
    }

    private static boolean isShown() {
        return shown;
    }

    private static void forceShown() {
        shown = true;
    }

    private static void initialize() {
        shown = false;
    }

    public void destroyLoadingDialog() {
        instance = null;
    }
}
