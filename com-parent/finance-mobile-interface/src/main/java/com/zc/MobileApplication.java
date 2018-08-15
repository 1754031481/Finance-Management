package com.zc;

import com.zc.common.util.spring.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

/**
 * Project Name:com-server-main
 * File Name:HehuoApplication.java
 * Package Name:
 * Date:2017年8月21日下午8:32:22
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

/**
 * ClassName:MainApplication <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月21日 下午8:32:22 <br/>
 * @author   张灿
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@ServletComponentScan
@SpringBootApplication(scanBasePackages = {"com.codingapi.tx","com.zc"})
public class MobileApplication {
	/**
	 * 启动程序的入口
	 * @param args
	 */ 
	public static void main(String[] args){
		SpringContextUtil.setApplicationContext(SpringApplication.run(MobileApplication.class, args));
	}

	/**
	 * 设置tomcat端口号
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerFactory servletContainer(){
		TomcatEmbeddedServletContainerFactory factory=new TomcatEmbeddedServletContainerFactory();
		factory.setPort(10001);
		return factory;
	}
}
