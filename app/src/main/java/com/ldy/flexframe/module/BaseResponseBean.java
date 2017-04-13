package com.ldy.flexframe.module;

/**
 * Created by hhz on 2015/5/21.
 */
public class BaseResponseBean {

    /**
     * 接口版本号
     */
    private String jsonrpc = "2.0";

    /**
     * 所调用的方法
     */
    private String method;

    /**
     * 返回结果
     */
    private BaseResultBean result;

    public BaseResultBean getResult() {
        return result;
    }

    public void setResult(BaseResultBean result) {
        this.result = result;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "BaseResponseBean{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", method='" + method + '\'' +
                ", result=" + result +
                '}';
    }
}
