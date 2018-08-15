package com.zc.common.core.aspect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *  @author  chen
 */
@Configuration
@PropertySource(value ="classpath:redis.properties")
public class RedisConfigrtion {

	@Value("${spring.redis.port}")
	private String port;
	@Value("${spring.redis.host}")
	private String host;

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}