package com.ldy.flexframe.base.util;

import com.ldy.flexframe.base.FlexApp;

public class DensityUtil {

	/** 
     * 将px值转换为dip或dp值，保证尺寸大小不变 
     *  
     * @param pxValue 
     * @return
     */  
    public static int px2dip(float pxValue) {
        final float scale = FlexApp.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);  
    }  
  
    /** 
     * 将dip或dp值转换为px值，保证尺寸大小不变 
     *  
     * @param dipValue 
     * @return
     */  
    public static int dip2px(float dipValue) {
        final float scale = FlexApp.getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);  
    }  
  


}
