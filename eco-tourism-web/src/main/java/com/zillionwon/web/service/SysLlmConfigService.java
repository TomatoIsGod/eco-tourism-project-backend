package com.zillionwon.web.service;

import com.zillionwon.common.core.util.CacheUtils;
import com.zillionwon.common.core.util.JsonUtils;
import com.zillionwon.common.jedis.util.JedisUtils;
import com.zillionwon.common.langchain.constant.AiConstant;
import com.zillionwon.web.domain.SysLlmConfig;
import com.zillionwon.web.mapper.SysLlmConfigMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模型配置Service接口
 *
 * @author InwardFlow
 */
@Service
@RequiredArgsConstructor
public class SysLlmConfigService {

    private final SysLlmConfigMapper baseMapper;

    /**
     * 初始化模型配置
     */
    public void init() {
        // 项目启动时，初始化参数到缓存，加载配置类
        List<SysLlmConfig> list = baseMapper.selectList();
        // 加载OSS初始化配置
        for (SysLlmConfig config : list) {
            String configKey = config.getConfigKey();
            // TODO: is default
            if ("0".equals(config.getStatus())) {
                JedisUtils.set(AiConstant.DEFAULT_CONFIG_KEY, configKey);
            }
            CacheUtils.put(AiConstant.CONFIG_PREFIX, config.getConfigKey(), JsonUtils.toJsonString(config));
        }
    }
}
