package com.example.springbootdemo.base.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 使用jackson json工具
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        // 对于空的对象转json的时候不抛出错误
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 允许属性名称没有引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 设置输入时忽略在json字符串中存在但在java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 设置输出时包含属性的风格
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    /**
     * 序列化，将对象转化为json字符串
     *
     * @param data
     * @return String
     */
    public static String toJsonString(Object data) {
        if (data == null) {
            return null;
        }
        String json = null;
        try {
            json = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("[{}] toJsonString error：{{}}", data.getClass().getSimpleName(), e);
        }
        return json;
    }

    /**
     * 反序列化，将json字符串转化为对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return T
     */
    public static <T> T parse(@NonNull String json, Class<T> clazz) {
        T t = null;
        try {
            t = mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error(" parse json [{}] to class [{}] error：{{}}", json, clazz.getSimpleName(), e);
        }
        return t;
    }

    /**
     * 将JSON数据转换成列表List
     *
     * @param json     JSON数据
     * @param beanType 对象类型
     * @return 列表
     */
    public static <T> List<T> toList(String json, Class<T> beanType) {
        List<T> resultList = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
            resultList = mapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error(" toList json [{}] to class [{}] error：{{}}", json, beanType.getSimpleName(), e);
        }
        return resultList;
    }

    /**
     * 将JSON数据转换成Set集合
     *
     * @param json        JSON数据
     * @param elementType 元素类型
     * @return Set集合
     */
    public static <E> Set<E> toSet(String json, Class<E> elementType) {
        Set<E> resultSet = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructCollectionType(Set.class, elementType);
            resultSet = mapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error(" toSet json [{}] to class [{}] error：{{}}", json, elementType.getSimpleName(), e);
        }
        return resultSet;
    }

    /**
     * 将JSON数据转换成Map集合
     *
     * @param json      JSON数据
     * @param keyType   键类型
     * @param valueType 值类型
     * @return Map集合
     */
    public static <K, V> Map<K, V> toMap(String json, Class<K> keyType, Class<V> valueType) {
        Map<K, V> resultMap = null;
        try {

            JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, keyType, valueType);
            resultMap = mapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error(" toMap json [{}] to class Map error：{{}}", json, e);
        }
        return resultMap;
    }

    /**
     * 将JSON数据转换成JsonNode
     *
     * @param json JSON数据
     * @return JsonNode
     */
    public static JsonNode toJsonNode(String json) {
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readValue(json, JsonNode.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error(" toJsonNode json [{}] to class [{}] error：{{}}", JsonNode.class.getSimpleName(), e);
        }
        return jsonNode;
    }

}

