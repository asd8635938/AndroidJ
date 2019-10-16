package com.example.jy.jieyou.manager;

import java.io.File;

/**
 * Created by DELL on 2018/9/3.
 */

public interface onNetCallbackIListener {
    /**
     * 返回网络请求数据
     *
     * @param requestStr 请求发出数据
     * @param result     请求结果
     */
    public void onSuccess(String requestStr, String result);

    public void onSuccess(String result);

    public void onError(Throwable throwable);

    public void onFinished();

    public void onCancelled();
    public void onSuccess(File mFile);
}
