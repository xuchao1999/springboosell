package com.xc.sell.util;

import com.google.gson.Gson;

public class JsonUtil<T> {
    public static <T> T json2Obj(String json, Class<T> cls){
        Gson gson = new Gson();
        return gson.fromJson(json, cls);
    }
}
