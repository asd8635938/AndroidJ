package com.example.jy.jieyou.utils;

import android.content.Context;

import com.android.tu.loadingdialog.LoadingDialog;

/**
 * Created by DELL on 2019/4/18.
 */

public class Loading {
    public static LoadingDialog dialog;

    public static void showDialog(Context context, String message) {
        if (message.isEmpty()) {
            message = "加载中...";
        }
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        LoadingDialog.Builder builder = new LoadingDialog.Builder(context)
                .setMessage(message)
                .setCancelOutside(false)
                .setCancelable(true);
        dialog = builder.create();
        dialog.show();
    }

    public static void dismissDialog() {
        if (dialog == null) {
            return;
        }
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
