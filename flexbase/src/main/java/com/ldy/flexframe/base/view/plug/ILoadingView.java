package com.ldy.flexframe.base.view.plug;

import android.support.annotation.Nullable;

/**
 * Created by frog on 2016/1/20.
 */
public interface ILoadingView {
    /**
     * loading操作成功
     */
    void success();

    /**
     * loading操作失败
     */
    void fail();
    void showLoading(@Nullable String text);
    void hideLoading();
    void showError(String msg);
}
