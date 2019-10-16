package com.example.jy.jieyou.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created by DELL on 2018/9/12.
 */

public class Utils {

    private static Toast toast;

    public static void Toash(Context context, CharSequence message) {
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
            toast.setGravity(17, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    public static int getDeviceWidth(Context context) {
        @SuppressLint("WrongConstant") WindowManager manager = (WindowManager) context.getSystemService("window");
        return manager.getDefaultDisplay().getWidth();
    }

    public static int getDeviceHeight(Context context) {
        @SuppressLint("WrongConstant") WindowManager manager = (WindowManager) context.getSystemService("window");
        return manager.getDefaultDisplay().getHeight();
    }

    /**
     * 方法名:         calcFloatValue
     * 方法功能描述:   float类型的加减乘除，避免Java 直接计算错误
     *
     * @param: type:   加：add   减：  subtract  乘：multiply 除：divide
     */
    public static float calcFloatValue(float t1, float t2, String type) {
        BigDecimal a = new BigDecimal(String.valueOf(t1));
        BigDecimal b = new BigDecimal(String.valueOf(t2));
        float retValue = 0f;
        switch (type) {
            case "add":
                retValue = a.add(b).floatValue();
                break;
            case "subtract":
                retValue = a.subtract(b).floatValue();
                break;
            case "multiply":
                retValue = a.multiply(b).floatValue();
                break;
            case "divide":
                retValue = a.divide(b).floatValue();
                break;
        }
        return retValue;
    }

    /**
     * 方法名:         calcDoubleValue
     * 方法功能描述:   double类型的加减乘除，避免Java 直接计算错误
     */
    public static double calcDoubleValue(double t1, double t2, String type) {
        BigDecimal a = new BigDecimal(String.valueOf(t1));
        BigDecimal b = new BigDecimal(String.valueOf(t2));
        double retValue = 0f;
        switch (type) {
            case "add":
                retValue = a.add(b).doubleValue();
                break;
            case "subtract":
                retValue = a.subtract(b).doubleValue();
                break;
            case "multiply":
                retValue = a.multiply(b).doubleValue();
                break;
            case "divide":
                retValue = a.divide(b).doubleValue();
                break;
        }
        return retValue;
    }

    /**
     * 判断整数（int）
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断浮点数（double和float）
     */
    public static boolean isDouble(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 获取屏幕的宽度
     *
     * @return
     */
    public static int getWidth(Context ctx) {
        return ctx.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @return
     */
    public static int getHeight(Context ctx) {
        return ctx.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * @param activity
     * @return
     */
    public static float density(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }

    public static int dip2px(Context ctx, int dpValue) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context ctx, int pxValue) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";
    /**
     * 移动手机号码的正则表达式。
     */
    private static final String REGEX_CHINA_MOBILE = "1(3[4-9]|4[7]|5[012789]|8[278])\\d{8}";
    /**
     * 联通手机号码的正则表达式。
     */
    private static final String REGEX_CHINA_UNICOM = "1(3[0-2]|5[56]|8[56])\\d{8}";
    /**
     * 电信手机号码的正则表达式。
     */
    private static final String REGEX_CHINA_TELECOM = "(?!00|015|013)(0\\d{9,11})|(1(33|53|80|89)\\d{8})";
    /**
     * 正则表达式：验证手机号
     */
    private static final String REGEX_PHONE_NUMBER = "^(0(10|2\\d|[3-9]\\d\\d)[- ]{0,3}\\d{7,8}|0?1[3584]\\d{9})$";
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_PHONE_NUMBER, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    public static Bitmap getViewBitmap(View addViewContent) {
        addViewContent.setDrawingCacheEnabled(true);
        addViewContent.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0, 0,
                addViewContent.getMeasuredWidth(),
                addViewContent.getMeasuredHeight());
        addViewContent.buildDrawingCache();

        Bitmap cacheBitmap = addViewContent.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        return bitmap;
    }
}
