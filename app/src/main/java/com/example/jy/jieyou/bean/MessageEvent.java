package com.example.jy.jieyou.bean;

import com.example.jy.jieyou.phone.SortModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhf on 2019/10/17.
 */

public class MessageEvent {

    private  List<SortModel> mResult;

    public List<SortModel> getmResult() {
        if (mResult == null) {
            return new ArrayList<>();
        }
        return mResult;
    }

    public void setmResult(List<SortModel> mResult) {
        this.mResult = mResult;
    }
}
