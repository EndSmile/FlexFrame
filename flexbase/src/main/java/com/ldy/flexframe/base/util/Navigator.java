package com.ldy.flexframe.base.util;

import android.app.Activity;
import android.content.Intent;

import com.ldy.flexframe.base.ActivityStack;

/**
 * Created by ldy on 2017/4/5.
 */

public class Navigator {
    public static void navigation(Class<? extends Activity> destination){
        Activity current = ActivityStack.instance().current();
        current.startActivity(new Intent(current,destination));
    }
}
