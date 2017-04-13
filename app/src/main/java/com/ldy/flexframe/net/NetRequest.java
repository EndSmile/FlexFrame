package com.ldy.flexframe.net;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.ldy.flexframe.JsonParser;
import com.ldy.flexframe.module.BaseParams;
import com.ldy.flexframe.module.BaseRequestBean;
import com.ldy.flexframe.module.BaseResponseBean;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by ldy on 2017/3/30.
 */

public class NetRequest {

    public static <T> Observable<T> request(String method, BaseParams params, final Class<T> tClass) {
        final BaseRequestBean baseRequestBean = new BaseRequestBean();
        baseRequestBean.setMethod(method);
        baseRequestBean.setJsonrpc("2.0");
        baseRequestBean.setParams(params);
        return Api.getInstance().request(baseRequestBean)
                .map(new Function<BaseResponseBean, T>() {
                    @Override
                    public T apply(@NonNull BaseResponseBean baseResponseBean) throws Exception {
//                        throw new NetResultException("ldy");
                        if (baseResponseBean.getResult().getResultStatus() == 1) {
                            return JsonParser.getInstance().obj2T(baseResponseBean.getResult().getInfo(), tClass);
                        } else {
                            String error = baseResponseBean.getResult().getInfo().toString();
                            if (!TextUtils.isEmpty(error) && error.contains("登录超时")) {
                                Log.d("NetRequest", "登录超时");
                            } else {
                                throw new NetResultException(error);
                            }
                            return null;
                        }
                    }
                })
                .filter(new Predicate<T>() {
                    @Override
                    public boolean test(@NonNull T t) throws Exception {
                        return t != null;
                    }
                });
    }
}
