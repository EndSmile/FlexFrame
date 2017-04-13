package com.ldy.flexframe;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by ldy on 2017/3/31.
 */

public class CommonViewConfig {

    public static LinearLayoutManager getLinearLayoutManager(Context context){
        return new LinearLayoutManager(context);
    }
}
