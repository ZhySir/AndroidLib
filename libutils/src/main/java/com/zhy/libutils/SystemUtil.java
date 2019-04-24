package com.zhy.libutils;

import java.util.Locale;
import java.util.TimeZone;

/**
 * utils about System
 * Created by zhy on 2019/4/24.
 */
public class SystemUtil {

    private SystemUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取“时间偏移”对应(小时)
     *
     * @return 偏移多少小时
     */
    public static int getTimeZoneRawOffsetToHour() {
        // 默认时区
        TimeZone tz = TimeZone.getDefault();
        // 获取“时间偏移”。相对于“本初子午线”的偏移，单位是ms
        int offset = tz.getRawOffset();
        return offset / (3600 * 1000);
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

}
