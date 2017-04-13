package com.ldy.flexframe.base.view.plug;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ldy.flexframe.base.BasePresenter;
import com.ldy.flexframe.base.FlexApp;

import java.lang.reflect.ParameterizedType;

/**
 * Created by ldy on 2017/4/5.
 */

public class ViewTierPlug<P extends BasePresenter> extends AbViewPlug {
    private final BasePlug basePlug;
    private final ILoadingView loadingView;
    protected P presenter;

    public ViewTierPlug(BasePlug basePlug, ILoadingView loadingView, P basePresenter,AppCompatActivity activity) {
        super(activity);
        this.basePlug = basePlug;
        this.loadingView = loadingView;
        presenter = basePresenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter != null) {
            presenter.onViewCreate(loadingView);
            basePlug.getBinding().setVariable(FlexApp.getPresenterValue(), presenter);
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onViewDestroy(loadingView);
        }
    }
}
