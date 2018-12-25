package com.zhy.androidlib.common.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.androidlib.R;
import com.zhy.androidlib.common.application.MyApplication;

/**
 * utils about Toast
 * Created by zhy on 2016/5/18.
 */
public class ToastUtil {

    private static Toast toast;
    private static Toast styleToast;

    private ToastUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 如果在主线程弹静态吐司可以使用这个，传入上下文即可，但是只能在主线程中执行
    public static void showToast(String msg) {
        Context context = MyApplication.getContext();
        if (toast == null)
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);

        View view = View.inflate(context, R.layout.toast_default, null);
        TextView tvMsg = view.findViewById(R.id.text_view);
        tvMsg.setText(msg);

        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(view);
        toast.show();
    }

    // 弹静态吐司，无论子线程还是主线程都能执行（里面做了一个线程判断）
    public static void showStaticToast(final Activity act, final String msg) {
        // 获取当前线程
        String nowThreadName = Thread.currentThread().getName();
        // 如果为主线程
        if ("main".equals(nowThreadName)) {
            if (toast == null) toast = Toast.makeText(act, msg, Toast.LENGTH_SHORT);
            toast.setText(msg);
            toast.show();
            // 如果为子线程
        } else {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (toast == null) toast = Toast.makeText(act, msg, Toast.LENGTH_SHORT);
                    toast.setText(msg);
                    toast.show();
                }
            });
        }
    }

    /**
     * 显示成功的Toast
     */
    public static void showCustomSuccessToast(Context context, String message) {
        showCustomToast(context, message, R.drawable.toast_success);
    }

    /**
     * 显示失败的Toast
     */
    public static void showCustomFaildToast(Context context, String message) {
        showCustomToast(context, message, R.drawable.toast_faild);
    }

    private static void showCustomToast(Context context, String message, int toast_bg) {
        View toastView = View.inflate(context, R.layout.toast, null);
        TextView tv = toastView.findViewById(R.id.textView);
        tv.setText(message);
        ImageView iv = toastView.findViewById(R.id.imageView);
        iv.setBackgroundResource(toast_bg);
        styleToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        styleToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        styleToast.setView(toastView);
        styleToast.show();
    }

}