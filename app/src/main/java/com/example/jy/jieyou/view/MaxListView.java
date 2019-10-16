package com.example.jy.jieyou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.example.jy.jieyou.R;


/**
 * Created by DELL on 2019/4/20.
 */

public class MaxListView extends ListView {
    /**
     * listview高度
     */
    private int listViewHeight;

    public int getListViewHeight() {
        return listViewHeight;
    }

    public void setListViewHeight(int listViewHeight) {
        this.listViewHeight = listViewHeight;
    }

    public MaxListView(Context context) {
        super(context);
        listViewHeight = context.getResources().getDimensionPixelOffset(R.dimen.list_heghit);
    }

    public MaxListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        listViewHeight = context.getResources().getDimensionPixelOffset(R.dimen.list_heghit);
    }

    public MaxListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        listViewHeight = context.getResources().getDimensionPixelOffset(R.dimen.list_heghit);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        if (listViewHeight > -1) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(listViewHeight,
                    MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}


