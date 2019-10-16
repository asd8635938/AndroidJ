package com.example.jy.jieyou.utils;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by DELL on 2017/12/26.
 */

public class BaseDialog extends Dialog {
    private int res;
    public BaseDialog(Context context, int theme, int res) {
        super(context, theme);
        // TODO 自动生成的构造函数存根
        setContentView(res);
        this.res = res;
        setCanceledOnTouchOutside(true);
    }
}
