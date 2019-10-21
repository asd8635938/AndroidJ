package com.example.jy.jieyou.request;

import com.example.jy.jieyou.utils.MD5Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jhf on 2019/10/18.
 */

public class Request {

//    private String action ="send";
    private String userName = "dxqf";
    private String passWord = "dxqf2005";
    private String timestamp = getTime14();  // 时间戳
    private String userid;  // 企业ID
    private String sign = MD5Utils.textToMD5L32(userName + passWord + timestamp);
    private String mobile; // 全部被叫号码 发信发送的目的号码.多个号码之间用半角逗号隔开
    private String content; // 发送内容
    private String sendTime; // 定时发送时间  为空表示立即发送，定时发送格式2010-10-24 09:08:10
    private String extno; // 请先询问配置的通道是否支持扩展子号，如果不支持，请填空。子号只能为数字，且最多10位数。

//    public String getAction() {
//        return action == null ? "" : action;
//    }
//
//    public void setAction(String action) {
//        this.action = action == null ? "" : action;
//    }

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? "" : userName;
    }

    public String getPassWord() {
        return passWord == null ? "" : passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? "" : passWord;
    }

    public String getUserid() {
        return userid == null ? "" : userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? "" : userid;
    }

    public String getTimestamp() {
        return timestamp == null ? "" : timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp == null ? "" : timestamp;
    }

    public String getSign() {
        return sign == null ? "" : sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? "" : sign;
    }

    public String getMobile() {
        return mobile == null ? "" : mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? "" : mobile;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content;
    }

    public String getSendTime() {
        return sendTime == null ? "" : sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? "" : sendTime;
    }

    public String getExtno() {
        return extno == null ? "" : extno;
    }

    public void setExtno(String extno) {
        this.extno = extno == null ? "" : extno;
    }

    /**
     * 获得当前时间，格式：yyyyMMddHHmmss
     * */
    public String getTime14() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String currtime = formatter.format(new Date());
        return currtime;
    }
}
