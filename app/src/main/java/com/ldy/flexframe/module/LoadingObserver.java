package com.ldy.flexframe.module;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;

/**
 * Created by ldy on 2017/3/30.
 */

public abstract class LoadingObserver<T> implements SingleObserver<BaseResponseBean> {
    @Override
    public void onSuccess(@NonNull BaseResponseBean baseResponseBean) {

    }
}
