package com.example.jy.jieyou.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by DELL on 2018/10/22.
 */

public class SFPopupWindow extends PopupWindow {

    public SFPopupWindow(Context context) {
        super(context, null);
    }

    public SFPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }
}
