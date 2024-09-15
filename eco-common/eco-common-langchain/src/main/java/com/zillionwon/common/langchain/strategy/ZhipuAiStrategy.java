package com.zillionwon.common.langchain.strategy;

import com.zillionwon.common.core.util.JsonUtils;
import com.zillionwon.common.langchain.config.ZhipuAiProperties;
import com.zillionwon.common.langchain.model.ChatLanguageModelDecorator;
import dev.langchain4j.model.zhipu.ZhipuAiChatModel;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * 智谱清言实现类
 *
 * @author InwardFlow
 */

@Slf4j
@Service("zhipu" + AiStrategy.BASE_NAME)
public class ZhipuAiStrategy implements AiStrategy {

    @Override
    public ChatLanguageModelDecorator create(String properties) {
        ZhipuAiProperties config = JsonUtils.parseObject(properties, ZhipuAiProperties.class);
        return new ChatLanguageModelDecorator(ZhipuAiChatModel.builder()
                .apiKey(Objects.requireNonNull(config).getApiKey())
                .model(config.getModelName())
                .logRequests(config.isLogRequests())
                .logResponses(config.isLogResponses())
                .temperature(config.getTemperature())
                .maxRetries(config.getMaxRetries())
                // 如果 URL 为空, 则返回 null
                .baseUrl(Optional.ofNullable(config.getBaseUrl())
                        .filter(StringUtils::isNotBlank)
                        .orElse(null))
                .build(), config);
    }
}
