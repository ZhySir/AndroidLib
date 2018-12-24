package com.zhy.libutils;

import android.app.Activity;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

/**
 * utils about Popup
 * Created by zhy on 2018/12/21.
 */

public class PopupUtil {

    /**
     * show
     *
     * @param activity
     * @param pw
     * @param anchor
     * @param xoff
     * @param yoff
     */
    public static void showAsDropDown(Activity activity, final PopupWindow pw, final View anchor,
                                      final int xoff, final int yoff) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            int[] location = new int[2];
            anchor.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            int screenHeight = ScreenUtil.getScreenHeight(activity);
            pw.setHeight(screenHeight - y - anchor.getHeight() - yoff);
            pw.showAtLocation(anchor, Gravity.NO_GRAVITY, xoff, pw.getHeight());
        } else {
            pw.showAsDropDown(anchor, xoff, yoff);
        }
    }

}
