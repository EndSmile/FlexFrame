package com.ldy.flexframe.module;

/**
 * Created by hhz on 2015/5/26.
 */
public class BaseParams {
    private String accessKey;
    public String clientType="0";//0民警客户端、1政工客户端、2辅警登录
    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
