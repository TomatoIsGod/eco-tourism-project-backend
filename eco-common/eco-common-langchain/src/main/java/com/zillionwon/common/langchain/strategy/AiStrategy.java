package com.zillionwon.common.langchain.strategy;

import com.zillionwon.common.core.util.SpringUtils;
import com.zillionwon.common.langchain.model.ChatLanguageModelDecorator;

/**
 * 大语言模型逻辑
 *
 * @author InwardFlow
 */

public interface AiStrategy {

    String BASE_NAME = "AiStrategy";

    static ChatLanguageModelDecorator create(String configKey, String properties) {
        // 授权类型和客户端id
        String beanName = configKey + BASE_NAME;
        if (!SpringUtils.containsBean(beanName)) {
            throw new RuntimeException("授权类型不正确!");
        }
        AiStrategy instance = SpringUtils.getBean(beanName);
        return instance.create(properties);
    }

    ChatLanguageModelDecorator create(String properties);
}
