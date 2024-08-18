package com.zillionwon.common.langchain.config;

/**
 * 智谱清言配置项
 *
 * @author InwardFlow
 */
public class ZhipuModelConfig extends AiModelConfig {

    public ZhipuModelConfig(Long id, String apiKey, String model, boolean logRequests, boolean logResponses, Integer maxRetries, Double temperature, String baseUrl) {
        super(id, apiKey, model, logRequests, logResponses, maxRetries, temperature, baseUrl);
    }

}
