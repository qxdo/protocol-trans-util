package com.util;




import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

public class JSONUtil {
    public JSONUtil() {
    }

    public static String beanToJSON(Object obj) {
        return JSONObject.toJSONString(obj, new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.IgnoreErrorGetter, SerializerFeature.IgnoreNonFieldGetter});
    }

    public static <T> T jsonToBean(String jsonStr, Class<T> clazz) {
        return JSONObject.parseObject(jsonStr, clazz);
    }

    public static <T> List<T> jsonToListBean(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    public static <T> T mapToBean(Map<String, Object> jsonMap, Class<T> clazz) {
        String jsonStr = JSON.toJSONString(jsonMap);
        return jsonToBean(jsonStr, clazz);
    }

    public static <T> List<T> listMapToListBean(List<Map<String, Object>> listMap, Class<T> clazz) {
        String jsonStr = JSON.toJSONString(listMap);
        return jsonToListBean(jsonStr, clazz);
    }
}

