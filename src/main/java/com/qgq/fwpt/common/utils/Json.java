package com.qgq.fwpt.common.utils;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

/**
 * Created on 2017/04/15
 *
 * @author 繁华
 */
public class Json {
    private static final ObjectMapper objectMapper = new CustomObjectMapper();

    private static class CustomObjectMapper extends ObjectMapper {
        private static final String dateFormatPattern = "yyyy-MM-dd HH:mm:ss";

        public CustomObjectMapper() {
            // 设置时间格式
            this.setDateFormat(new SimpleDateFormat(dateFormatPattern));

            // 反序列化时可能出现bean对象中没有的字段, 忽略
            this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // 设置序列化的可见域
            this.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.PUBLIC_ONLY);
        }
    }

    public static String object2String(Object obj) {
        String s = null;
        try {
            s = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }
}
