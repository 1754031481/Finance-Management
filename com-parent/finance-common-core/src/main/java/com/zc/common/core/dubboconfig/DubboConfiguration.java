package com.zc.common.core.dubboconfig;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.zc.common.core.properties.InitParamc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @ClassName:  DubboConfiguration
 * @Description:member dubbo provider
 * @author: 李杰
 * @date:   2017年4月27日 上午11:08:00
 * @Copyright: 2017
 */
@Configuration
public class DubboConfiguration  {


	private static   InitParamc initParams = new InitParamc("");


	private String applicationName;

	private String protocol;

	private String registryAddress;

	private String protocolName;

	private int protocolPort;

	private int timeout;

	private int retries;

	private int delay;

	private String filter;

	private String vsersion;

	/**
	 * 设置dubbo扫描包
	 * @return
	 */
	@Bean
	public static AnnotationBean annotationBean() {
		AnnotationBean annotationBean = new AnnotationBean();
		annotationBean.setPackage(initParams.getProperties().getProperty("dubbo.annotation.package"));
		return annotationBean;
	}

    /**
     * 注入dubbo上下文
     *
     * @return
     */
    @Bean
	public ApplicationConfig applicationConfig() {
		// 当前应用配置
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName(getApplicationName());
		applicationConfig.setLogger("slf4j");
		return applicationConfig;
	}

    /**
     * 注入dubbo注册中心配置,基于zookeeper
     *
     * @return
     */
    @Bean
    public RegistryConfig registryConfig() {
        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol(getProtocol());
        registry.setAddress(getRegistryAddress());
        // 设置启动时不检查注册中心
        registry.setCheck(false);
        registry.setPort(getProtocolPort());
        return registry;
    }

    /**
     * dubbo监控中心
     * @return
     */
    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig mc = new MonitorConfig();
        return mc;
    }

    /**
     * 默认基于dubbo协议提供服务
     *
     * @return
     */
    @Bean
    public ProtocolConfig protocolConfig() {
        // 服务提供者协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(getProtocolName());
        protocolConfig.setPort(getProtocolPort());
        protocolConfig.setThreads(200);
        return protocolConfig;
    }

    /**
     * dubbo服务提供
     *
     * @param applicationConfig
     * @param registryConfig
     * @param protocolConfig
     * @return
     */
    @Bean(name = "commentProvider")
    public ProviderConfig providerConfig(ApplicationConfig applicationConfig, RegistryConfig registryConfig,
                                         ProtocolConfig protocolConfig, MonitorConfig monitorConfig) {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(getTimeout());
        providerConfig.setRetries(getRetries());
        providerConfig.setDelay(getDelay());
        providerConfig.setApplication(applicationConfig);
        providerConfig.setRegistry(registryConfig);
        providerConfig.setProtocol(protocolConfig);
        providerConfig.setMonitor(monitorConfig);
		providerConfig.setVersion(initParams.getProperties().getProperty("version"));
        return providerConfig;
    }

    /**
     * dubbo消费
     *
     * @param applicationConfig
     * @param registryConfig
     * @param monitorConfig
     * @return
     */
    @Bean(name="commentConsumer")
    public ConsumerConfig consumerConfig(ApplicationConfig applicationConfig, RegistryConfig registryConfig, MonitorConfig monitorConfig) {
        ConsumerConfig consumer = new ConsumerConfig();
        consumer.setApplication(applicationConfig);
        consumer.setRegistry(registryConfig);
        consumer.setMonitor(monitorConfig);
        //设置不检查服务提供者
        consumer.setCheck(false);
        consumer.setTimeout(360000);
        consumer.setRetries(0);
		consumer.setVersion(initParams.getProperties().getProperty("version"));
        return consumer;
    }

    /**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return initParams.getProperties().getProperty("dubbo.application.name");
	}

	/**
	 * @param applicationName the applicationName to set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return initParams.getProperties().getProperty("dubbo.registry.protocol");
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the registryAddress
	 */
	public String getRegistryAddress() {
		return initParams.getProperties().getProperty("dubbo.registry.address");
	}

	/**
	 * @param registryAddress the registryAddress to set
	 */
	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	/**
	 * @return the protocolName
	 */
	public String getProtocolName() {
		return initParams.getProperties().getProperty("dubbo.protocol.name");
	}

	/**
	 * @param protocolName the protocolName to set
	 */
	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

	/**
	 * @return the protocolPort
	 */
	public int getProtocolPort() {
		return Integer.valueOf(initParams.getProperties().getProperty("dubbo.protocol.port"));
	}

	/**
	 * @param protocolPort the protocolPort to set
	 */
	public void setProtocolPort(int protocolPort) {
		this.protocolPort = protocolPort;
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return Integer.valueOf(initParams.getProperties().getProperty("dubbo.provider.timeout"));
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the retries
	 */
	public int getRetries() {
		return Integer.valueOf(initParams.getProperties().getProperty("dubbo.provider.retries"));
	}

	/**
	 * @param retries the retries to set
	 */
	public void setRetries(int retries) {
		this.retries = retries;
	}

	/**
	 * @return the delay
	 */
	public int getDelay() {
		return  Integer.valueOf(initParams.getProperties().getProperty("dubbo.provider.delay"));
	}

	/**
	 * @param delay the delay to set
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getFilter() {
		return initParams.getProperties().getProperty("dubbo.consumer.filter");
	}
}
