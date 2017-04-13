package com.ldy.flexframe.base.loading.coverview;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by ldy on 2016/11/7.
 */
public class CoverLoadingControl extends CoverControl {

    private final View coveredView;
    private int topMargin;

    public CoverLoadingControl(View coveredView, int topMargin) {
        super((Activity) coveredView.getContext());
        this.coveredView = coveredView;
        this.topMargin = topMargin;
    }

    public CoverLoadingControl(View coveredView) {
        this(coveredView, 0);
    }

    public CoverLoadingControl(Activity activity, int topMargin) {
        this(activity.getWindow().getDecorView(), topMargin);
    }

    public void coverLoading() {
        coverLoading((FrameLayout) activity.getWindow().getDecorView());
    }

    public void coverLoading(@NonNull final FrameLayout frameLayout) {
        if (ICoverView == null) {
            if (coveredView.getWidth()==0){
                //view还没有测量完毕
                coveredView.post(new Runnable() {
                    @Override
                    public void run() {
                        initCover(frameLayout);
                    }
                });
            }else {
                initCover(frameLayout);
            }
        } else {
            ICoverView.cover(frameLayout, null);
        }
    }

    protected void initCover(@NonNull FrameLayout frameLayout) {
        ICoverView = new CoverLoadingView(activity);
        int[] coveredLocation = new int[2];
        coveredView.getLocationInWindow(coveredLocation); //获取在当前窗口内的绝对坐标

        int[] frameLayoutLocation = new int[2];
        frameLayout.getLocationInWindow(frameLayoutLocation); //获取在当前窗口内的绝对坐标
        int marginX = coveredLocation[0] - frameLayoutLocation[0];
        int marginY = coveredLocation[1] - frameLayoutLocation[1];

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(coveredView.getWidth(), coveredView.getHeight());
        layoutParams.setMargins(marginX, marginY + topMargin, 0, 0);
        ICoverView.cover(frameLayout, layoutParams);
    }
}
