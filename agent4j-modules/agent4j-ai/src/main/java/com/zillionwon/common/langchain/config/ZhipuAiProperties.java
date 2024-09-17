package com.zillionwon.common.langchain.config;

/**
 * 智谱清言配置项
 *
 * @author InwardFlow
 */
public class ZhipuAiProperties extends AiProperties {
    public ZhipuAiProperties(Long id, String apiKey, String modelName, boolean logRequests, boolean logResponses, Integer maxRetries, Double temperature, String baseUrl, String params) {
        super(id, apiKey, modelName, logRequests, logResponses, maxRetries, temperature, baseUrl, params);
    }
}
