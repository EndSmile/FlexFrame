package com.ldy.flexframe.base.loading;

import com.ldy.flexframe.base.view.plug.ILoadingView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by ldy on 2017/3/30.
 */

public abstract class LoadingObserver<T> implements Observer<T> {
    private ILoadingView iLoadingView;

    public LoadingObserver(ILoadingView iLoadingView) {
        this(iLoadingView,null);
    }

    public LoadingObserver(ILoadingView iLoadingView, String loadingText) {
        this.iLoadingView = iLoadingView;
        iLoadingView.showLoading(loadingText);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        iLoadingView.fail();
        onComplete();
        if (isShowError()){
            iLoadingView.showError(e.getMessage());
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onComplete() {
        if (isShowLoading()) {
            iLoadingView.hideLoading();
        }
    }

    protected boolean isShowLoading(){
        return true;
    }

    protected boolean isShowError(){
        return true;
    }
}
