package com.example.jy.jieyou.manager;

import java.io.File;

/**
 * Created by DELL on 2018/12/24.
 */

public abstract class onNetCallbackListener implements onNetCallbackIListener {
    @Override
    public void onSuccess(String requestStr, String result) {

    }

    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onFinished() {

    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onSuccess(File mFile) {

    }
}
