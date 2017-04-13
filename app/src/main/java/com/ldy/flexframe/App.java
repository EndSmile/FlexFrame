package com.ldy.flexframe;

import android.app.Application;

import com.ldy.flexframe.base.FlexApp;
import com.ldy.xelog.XELog;
import com.ldy.xelog.config.auto.ActivityLog;
import com.ldy.xelog.config.auto.CrashCatchLog;
import com.ldy.xelog_read.XelogRead;

/**
 * Created by ldy on 2017/4/5.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlexApp.init(this,BR.presenter);
        XELog.init(this);
        XELog.activateAutoLog(ActivityLog.getInstance("ldy"),new CrashCatchLog("ldy"));
        XelogRead.init(XELog.getFileDir());
    }
}
