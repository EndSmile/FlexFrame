package com.ldy.flexframe.base;


import android.content.Context;
import android.databinding.BaseObservable;

import com.ldy.flexframe.base.view.plug.ILoadingView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ldy on 2017/3/30.
 */
public class BasePresenter extends BaseObservable{
    protected Map<Class<? extends ILoadingView>,ILoadingView> viewMap = new LinkedHashMap<>();
    protected ILoadingView currentView;
    protected Context context = FlexApp.getContext();

    public void onViewCreate(ILoadingView ILoadingView){
        boolean isFirst = viewMap.isEmpty();
        viewMap.put(ILoadingView.getClass(), ILoadingView);
        currentView = ILoadingView;
        if (isFirst){
            onCreate();
        }
    }

    public void onViewDestroy(ILoadingView ILoadingView){
        viewMap.remove(ILoadingView.getClass());
        if (viewMap.isEmpty()){
            onDestroy();
            currentView = null;
        }
    }

    protected void onCreate(){

    }

    protected void onDestroy(){

    }


    private static Map<Class<? extends BasePresenter>,BasePresenter> presenterInstance = new HashMap<>();

    public static<P extends BasePresenter> P getPresenter(Class<P> presenterClass){
        P basePresenter = (P) presenterInstance.get(presenterClass);
        if (basePresenter==null){
            try {
                basePresenter = presenterClass.newInstance();
                presenterInstance.put(presenterClass,basePresenter);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return basePresenter;
    }
}
