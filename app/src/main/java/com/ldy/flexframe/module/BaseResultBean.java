package com.ldy.flexframe.module;

/**
 * Created by hhz on 2015/5/21.
 */
public class BaseResultBean {

    /**
     * 返回结果状态 1代表成功 其他-获取失败
     */
    private int resultStatus;

    /**
     * 返回结果信息
     */
    private Object info;

    public int getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(int resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
