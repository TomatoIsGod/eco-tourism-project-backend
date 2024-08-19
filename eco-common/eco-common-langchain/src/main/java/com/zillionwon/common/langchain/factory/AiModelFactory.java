package com.zillionwon.common.langchain.factory;

import com.zillionwon.common.core.util.SpringUtils;
import com.zillionwon.common.jedis.service.JedisService;
import com.zillionwon.common.langchain.config.IModelStrategy;
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
public class AiModelFactory {

    private static final JedisService JEDIS = SpringUtils.getBean(JedisService.class);

    private static final Map<String, ChatLanguageModel> MODEL_CACHE = new ConcurrentHashMap<>();

    /**
     * 根据类型获取实例
     */
    public static synchronized ChatLanguageModel getInstance(String configKey) {
        // 从 Redis 读取模型 Properties
        String json = String.valueOf(JEDIS.get(AiConstant.CONFIG_PREFIX + "." + configKey, 0));
        if (json == null) {
            throw new AiException("404", "系统异常, '" + configKey + "'配置信息不存在!");
        }

        // 获取已创建的实例
        ChatLanguageModel model = MODEL_CACHE.get(configKey);
        if (model == null) {
            MODEL_CACHE.put(configKey, IModelStrategy.create(configKey, json));
            log.info("创建 AiModel 实例 key => {}", configKey);
            return MODEL_CACHE.get(configKey);
        }

        // TODO: 配置不相同则重新构建
//        if (!model.checkPropertiesSame(properties)) {
//            CLIENT_CACHE.put(key, new OssClient(configKey, properties));
//            log.info("重载 AiModel 实例 key => {}", configKey);
//            return CLIENT_CACHE.get(key);
//        }
        return model;
    }


}
