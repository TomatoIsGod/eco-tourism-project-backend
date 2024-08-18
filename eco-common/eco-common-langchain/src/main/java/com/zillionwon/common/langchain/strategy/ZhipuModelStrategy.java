package com.zillionwon.common.langchain.strategy;

import com.zillionwon.common.core.util.JsonUtils;
import com.zillionwon.common.langchain.config.IModelStrategy;
import com.zillionwon.common.langchain.config.ZhipuModelConfig;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.zhipu.ZhipuAiChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 智谱清言实现类
 *
 * @author InwardFlow
 */

@Slf4j
@Service("zhipu" + IModelStrategy.BASE_NAME)
public class ZhipuModelStrategy implements IModelStrategy{

    @Override
    public ChatLanguageModel create(String properties) {
        ZhipuModelConfig config = JsonUtils.parseObject(properties, ZhipuModelConfig.class);
        return ZhipuAiChatModel.builder()
                .apiKey(Objects.requireNonNull(config).getApiKey())
                .model(config.getModel())
                .logRequests(config.isLogRequests())
                .logResponses(config.isLogResponses())
                .temperature(config.getTemperature())
                .maxRetries(config.getMaxRetries())
                .baseUrl(config.getBaseUrl())
                .build();
    }
}
