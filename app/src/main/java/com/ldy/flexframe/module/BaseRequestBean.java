package com.ldy.flexframe.module;

/**
 * Created by hhz on 2015/5/21.
 */
public class BaseRequestBean {

    /**
     * 接口版本
     */
    private String jsonrpc = "2.0";

    /**
     * 接口方法
     */
    private String method;

    private Object params;

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }


}
