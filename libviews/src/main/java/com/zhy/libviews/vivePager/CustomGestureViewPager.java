package com.zhy.libviews.vivePager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ViewPager + Fragment + RecyclerView
 * Recycleriew item 子view设置点击事件，左右滑动冲突
 * 交易子窗口使用
 */

public class CustomGestureViewPager extends ViewPager {

    private int startX;
    private int startY;

    public CustomGestureViewPager(Context context) {
        super(context);
    }

    public CustomGestureViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dX = (int) (ev.getX() - startX);
                int dY = (int) (ev.getY() - startY);
                // 左右滑动true 上下滑动false
                return Math.abs(dX) > Math.abs(dY) ? true : false;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

}
