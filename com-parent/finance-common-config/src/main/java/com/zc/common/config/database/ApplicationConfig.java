package com.zc.common.config.database;


import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.zc.common.core.properties.InitParamc;
import org.springframework.context.annotation.Configuration;

/**
 * @description ：读取properties文件
 * @Created by  : Moya
 * @Creation Date ： 2018/1/24 11:52
 * @version
 * @param :
 */
//@PropertySource(value ="classpath:redis.properties")
@Configuration
public class ApplicationConfig {
//    private InitParamc initParamc = new InitParamc("c:/base/web_base/com-common-config/resources/application.properties");
      private InitParamc initParamc = new InitParamc("/root/base/web_base/com-common-config/resources/application.properties");
    @ApolloConfig // 指定获取公共的资源库
            Config apolloConfig;


    /**
     * redis配置
     */
    private String redisHost;
    private String redisDatabase;
    private String redisPort;
    private String redisPassword;
    private String redisTimeout;
    private String redisPoolMaxWait;
    private String redisPoolMaxIdle;
    private String redisPoolMinWait;
    /**
     * 日志配置
     */
    private String loggingZookeeper;
    private String loggingAlibaba;
    /**
     * mybatis扫描xml路径
     */
    private String mybatisPackage;
    private String mybatisLocations;
    /**
     * Mapper配置
     */
    private String mappers;
    private String notEmpty;
    private String identity;
    /**
     * pageHelper分页数据配置
     */
    private String pagehelperHelperDialect;
    private String pagehelperReasonable;
    private String pagehelperSupportMethodsArguments;
    private String pagehelperParams;


    private Config getAbolConfig(){
        return  ConfigService.getAppConfig();
    }


    public String getRedisHost() {
        apolloConfig = getAbolConfig();

        return apolloConfig.getProperty("spring.redis.host",initParamc.getProperties().getProperty("spring.redis.host"));
    }

    public String getRedisDatabase() {
        apolloConfig = getAbolConfig();

        return apolloConfig.getProperty("spring.redis.database",initParamc.getProperties().getProperty("spring.redis.database"));
    }

    public String getRedisPort() {
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("spring.redis.port",initParamc.getProperties().getProperty("spring.redis.port"));
    }

    public String getRedisPassword(){
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("spring.redis.password",initParamc.getProperties().getProperty("spring.redis.password"));

    }

    public String getRedisTimeout() {
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("spring.redis.timeout",initParamc.getProperties().getProperty("spring.redis.timeout"));
    }

    public String getRedisPoolMaxWait() {

        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("spring.redis.pool.max-wait",initParamc.getProperties().getProperty("spring.redis.pool.max-wait"));
    }

    public String getRedisPoolMaxIdle() {
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("spring.redis.pool.max-idle",initParamc.getProperties().getProperty("spring.redis.pool.max-idle"));
    }

    public String getRedisPoolMinWait() {
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("spring.redis.pool.min-idle",initParamc.getProperties().getProperty("spring.redis.pool.min-idle"));
    }

    public String getLoggingZookeeper() {
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("logging.level.org.apache.zookeeper",initParamc.getProperties().getProperty("logging.level.org.apache.zookeeper"));
    }

    public String getLoggingAlibaba() {

        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("logging.level.com.alibaba",initParamc.getProperties().getProperty("logging.level.com.alibaba"));

    }

    public String getMybatisPackage() {
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("mybatis.type-aliases-package",initParamc.getProperties().getProperty("mybatis.type-aliases-package"));
    }

    public String getMybatisLocations() {
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("mybatis.mapper-locations",initParamc.getProperties().getProperty("mybatis.mapper-locations"));
    }

    public String getMappers() {
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("mapper.mappers",initParamc.getProperties().getProperty("mapper.mappers"));
    }

    public String getNotEmpty() {
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("mapper.not-empty",initParamc.getProperties().getProperty("mapper.not-empty"));
    }

    public String getIdentity()
    {
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("mapper.identity",initParamc.getProperties().getProperty("mapper.identity"));
    }

    public String getPagehelperHelperDialect() {
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("pagehelper.helperDialect",initParamc.getProperties().getProperty("pagehelper.helperDialect"));
    }

    public String getPagehelperReasonable() {
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("pagehelper.reasonable",initParamc.getProperties().getProperty("pagehelper.reasonable"));
    }

    public String getPagehelperSupportMethodsArguments() {
        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("pagehelper.supportMethodsArguments",initParamc.getProperties().getProperty("pagehelper.supportMethodsArguments"));

    }

    public String getPagehelperParams() {

        apolloConfig = getAbolConfig();
        return apolloConfig.getProperty("pagehelper.params",initParamc.getProperties().getProperty("pagehelper.params"));

    }


}
