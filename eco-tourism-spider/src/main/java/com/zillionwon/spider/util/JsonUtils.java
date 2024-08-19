package com.zillionwon.spider.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import java.io.Serializable;

/**
 * JSON 工具类
 *
 * @author InwardFlow
 */
public class JsonUtils {
    public static <T extends Serializable> String toJson(T o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Serializable> String toJsonPretty(T o) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SnakeCaseStrategy.INSTANCE);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean toAjax(String s) {
        if (s == null) {
            return false;
        }
        return ("true".equals(s));
    }
}
