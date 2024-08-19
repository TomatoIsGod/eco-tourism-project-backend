package com.zillionwon.common.langchain.config;

import com.zillionwon.common.core.util.SpringUtils;
import dev.langchain4j.model.chat.ChatLanguageModel;

/**
 * 大语言模型逻辑
 *
 * @author InwardFlow
 */

public interface IModelStrategy {

    String BASE_NAME = "ModelStrategy";

    static ChatLanguageModel create(String configKey, String properties) {
        // 授权类型和客户端id
        String beanName = configKey + BASE_NAME;
        if (!SpringUtils.containsBean(beanName)) {
            throw new RuntimeException("授权类型不正确!");
        }
        IModelStrategy instance = SpringUtils.getBean(beanName);
        return instance.create(properties);
    }

    ChatLanguageModel create(String properties);
}
