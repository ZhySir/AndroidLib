package com.zhy.libviews.layoutManager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * Created by zhy on 2017/8/15.
 */

public class ScrollGridLayoutManager extends GridLayoutManager {

    private boolean isScrollEnabled = true;

    public ScrollGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }

}
