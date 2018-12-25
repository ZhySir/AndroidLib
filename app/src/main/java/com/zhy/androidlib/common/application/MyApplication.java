package com.zhy.androidlib.common.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import java.util.List;

/**
 * The earliest start
 * Created by zhy on 2018/12/25.
 */

public class MyApplication extends Application {

    private static MyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        String curProcess = getProcessName(this, android.os.Process.myPid());
        if (!getPackageName().equals(curProcess)) return;
        // 所有操作往下写...
        mApplication = this;

    }

    public static Context getContext() {
        if (mApplication != null) {
            return mApplication;
        }
        throw new NullPointerException("u should init first");
    }

    // 进程名
    private String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

}