package com.ldy.flexframe.base.view.plug;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldy on 2017/4/5.
 */

public class BasePlug<B extends ViewDataBinding> extends AbViewPlug {
    private Inner inner;
    private final boolean isAttachView;
    private B binding;
    private List<AttrInterceptor<View>> interceptors;

    public interface Inner {
        @LayoutRes
        int getContentId();
        void initView(@Nullable Bundle savedInstanceState);
    }

    public BasePlug(AppCompatActivity activity, Inner inner) {
        this(activity,inner,true);
    }

    public BasePlug(AppCompatActivity activity, Inner inner,boolean isAttachView) {
        super(activity);
        this.inner = inner;
        this.isAttachView = isAttachView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isAttachView){
            attachView(savedInstanceState);
        }
        inner.initView(savedInstanceState);
    }

    protected void attachView(@Nullable Bundle savedInstanceState){
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        activity.setContentView(getRootView(),params);
    }

    public View getRootView() {
        View rootView = activity.getLayoutInflater().inflate(inner.getContentId(), null, false);
        binding = DataBindingUtil.bind(rootView);
        for (AttrInterceptor<View> attrInterceptor:interceptors){
            rootView = attrInterceptor.interceptor(rootView);
        }
        return rootView;
    }

    public void addRootViewInterceptor(AttrInterceptor<View> viewAttrInterceptor){
        if (interceptors==null){
            interceptors = new ArrayList<>();
        }
        interceptors.add(viewAttrInterceptor);
    }

    public B getBinding() {
        return binding;
    }
}
