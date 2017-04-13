package com.ldy.flexframe.base.loading.coverview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;


import com.ldy.flexframe.base.R;

import java.security.InvalidParameterException;

/**
 * Created by ldy on 16/8/26.
 */
public class CoverLoadingView extends FrameLayout implements ICoverView {
    private static float density;
    private final Context context;
    private FrameLayout container;
    private View car;
    private ImageView ivProgress;

    public CoverLoadingView(Context context) {
        this(context, null);
    }

    public CoverLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {

        View view = LayoutInflater.from(context).inflate(R.layout.view_cover_loading, null);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        addView(view, params);

        ivProgress = (ImageView) findViewById(R.id.iv_cover_loading_car);
    }

    public float getDensity(Context context) {
        if (density <= 0F) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return density;
    }

    public int dip2px(float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5f);
    }

    @Override
    public void fadeOut() {
        Animation fadeOut = new AlphaAnimation(1f, 0f);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(100);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                stopProgress();
                setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startAnimation(fadeOut);
    }


    /**
     * coverView填充的父布局
     *
     * @param container    父布局
     * @param layoutParams 填充的参数
     */
    @Override
    public void cover(@NonNull FrameLayout container, @Nullable LayoutParams layoutParams) {
        if (this.container == null) {
            this.container = container;
            if (layoutParams == null) {
                layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            container.addView(this, layoutParams);
        } else {
            if (this.container != container) {
                throw new InvalidParameterException("一个coverLoading只能接受一个container");
            }
            setVisibility(VISIBLE);
        }
        startProgress();
    }

    public void cover() {
        setVisibility(VISIBLE);
        startProgress();
    }

    private void startProgress() {
        Animation operatingAnim = AnimationUtils.loadAnimation(context, R.anim.cover_loading);
        operatingAnim.setInterpolator(new LinearInterpolator());
        ivProgress.startAnimation(operatingAnim);
    }
    private void stopProgress() {
        ivProgress.clearAnimation();
    }
}
