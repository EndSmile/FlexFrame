package com.ldy.flexframe.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by ldy on 2017/3/30.
 */

public class FlexApp{

    private static Context context;
    private static int presenterValue;

    public static void init(Application application,int presenterValue){
        context = application.getApplicationContext();
        FlexApp.presenterValue = presenterValue;
        ActivityStackControl.instance().register(application);
    }

    public static Context getContext() {
        return context;
    }

    public static int getPresenterValue() {
        return presenterValue;
    }
}
