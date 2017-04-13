package com.ldy.flexframe.base.view.plug;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ldy on 2017/4/5.
 */

public abstract class AbViewPlug {

    protected AppCompatActivity activity;

    public AbViewPlug(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void onCreate(@Nullable Bundle savedInstanceState){}

    public void onStart(){}

    public void onResume(){}

    public void onPause(){}

    public void onStop(){}

    public void onDestroy(){}
}
