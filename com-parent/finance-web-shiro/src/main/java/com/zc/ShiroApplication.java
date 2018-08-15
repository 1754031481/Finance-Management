package com.zc;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.zc.common.util.spring.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@ServletComponentScan
@SuppressWarnings("ALL")
@SpringBootApplication(scanBasePackages = {"com.codingapi.tx","com.zc"})
@EnableScheduling
@EnableApolloConfig
@Configuration
public class ShiroApplication {

	/**
	 * 启动程序的入口
	 * @param args
	 */
	public static void main(String[] args){
		SpringContextUtil.setApplicationContext(SpringApplication.run(ShiroApplication.class, args));
	}

}