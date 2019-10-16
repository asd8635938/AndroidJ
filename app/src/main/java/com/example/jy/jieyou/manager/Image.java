package com.example.jy.jieyou.manager;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.jy.jieyou.R;

import java.io.File;

/**
 * Created by DELL on 2019/5/15.
 */

public class Image {
    /**
     * 图像类
     */
    public static void image(Context mContext, String url, int err, ImageView mImageView) {
        RequestOptions mRequestOptions = new RequestOptions();
        mRequestOptions.error(err).dontAnimate().dontTransform();
        Glide.with(mContext).load(url).apply(mRequestOptions).into(mImageView);
    }

    public static void errImage(Context mContext, String url, int err,ImageView mImageView) {
        RequestOptions mRequestOptions = new RequestOptions();
        mRequestOptions.error(err).placeholder(R.mipmap.test_1).fallback(R.mipmap.test_1).dontAnimate().dontTransform();
        Glide.with(mContext).load(url).apply(mRequestOptions).into(mImageView);
    }

    public static void image(Context mContext, String url, ImageView mImageView) {
        RequestOptions mRequestOptions = new RequestOptions().dontAnimate();
        Glide.with(mContext).load(url).apply(mRequestOptions).into(mImageView);
    }

    public static void image(Context mContext, int url, ImageView mImageView) {
        RequestOptions mRequestOptions = new RequestOptions().dontAnimate();
        Glide.with(mContext).load(url).apply(mRequestOptions).into(mImageView);
    }

    public static void imageCenterCrop(Context mContext, String url, ImageView mImageView) {
        RequestOptions mRequestOptions = new RequestOptions().dontAnimate().centerCrop();
        Glide.with(mContext).load(url).apply(mRequestOptions).into(mImageView);
    }

    public static void image(Context mContext, File url, ImageView mImageView) {
        RequestOptions mRequestOptions = new RequestOptions().dontAnimate();
        Glide.with(mContext).load(url).apply(mRequestOptions).into(mImageView);
    }

    public static void imageCirle(Context mContext, String url, int err, ImageView mImageView) {
        RequestOptions mRequestOptions = new RequestOptions();
        mRequestOptions.circleCrop().dontAnimate().error(err);
        Glide.with(mContext).load(url).apply(mRequestOptions).into(mImageView);
    }

    public static void imageCirle(Context mContext, String url, int placeholder, int err, ImageView mImageView) {
        RequestOptions mRequestOptions = new RequestOptions();
        mRequestOptions.circleCrop().dontAnimate().error(err).placeholder(placeholder);
        Glide.with(mContext).load(url).apply(mRequestOptions).into(mImageView);
    }

    public static void imageCirle(Context mContext, int head, ImageView mImageView) {
        RequestOptions mRequestOptions = new RequestOptions();
        mRequestOptions.circleCrop().dontAnimate();
        Glide.with(mContext).load(head).apply(mRequestOptions).into(mImageView);
    }

    public static void imageRoundRect(Context mContext, String url, int err, ImageView mImageView, int round) {
        RequestOptions mRequestOptions = new RequestOptions();
        mRequestOptions.error(err).dontAnimate().centerCrop();
        if (round > 0) {
            mRequestOptions = mRequestOptions.transform(new RoundedCorners(round));
        }
        Glide.with(mContext).load(url).apply(mRequestOptions).into(mImageView);
    }

    /**
     * 释放Glide持有资源
     *
     * @param mContext
     * @param mImageView
     */
    public static void release(Context mContext, ImageView mImageView) {
        Glide.with(mContext).clear(mImageView);
    }
}
