package com.ldy.flexframe.base.view.plug;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ldy.flexframe.base.R;

/**
 * Created by ldy on 2017/4/5.
 */

public class WrapperContentPlug extends AbViewPlug {
    public static final int TOOLBAR_TYPE_NULL = 0;
    public static final int TOOLBAR_TYPE_BACK = 1;
    public static final int TOOLBAR_TYPE_NORMAL = 2;
    private final Inner inner;
    private Toolbar toolbar;
    private FrameLayout contentWrapper;
    private ViewGroup rootView;

    public interface Inner{
        int getToolbarType();
    }

    public WrapperContentPlug(BasePlug basePlug, final Inner inner) {
        super(basePlug.activity);
        this.inner = inner;

        basePlug.addRootViewInterceptor(new AttrInterceptor<View>() {
            @Override
            public View interceptor(View view) {
                return wrapperView(view, inner);
            }
        });
    }

    private View wrapperView(View view, Inner inner) {
        contentWrapper = new FrameLayout(activity);

        switch (inner.getToolbarType()){
            case TOOLBAR_TYPE_NULL:
                rootView = contentWrapper;
                break;
            case TOOLBAR_TYPE_BACK:
            case TOOLBAR_TYPE_NORMAL:
                rootView = new LinearLayout(activity);
                ((LinearLayout) rootView).setOrientation(LinearLayout.VERTICAL);
                toolbar = (Toolbar) LayoutInflater.from(activity).inflate(R.layout.view_toolbar,null);
                rootView.addView(toolbar);
                rootView.addView(contentWrapper,FrameLayout.LayoutParams.MATCH_PARENT
                        ,FrameLayout.LayoutParams.MATCH_PARENT);
                break;
        }
        if (toolbar!=null){
            activity.setSupportActionBar(toolbar);
            switch (inner.getToolbarType()) {
                case TOOLBAR_TYPE_BACK:
                    toolbar.setNavigationIcon(R.drawable.ic_back);
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            activity.onBackPressed();
                        }
                    });
                    break;
            }
        }
        contentWrapper.addView(view, FrameLayout.LayoutParams.MATCH_PARENT
                ,FrameLayout.LayoutParams.MATCH_PARENT);
        return rootView;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public FrameLayout getContentWrapper() {
        return contentWrapper;
    }

    public ViewGroup getRootView() {
        return rootView;
    }
}
