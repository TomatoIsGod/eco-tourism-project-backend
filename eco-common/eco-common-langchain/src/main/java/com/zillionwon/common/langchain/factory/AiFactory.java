package com.zillionwon.common.langchain.factory;

import com.zillionwon.common.core.util.CacheUtils;
import com.zillionwon.common.langchain.model.ChatLanguageModelDecorator;
import com.zillionwon.common.langchain.strategy.AiStrategy;
import com.zillionwon.common.langchain.constant.AiConstant;
import com.zillionwon.common.langchain.exception.AiException;
import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 大语言模型工厂类
 *
 * @author InwardFlow
 */

@Slf4j
public class AiFactory {

    private static final Map<String, ChatLanguageModelDecorator> MODEL_CACHE = new ConcurrentHashMap<>();

    /**
     * 根据类型获取实例
     */
    public static synchronized ChatLanguageModel getInstance(String configKey) {
        // 从 Redis 缓存中读取模型 Properties
        String json = CacheUtils.get(AiConstant.CONFIG_PREFIX, configKey);
        if (json == null) {
            // 如果在缓存中找不到, 则抛出异常
            throw new AiException("404", "系统异常, '" + configKey + "' 配置信息不存在!");
        }

        // 获取 MODEL_CACHE 中已创建的实例
        ChatLanguageModelDecorator modelDecorator = MODEL_CACHE.get(configKey);
        if (modelDecorator == null) {
            // 如果在 MODEL_CACHE 中还未创建, 则根据 Properties 创建
            MODEL_CACHE.put(configKey, AiStrategy.create(configKey, json));
            log.info("创建 AiModel 实例 key => {}", configKey);
            return MODEL_CACHE.get(configKey).getChatLanguageModel();
        }

        // TODO: 配置不相同则重新构建
//        if (!modelDecorator.checkPropertiesSame(properties)) {
//            CLIENT_CACHE.put(key, new OssClient(configKey, properties));
//            log.info("重载 AiModel 实例 key => {}", configKey);
//            return CLIENT_CACHE.get(key);
//        }
        return modelDecorator.getChatLanguageModel();
    }


}
