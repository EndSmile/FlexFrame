package com.ldy.flexframe;


import android.util.Log;

import com.ldy.flexframe.base.BasePresenter;
import com.ldy.flexframe.module.AppConfig;
import com.ldy.flexframe.module.BaseRequestBean;
import com.ldy.flexframe.module.LoginParams;
import com.ldy.flexframe.base.loading.LoadingObserver;
import com.ldy.flexframe.net.NetRequest;

import io.reactivex.annotations.NonNull;

/**
 * Created by ldy on 2017/3/31.
 */

public class LoginPresenter extends BasePresenter {

    @Override
    protected void onCreate() {
        super.onCreate();

    }

    public void login(){
        LoginParams loginParams = new LoginParams();
        loginParams.setIccid("1");
        loginParams.setImei("1");
        loginParams.setImsi("1");
        loginParams.setValidateName("008716");
        loginParams.setValidatePass("123456");
        BaseRequestBean baseRequestBean = new BaseRequestBean();
        baseRequestBean.setMethod("login");
        baseRequestBean.setJsonrpc("2.0");
        baseRequestBean.setParams(loginParams);
        NetRequest.request("login",loginParams, AppConfig.class)

                .subscribe(new LoadingObserver<AppConfig>(currentView) {
                    @Override
                    public void onNext(@NonNull AppConfig appConfig) {
                        Log.d("MainActivity", appConfig.toString());
                    }
                });
    }
}
