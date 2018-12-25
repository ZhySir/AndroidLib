package com.zhy.androidlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhy.androidlib.common.utils.ToastUtil;
import com.zhy.libviews.dialog.BaseDialog;

/**
 * Created by zhy on 2018/12/25.
 */
public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    TextView tvDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        tvDialog = findViewById(R.id.tv_Dialog);

        tvDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Dialog:
                new BaseDialog(this)
                        .setMsg("Are you OK?")
                        .setButtonListener(new BaseDialog.ConfirmButtonListener() {
                            @Override
                            public void onConfirmButtonListener() {
                                ToastUtil.showToast("OK");
                            }
                        }).show();
                break;
        }
    }
}
