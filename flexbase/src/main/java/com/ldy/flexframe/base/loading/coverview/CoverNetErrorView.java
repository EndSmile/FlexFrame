package com.ldy.flexframe.base.loading.coverview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ldy.flexframe.base.R;

import java.security.InvalidParameterException;

/**
 * Created by ldy on 16/8/26.
 */
public class CoverNetErrorView extends FrameLayout implements ICoverView {
    private static float density;
    private final Context context;
    private FrameLayout container;
    private ImageView ivProgress;
    private View reload;

    public CoverNetErrorView(Context context) {
        this(context, null);
    }

    public CoverNetErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.view_net_error,this);
        reload = findViewById(R.id.btn_net_error_reload);
    }

    public void setReloadListener(OnClickListener onClickListener){
        reload.setOnClickListener(onClickListener);
    }

    @Override
    public void fadeOut() {
//        Animation fadeOut = new AlphaAnimation(1f, 0f);
//        fadeOut.setInterpolator(new AccelerateInterpolator());
//        fadeOut.setDuration(100);
//        fadeOut.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                ((AnimationDrawable) ivProgress.getDrawable()).stop();
//                setVisibility(GONE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        startAnimation(fadeOut);
        setVisibility(GONE);
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
            if (layoutParams==null) {
                layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            container.addView(this,layoutParams);
        } else {
            if (this.container != container) {
                throw new InvalidParameterException("一个coverLoading只能接受一个container");
            }
            setVisibility(VISIBLE);
        }
    }

    public void cover(){
        setVisibility(VISIBLE);
        ((AnimationDrawable) ivProgress.getDrawable()).start();
    }
}
