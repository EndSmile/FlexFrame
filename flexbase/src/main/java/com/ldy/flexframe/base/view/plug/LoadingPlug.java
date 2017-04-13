package com.ldy.flexframe.base.view.plug;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ldy.flexframe.base.R;
import com.ldy.flexframe.base.loading.LoadingDialog;
import com.ldy.flexframe.base.loading.coverview.CoverLoadingView;
import com.ldy.flexframe.base.loading.coverview.CoverNetErrorView;
import com.ldy.flexframe.base.loading.coverview.ICoverView;
import com.ldy.flexframe.base.util.ToastUtil;

/**
 * Created by ldy on 2017/4/5.
 */

public class LoadingPlug extends AbViewPlug implements ILoadingView {
    private final FrameLayout container;
    private final Inner inner;

    public static final int LOADING_TYPE_DIALOG = 0;
    public static final int LOADING_TYPE_COVER = 1;
    @ColorInt
    private int loadingBackground;

    private LoadingDialog loadingDialog;
    private CoverLoadingView coverLoadingView;
    private boolean isSuccess;
    private int loadingType = LOADING_TYPE_COVER;


    public interface Inner {
        boolean loadData(@Nullable Bundle savedInstanceState);
    }

    public LoadingPlug(AppCompatActivity activity, FrameLayout container, Inner inner) {
        super(activity);
        this.container = container;
        this.inner = inner;
        this.loadingBackground = activity.getResources().getColor(R.color.activityBackground);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading(savedInstanceState);
    }

    private void initLoading(@Nullable Bundle savedInstanceState) {
        boolean isInitLoad = inner.loadData(savedInstanceState);
        if (isInitLoad) {
            loadingType = LOADING_TYPE_COVER;
        } else {
            loadingType = LOADING_TYPE_DIALOG;
        }
    }

    @Override
    public void showLoading(final String text) {
        activity.getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                if (loadingType == LOADING_TYPE_COVER) {
                    if (coverLoadingView == null) {
                        coverLoadingView = new CoverLoadingView(activity);
                        coverLoadingView.setBackgroundColor(loadingBackground);
                        cover(coverLoadingView);
                    } else {
                        coverLoadingView.cover();
                    }
                } else {
                    if (loadingDialog == null) {
                        loadingDialog = new LoadingDialog(activity);
                    }
                    loadingDialog.show(text);
                }
            }
        });
    }

    protected void cover(ICoverView iCoverView) {
        FrameLayout.LayoutParams layoutParams = getCoverLoadingParams();
        iCoverView.cover(container, layoutParams);
    }

    @NonNull
    protected FrameLayout.LayoutParams getCoverLoadingParams() {
        return new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void hideLoading() {
        activity.getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                if (loadingType == LOADING_TYPE_COVER && null != coverLoadingView) {
                    coverLoadingView.fadeOut();
                } else {
                    if (loadingDialog != null) {
                        loadingDialog.dismiss();
                    }
                }
                if (isSuccess) {
                    loadingType = LOADING_TYPE_DIALOG;
                }
            }
        });

    }

    /**
     * loading操作成功
     */
    @Override
    public void success() {
        isSuccess = true;
    }

    /**
     * loading操作失败
     */
    @Override
    public void fail() {
        if (loadingType == LOADING_TYPE_COVER) {
            final CoverNetErrorView coverNetErrorView = new CoverNetErrorView(activity);
            coverNetErrorView.setBackgroundColor(loadingBackground);
            cover(coverNetErrorView);
            coverNetErrorView.setReloadListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    coverNetErrorView.fadeOut();
                    inner.loadData(null);
                }
            });
        }
    }

    @Override
    public void showError(String msg) {
        ToastUtil.show(msg);
    }

    public void setLoadingBackground(@ColorInt int loadingBackground) {
        this.loadingBackground = loadingBackground;
    }
}
