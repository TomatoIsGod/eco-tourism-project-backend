package com.zillionwon.common.langchain.model;

import com.zillionwon.common.langchain.config.AiProperties;
import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ChatLanguageModel 装饰器接口
 * @see ChatLanguageModel
 * @author InwardFlow
 */
@Data
@AllArgsConstructor
public class ChatLanguageModelDecorator implements WithAiProperties {

    private ChatLanguageModel chatLanguageModel;
    private AiProperties properties;

    @Override
    public void setProperties(AiProperties properties) {
        this.properties = properties;
    }

    @Override
    public AiProperties getProperties() {
        return properties;
    }

    @Override
    public boolean propertiesEqual(AiProperties properties) {
        return this.properties.equals(properties);
    }
}
