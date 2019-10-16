package com.example.jy.jieyou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jy.jieyou.R;
import com.example.jy.jieyou.utils.DateUtils;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by jhf on 2019/6/27.
 */

public class LoadingHeader extends FrameLayout implements PtrUIHandler {

    private TextView tvLoading,tvLoaded;
    private View view;
    private ProgressDrawable progressDrawable;
    private ImageView mArrowView, mProgressView;

    public LoadingHeader(Context context) {
        this(context, null);
    }

    public LoadingHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_loading_head, this, false);
        tvLoading = view.findViewById(R.id.srl_classics_title);
        tvLoaded = view.findViewById(R.id.srl_classics_update);
        mArrowView = view.findViewById(R.id.srl_classics_arrow);
        mProgressView = view.findViewById(R.id.srl_classics_progress);
        ArrowDrawable arrowDrawable = new ArrowDrawable();
        mArrowView.setImageDrawable(arrowDrawable);
        progressDrawable = new ProgressDrawable();
        mProgressView.setImageDrawable(progressDrawable);
        addView(view);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        //重置
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        //准备刷新
        if (tvLoaded != null) {
            long time = System.currentTimeMillis();
            tvLoaded.setText("刷新时间  " + DateUtils.getDateToString(time, "MM-dd HH:mm"));
        }
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        //开始刷新 显示刷新进度跟文本
        try {
            tvLoading.setText("正在刷新...");
            mArrowView.setVisibility(View.GONE);
            mProgressView.setVisibility(View.VISIBLE);
            progressDrawable.start();
        } catch (Exception e) {
        }
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        //刷新完成  设置文本 设置进度隐藏
        try {
            tvLoading.setText("刷新完成");
            if (mArrowView != null && mProgressView != null) {
                mArrowView.setVisibility(View.GONE);
                mProgressView.setVisibility(View.GONE);
            }
            if (progressDrawable != null) {
                progressDrawable.stop();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();
        if (currentPos < mOffsetToRefresh) {
            //未到达刷新线
            if (status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                tvLoading.setText("下拉可以刷新");
                mArrowView.setVisibility(View.VISIBLE);
            }
        } else if (currentPos > mOffsetToRefresh) {
            //到达或超过刷新线
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                tvLoading.setText("释放立即刷新");
            }
        }
    }

    public void setHeadColor(int color) {
        view.setBackgroundColor(color);
    }
}
