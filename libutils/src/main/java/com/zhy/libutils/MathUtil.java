package com.zhy.libutils;

import java.math.BigDecimal;

/**
 * utils about Math
 * Created by zhy on 2018/11/16.
 */

public class MathUtil {

    public static float add(float d1, float d2) {//进行加法计算
        BigDecimal b1 = new BigDecimal(String.valueOf(d1));
        BigDecimal b2 = new BigDecimal(String.valueOf(d2));
        return b1.add(b2).floatValue();
    }

    public static double add(double d1, double d2) {//进行加法计算
        BigDecimal b1 = new BigDecimal(String.valueOf(d1));
        BigDecimal b2 = new BigDecimal(String.valueOf(d2));
        return b1.add(b2).doubleValue();
    }

    static float sub(float d1, float d2) {//进行减法计算
        BigDecimal b1 = new BigDecimal(String.valueOf(d1));
        BigDecimal b2 = new BigDecimal(String.valueOf(d2));
        return b1.subtract(b2).floatValue();
    }

    static float mul(float d1, float d2) {//进行乘法计算
        if (d1 == 0 || d2 == 0) return 0;
        BigDecimal b1 = new BigDecimal(String.valueOf(d1));
        BigDecimal b2 = new BigDecimal(String.valueOf(d2));
        return b1.multiply(b2).floatValue();
    }

    static float div(float d1, float d2, int len) {//进行除法计算
        if (d1 == 0 || d2 == 0) return 0;
        BigDecimal b1 = new BigDecimal(String.valueOf(d1));
        BigDecimal b2 = new BigDecimal(String.valueOf(d2));
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static float round(float d, int len) {//进行四舍五入
        BigDecimal b1 = new BigDecimal(d);
        BigDecimal b2 = new BigDecimal(1);//技巧
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).floatValue();
    }

}
