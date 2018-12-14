package com.zhy.libutils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * utils about Activity
 * Created by zhy on 2016/6/14.
 */
public class ActivityUtil {

    private List<Activity> activityStack = null;
    private static ActivityUtil instance = null;

    private ActivityUtil() {
        activityStack = new ArrayList<>();
    }

    public void clearActList() {
        if (activityStack != null)
            activityStack.clear();
    }

    /**
     * @return ActivityUtil
     */
    public static ActivityUtil getInstance() {
        if (instance == null) {
            instance = new ActivityUtil();
        }
        return instance;
    }

    /**
     * 添加activity
     */
    public void addActivity(Activity activity) {

        if (!activityStack.contains(activity)) {
            activityStack.add(activity);
        }
    }

    /**
     * @return Activity
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity getCurrentActivity() {
        Activity activity = activityStack.get(activityStack.size() - 1);
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        Activity activity = activityStack.get(activityStack.size() - 1);
        finishActivity(activity);
    }

    /**
     * finishActivity 结束当前Activity（堆栈中倒数第二个压入的）
     */
    public void finishSecondToLastActivity() {
        Activity activity = activityStack.get(activityStack.size() - 2);
        finishActivity(activity);
    }

    /**
     * finishActivity  结束指定的Activity
     */
    private void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
//            Lg.e("----finishActivity----" + activity.getLocalClassName());
            activity.finish();
            activity = null;
        }
    }

    /**
     * finishActivity 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (activityStack != null) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                Activity activity = activityStack.get(i);
                if (activity != null && activity.getClass().equals(cls)) {
                    activity.finish();
                    activity = null;
                    break;
                }
            }
        }
    }

    /**
     * finishActivity  结束指定类名的Activity
     */
    public void finishOtherActivity(Class<?> cls) {
        ArrayList<Activity> list = null;
        int activityNum = 0;
        if (activityStack != null) {
            list = new ArrayList<Activity>();
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                Activity activity = activityStack.get(i);
                if (activity != null && activity.getClass().equals(cls)) {
                    list.add(activity);
                }
            }
            activityNum = list.size();
            if (activityNum > 1) {
                for (int j = 0; j < activityNum - 1; j++) {
                    Activity activity = list.get(j);
                    activity.finish();
                    activity = null;
                }
            }
        }
    }

    /**
     * remainActivity  保留唯一的Activity
     */
    public void remainActivity(Class<?> clazz) {
        if (activityStack != null) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    if (!activityStack.get(i).getClass().equals(clazz)) {
                        activityStack.get(i).finish();
                    }
                }
            }
            activityStack.clear();
        }
    }

    /**
     * finishAllActivity  结束所有Activity
     */
    public void finishAllActivity() {
        if (activityStack != null) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }

    /**
     * 当执行onDestory方法时移除改activity
     */
    public void removeActivity(Activity activity) {

        if (activityStack != null) {
            activityStack.remove(activity);
        }
    }

}
