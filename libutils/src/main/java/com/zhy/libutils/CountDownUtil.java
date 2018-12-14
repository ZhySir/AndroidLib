package com.zhy.libutils;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * utils about CountDown
 * Created by zhy on 2017/8/28.
 */
public class CountDownUtil extends CountDownTimer {

    private Button mButton;

    public CountDownUtil(Button button, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mButton = button;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        // 按钮不可用
        mButton.setEnabled(false);
        String showText = millisUntilFinished / 1000 + mButton.getResources().getString(R.string.resend_x1);
        mButton.setText(showText);
    }

    @Override
    public void onFinish() {
        // 按钮设置可用
        mButton.setEnabled(true);
        mButton.setText(mButton.getResources().getString(R.string.resend_x2));
    }
}
