package com.zillionwon.common.langchain.factory;

import com.zillionwon.common.core.util.CacheUtils;
import com.zillionwon.common.core.util.JsonUtils;
import com.zillionwon.common.langchain.config.AiProperties;
import com.zillionwon.common.langchain.constant.AiConstant;
import com.zillionwon.common.langchain.exception.AiException;
import com.zillionwon.common.langchain.model.ChatLanguageModelDecorator;
import com.zillionwon.common.langchain.strategy.AiStrategy;
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

        // 从 Redis 缓存中获取配置信息
        String json = CacheUtils.get(AiConstant.CONFIG_PREFIX, configKey);
        if (json == null) {
            // 如果找不到, 则抛出异常
            throw new AiException("404", "系统异常, '" + configKey + "' 配置信息不存在!");
        }
        AiProperties properties = JsonUtils.parseObject(json, AiProperties.class);

        // 尝试从缓存中获取模型
        ChatLanguageModelDecorator modelDecorator = MODEL_CACHE.get(configKey);
        if (modelDecorator == null) {
            // 如果模型不在缓存中, 则根据配置信息, 创建新模型并放入缓存
            MODEL_CACHE.put(configKey, AiStrategy.create(configKey, json));
            log.info("创建 AiModel 实例 key => {}", configKey);
            return MODEL_CACHE.get(configKey).getChatLanguageModel();
        }

        // TODO: 配置不相同则重新构建
        if (!modelDecorator.propertiesEqual(properties)) {
            MODEL_CACHE.put(configKey, AiStrategy.create(configKey, json));
            log.info("重载 AiModel 实例 key => {}", configKey);
            return MODEL_CACHE.get(configKey).getChatLanguageModel();
        }
        return modelDecorator.getChatLanguageModel();
    }

}
