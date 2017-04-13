package com.ldy.flexframe.base.view;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public abstract class FlexBaseFragment<P extends BasePresenter, B extends ViewDataBinding> extends Fragment
        implements BasePlug.Inner, WrapperContentPlug.Inner, LoadingPlug.Inner {
    protected B binding;
    private BasePlug<B> basePlug;
    private WrapperContentPlug wrapperContentPlug;
    private LoadingPlug loadingPlug;
    private ViewTierPlug<P> viewTierPlug;
    private List<AbViewPlug> plugs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPlug();
        commandPlug();
        for (AbViewPlug plug : plugs) {
            plug.onCreate(savedInstanceState);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return basePlug.getRootView();
    }

    private void createPlug() {
        plugs = new ArrayList<>();

        basePlug = new BasePlug<>((AppCompatActivity) getActivity(), this);
        wrapperContentPlug = new WrapperContentPlug(basePlug, this);
        loadingPlug = new LoadingPlug((AppCompatActivity) getActivity(), wrapperContentPlug.getContentWrapper(), this);
        viewTierPlug = new ViewTierPlug<>(basePlug, loadingPlug, getPresenter(),(AppCompatActivity) getActivity());
        plugs.add(basePlug);
        plugs.add(wrapperContentPlug);
        plugs.add(loadingPlug);
        plugs.add(viewTierPlug);
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

    protected void commandPlug() {

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public int getToolbarType() {
        return WrapperContentPlug.TOOLBAR_TYPE_NULL;
    }

    @Override
    public boolean loadData(@Nullable Bundle savedInstanceState) {
        return false;
    }


    @Override
    public void onStart() {
        super.onStart();
        for (AbViewPlug plug : plugs) {
            plug.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        for (AbViewPlug plug : plugs) {
            plug.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        for (AbViewPlug plug : plugs) {
            plug.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        for (AbViewPlug plug : plugs) {
            plug.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (AbViewPlug plug : plugs) {
            plug.onDestroy();
        }
    }
}
