package com.ldy.flexframe.net;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ldy.flexframe.module.BaseParams;
import com.ldy.flexframe.module.BaseRequestBean;
import com.ldy.flexframe.module.BaseResponseBean;
import com.ldy.flexframe.module.BaseResultBean;
import com.xdja.xelog.okhttp_interceptor.OkHttpLogInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by baixiaokang on 16/3/9.
 */
public class Api {
    private final Net service;
    public Retrofit retrofit;

    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }

    //构造方法私有
    private Api() {
        OkHttpLogInterceptor logInterceptor = new OkHttpLogInterceptor();
        logInterceptor.setLevel(OkHttpLogInterceptor.Level.BODY);
        logInterceptor.jsonBody();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(7676, TimeUnit.MILLISECONDS)
                .connectTimeout(7676, TimeUnit.MILLISECONDS)
                .addInterceptor(logInterceptor)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://120.194.4.131:18081/fjms/client/")
                .build();
        service = retrofit.create(Net.class);
    }

    public Observable<BaseResponseBean> request(BaseRequestBean requestBean){
        return service.request(requestBean).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public interface Net{
        @POST("clientApi.do")
        Observable<BaseResponseBean> request(@Body BaseRequestBean requestBean);
    }

}