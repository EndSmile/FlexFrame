package com.ldy.flexframe.base.loading.coverview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

/**
 * Created by ldy on 2016/11/7.
 */

public interface ICoverView {
    /**
     * coverView消失
     */
    void fadeOut();

    /**
     * coverView填充的父布局
     * @param container 父布局
     * @param layoutParams 填充的参数
     */
    void cover(@NonNull FrameLayout container, @Nullable FrameLayout.LayoutParams layoutParams);
}
