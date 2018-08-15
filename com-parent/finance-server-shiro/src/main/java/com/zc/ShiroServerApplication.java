package com.zc;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = {"com.codingapi.tx","com.zc","com.alibaba"})
@MapperScan(basePackages = "com.zc.dao")
@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
@EnableTransactionManagement(order = 2)
@EnableAsync
@EnableScheduling
@EnableApolloConfig
@Configuration
public class ShiroServerApplication {

	//private ThreadLocal threadLocal=new ThreadLocal();

	/**
	 * 启动程序的入口
	 * @param args
	 */
	public static void main(String[] args){

		SpringApplication.run(ShiroServerApplication.class, args);
	}


	/**
	 * 设置tomcat端口号
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerFactory servletContainer(){
		TomcatEmbeddedServletContainerFactory factory=new TomcatEmbeddedServletContainerFactory();
		factory.setPort(10010);
		return factory;
	}
}
