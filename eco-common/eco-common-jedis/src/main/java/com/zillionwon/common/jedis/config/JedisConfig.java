package com.zillionwon.common.jedis.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.util.Optional;

/**
 * Redis 配置项
 *
 * @author InwardFlow
 */

@Data
@Configuration
public class JedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Value("${spring.data.redis.timeout}")
    private Duration timeout;

    @Value("${spring.data.redis.password}")
    private String password;

    @Value("${spring.data.redis.database}")
    private int database;

    @Bean(destroyMethod = "close")
    public Jedis jedis() {
        Jedis jedis = new Jedis(host, port);
        jedis.auth(Optional.ofNullable(password).orElse(""));
        jedis.getClient().setSoTimeout(timeout.toMillisPart());
        jedis.select(database);
        return jedis;
    }
}
