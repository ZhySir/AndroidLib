package com.zhy.libutils;

import android.text.TextUtils;
import android.util.Log;

/**
 * utils about Log
 * Created by zhy on 2016/6/14.
 */

public class LogUtil {

    static final String TAG = "TAG";

    public static void i(String tag, String msg) {  //信息太长,分段打印
        if (TextUtils.isEmpty(msg)) {
            msg = "";
        }
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.i(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.i(tag, msg);

    }

    public static void e(String msg) {
        if (msg != null)
            Log.e(TAG, msg);
    }

    public static void i(String msg) {
        if (msg != null)
            Log.i(TAG, msg);
    }

}
