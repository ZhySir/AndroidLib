package com.zhy.libviews.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.zhy.libviews.R;

/**
 * 自定义dialog
 * 可自定义 标题，内容，左右按钮文本
 * Created by zhy on 2018/11/3.
 */

public class BaseDialog extends Dialog {

    private TextView tvTitle, tvMsg, tvCancel, tvConfirm;
    private ConfirmButtonListener buttonListener;
    private String msg;
    private String title;
    private String cancelStr;
    private String confirmStr;

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.UpdateDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_base);

        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = (int) (display.getWidth() * 0.7);
        getWindow().setAttributes(lp);

        setCanceledOnTouchOutside(false);
        setCancelable(false);

        initView();
        initListener();

    }

    private void initView() {
        tvTitle = findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
        tvMsg = findViewById(R.id.tv_msg);
        tvMsg.setText(msg);
        tvCancel = findViewById(R.id.tv_cancel);
        if (!TextUtils.isEmpty(cancelStr)) {
            tvCancel.setText(cancelStr);
        }
        tvConfirm = findViewById(R.id.tv_confirm);
        if (!TextUtils.isEmpty(confirmStr)) {
            tvConfirm.setText(confirmStr);
        }
    }

    public BaseDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public BaseDialog setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public BaseDialog setCancelStr(String cancelStr) {
        this.cancelStr = cancelStr;
        return this;
    }

    public BaseDialog setConfirmStr(String conStr) {
        this.confirmStr = conStr;
        return this;
    }

    private void initListener() {
        // 确定
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonListener != null) {
                    buttonListener.onConfirmButtonListener();
                }
                dismiss();
            }
        });
        // 取消
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonListener != null && buttonListener instanceof CancelButtonListener) {
                    ((CancelButtonListener) buttonListener).onCancelButtonListener();
                }
                dismiss();
            }
        });
    }

    public BaseDialog setButtonListener(ConfirmButtonListener listener) {
        this.buttonListener = listener;
        return this;
    }

    public interface ConfirmButtonListener {
        void onConfirmButtonListener();
    }

    public interface CancelButtonListener extends ConfirmButtonListener {
        void onCancelButtonListener();
    }
}
