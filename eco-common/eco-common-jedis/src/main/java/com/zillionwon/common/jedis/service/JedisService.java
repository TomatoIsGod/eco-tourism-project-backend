package com.zillionwon.common.jedis.service;

import com.zillionwon.common.core.util.JsonUtils;
import com.zillionwon.common.jedis.config.JedisConfig;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * Redis 服务
 *
 * @author InwardFlow
 */

@Service
public class JedisService {

    private final Jedis jedis;

    private final JedisConfig jedisConfig;

    public JedisService(Jedis jedis, JedisConfig jedisConfig) {
        this.jedis = jedis;
        this.jedisConfig = jedisConfig;
    }

    public void set(String key, Object value, int index) {
        jedis.select(index);
        jedis.set(key, JsonUtils.toJsonString(value));
    }

    public Object get(String key, int index) {
        jedis.select(index);
        return jedis.get(key);
    }

    public Object get(String key) {
        return get(key, jedisConfig.getDatabase());
    }

    public void set(String key, Object value) {
        set(key, value, jedisConfig.getDatabase());
    }

}
