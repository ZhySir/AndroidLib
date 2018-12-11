package com.zhy.libutils;

import android.text.TextUtils;
import android.util.Log;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 数据格式化工具
 * format(double in, int keepNum, boolean isRound) 保留几位小数，是否四舍五入
 * parseString2Float / parseString2Double / parseString2Int 字符串转数据
 * Created by zhy on 2017/12/9.
 */

public class DataUtil {

    /**
     * 数字格式化
     * in      需要格式化的内容
     * keepNum 保留位数,默认保留两位小数
     * isRound 是否开启四舍五入
     */
    public static String format(double in, boolean isRound) {
        return format(in, 2, isRound);
    }

    public static String format(String in, boolean isRound) {
        return format(in, 2, isRound);
    }

    public static String format(double in, int keepNum, boolean isRound) {
        DecimalFormat format = new DecimalFormat();
        if (keepNum < 0) {
            keepNum = 0;
        }

        format.setMaximumFractionDigits(keepNum);
        format.setMinimumFractionDigits(keepNum);
        format.setGroupingUsed(false);

        if (isRound) {
            // 四舍五入，负数原理同上
            format.setRoundingMode(RoundingMode.HALF_UP);
        }

        String result = format.format(in);
        return result;
    }

    public static String format(String in, int keepNum, boolean isRound) {
        String var3 = "";

        double indouble;
        try {
            indouble = Double.parseDouble(in);
        } catch (NumberFormatException var7) {
            var7.printStackTrace();
            return "";
        }

        return format(indouble, keepNum, isRound);
    }

    public static float parseString2Float(String value) {
        if (value == null || TextUtils.isEmpty(value) || "null".equalsIgnoreCase(value)) {
            return 0;
        }
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            Log.e("parseString2Float", "parseString2Float error:" + e.getMessage());
            return 0;
        }
    }

    public static int parseString2Int(String value) {
        if (value == null || TextUtils.isEmpty(value) || "null".equalsIgnoreCase(value)) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            Log.e("parseString2Int", "parseString2Int error:" + e.getMessage());
            return 0;
        }
    }

    public static double parseString2Double(String value) {
        if (value == null || TextUtils.isEmpty(value) || "null".equalsIgnoreCase(value)) {
            return 0;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            Log.e("parseString2Float", "parseString2Float error:" + e.getMessage());
            return 0;
        }
    }

}
