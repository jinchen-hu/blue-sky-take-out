package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.redis")
@Data
public class RedisProperties {
    private String host;
    private int port;
    private String password;
    private int database;
}
