package com.ldy.flexframe.base.view;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ldy.flexframe.base.BasePresenter;
import com.ldy.flexframe.base.view.plug.AbViewPlug;
import com.ldy.flexframe.base.view.plug.BasePlug;
import com.ldy.flexframe.base.view.plug.LoadingPlug;
import com.ldy.flexframe.base.view.plug.ViewTierPlug;
import com.ldy.flexframe.base.view.plug.WrapperContentPlug;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldy on 2017/3/30.
 */

public abstract class FlexBaseActivity<P extends BasePresenter, B extends ViewDataBinding> extends AppCompatActivity
        implements BasePlug.Inner, WrapperContentPlug.Inner, LoadingPlug.Inner {
    private BasePlug<B> basePlug;
    private WrapperContentPlug wrapperContentPlug;
    private LoadingPlug loadingPlug;
    private ViewTierPlug<P> viewTierPlug;
    private List<AbViewPlug> plugs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPlug();
        commandPlug();
        for (AbViewPlug plug : plugs) {
            plug.onCreate(savedInstanceState);
        }
    }

    private void createPlug() {
        plugs = new ArrayList<>();

        basePlug = new BasePlug<B>(this, this);
        wrapperContentPlug = new WrapperContentPlug(basePlug, this);
        loadingPlug = new LoadingPlug(this, wrapperContentPlug.getContentWrapper(), this);
        viewTierPlug = new ViewTierPlug<>(basePlug, loadingPlug, getPresenter(), this);
        plugs.add(basePlug);
        plugs.add(wrapperContentPlug);
        plugs.add(loadingPlug);
        plugs.add(viewTierPlug);
    }

    protected void commandPlug() {

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    private P getPresenter() {
        if (this.getClass().getGenericSuperclass() instanceof ParameterizedType &&
                ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments().length > 0) {
            Class presenterClass = (Class) ((ParameterizedType) (this.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[0];
            return (P) BasePresenter.getPresenter(presenterClass);

        }
        return null;
    }

    @Override
    public int getToolbarType() {
        return WrapperContentPlug.TOOLBAR_TYPE_BACK;
    }

    @Override
    public boolean loadData(@Nullable Bundle savedInstanceState) {
        return false;
    }


    @Override
    protected void onStart() {
        super.onStart();
        for (AbViewPlug plug : plugs) {
            plug.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (AbViewPlug plug : plugs) {
            plug.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (AbViewPlug plug : plugs) {
            plug.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (AbViewPlug plug : plugs) {
            plug.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (AbViewPlug plug : plugs) {
            plug.onDestroy();
        }
    }
}
