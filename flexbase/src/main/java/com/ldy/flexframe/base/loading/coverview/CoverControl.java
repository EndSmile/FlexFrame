package com.ldy.flexframe.base.loading.coverview;

import android.app.Activity;

/**
 * Created by ldy on 16/8/26.
 */
public abstract class CoverControl{
    protected final Activity activity;
    protected ICoverView ICoverView;


    public CoverControl(Activity activity){
        this.activity = activity;
    }

    public void fadeout() {
        ICoverView.fadeOut();
    }

}
