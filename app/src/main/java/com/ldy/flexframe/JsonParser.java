package com.ldy.flexframe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ldy.flexframe.module.BaseResponseBean;

import java.lang.reflect.Type;

/**
 * Created by hhz on 2015/5/21.
 */
public class JsonParser {

    private static JsonParser jsonParser = new JsonParser();
    private Gson gson;

    private JsonParser() {
        if (gson == null) gson = getGson();
    }

    public static final JsonParser getInstance() {
        return jsonParser;
    }

    /**
     * 返回gson对象
     *
     * @return gson;
     */
    private Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson;
    }


    /**
     * 获取http响应bean
     * @param json
     * @return
     */
    public BaseResponseBean getHttpResponseObject(String json){
        Type responseType = new TypeToken<BaseResponseBean>() {}.getType();
        BaseResponseBean responseBean = (BaseResponseBean) JsonParser.getInstance()
                .json2object(responseType, json);
        return responseBean;
    }

    public Object json2object(Type type, String jsonStr) {
        return getGson().fromJson(jsonStr, type);
    }

    public String obj2Json(Object obj) {
        return gson.toJson(obj);
    }

    public <T> T obj2T(Object obj,Class<T> tClass){
        return gson.fromJson(obj2Json(obj),tClass);
    }
}
