package com.zillionwon.common.langchain.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 大语言模型配置类
 *
 * @author InwardFlow
 */
@Data
@AllArgsConstructor
public abstract class AiModelConfig {

    private Long id;
    private String apiKey;
    private String modelName;
    private boolean logRequests;
    private boolean logResponses;
    private Integer maxRetries;
    private Double temperature;
    private String baseUrl;
}
