package com.example.jy.jieyou.phone;

import java.io.Serializable;

public class SortModel implements Serializable{

    private String name; //联系人姓名
    private String sortLetters; // 显示数据拼音的首字母
    private String telPhone;    //电话号码
    private boolean isClick = false;
    private int numb;

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getTelPhone() {
        return telPhone == null ? "" : telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone == null ? "" : telPhone;
    }

    public SortModel() {
    }

    public SortModel(String name, String telPhone) {
        this.name = name;
        this.telPhone = telPhone;
    }
}
