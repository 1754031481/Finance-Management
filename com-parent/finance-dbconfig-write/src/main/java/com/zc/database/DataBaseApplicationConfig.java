package com.zc.database;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.zc.common.core.property.DataSourceInit;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Moya on 2018/1/24.
 */
@Configuration
public class DataBaseApplicationConfig {

//    private DataSourceInit initParamc = new DataSourceInit("c:/base/web_base/com-dbconfig-write/resources/application-write.properties");
      private DataSourceInit initParamc = new DataSourceInit("/root/base/web_base/com-dbconfig-write/resources/application-write.properties");

    @ApolloConfig // 指定获取公共的资源库
            Config apolloConfig;




    private String springProfiles;
    private String datasourceUrl;
    private String datasourceUsername;
    private String datasourcePassword;
    private String driverClassName;
    private String type;
    private String druidFilters;
    private String druidMaxActive;
    private String druidInitialSize;
    private String druidMaxWait;
    private String druidMinIdle;
    private String druidTimeBetweenEvictionRunsMillis;
    private String druidMinEvictableIdleTimeMillis;
    private String druidValidationQuery;
    private String druidTestWhileIdle;
    private String druidTestOnBorrow;
    private String druiTestOnReturn;
    private String druidConnectionProperties;

    private Config getAbolConfig(){
        return  ConfigService.getAppConfig();
    }


    public String getSpringProfiles() {

        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.profiles",initParamc.getProperties().getProperty("spring.profiles"));
    }

    public String getDatasourceUrl() {
        apolloConfig = getAbolConfig();
        String property = apolloConfig.getProperty("spring.datasource.url", initParamc.getProperties().getProperty("spring.datasource.url"));

        String property1 = apolloConfig.getProperty("spring.datasource.url", "");
        return  property;
    }

    public String getDatasourceUsername() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.username",initParamc.getProperties().getProperty("spring.datasource.username"));
    }

    public String getDatasourcePassword() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.password",initParamc.getProperties().getProperty("spring.datasource.password"));
    }

    public String getDriverClassName() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.driver-class-name",initParamc.getProperties().getProperty("spring.datasource.driver-class-name"));

    }

    public String getType() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.type",initParamc.getProperties().getProperty("spring.datasource.type"));


    }

    public String getDruidFilters() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.druid.filters",initParamc.getProperties().getProperty("spring.datasource.druid.filters"));

    }

    public String getDruidMaxActive() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.druid.maxActive",initParamc.getProperties().getProperty("spring.datasource.druid.maxActive"));

    }

    public String getDruidInitialSize() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.druid.initialSize",initParamc.getProperties().getProperty("spring.datasource.druid.initialSize"));

    }

    public String getDruidMaxWait() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.druid.maxWait",initParamc.getProperties().getProperty("spring.datasource.druid.maxWait"));

    }

    public String getDruidMinIdle() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.druid.minIdle",initParamc.getProperties().getProperty("spring.datasource.druid.minIdle"));

    }

    public String getDruidTimeBetweenEvictionRunsMillis() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.druid.timeBetweenEvictionRunsMillis",initParamc.getProperties().getProperty("spring.datasource.druid.timeBetweenEvictionRunsMillis"));

    }

    public String getDruidMinEvictableIdleTimeMillis() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.druid.minEvictableIdleTimeMillis",initParamc.getProperties().getProperty("spring.datasource.druid.minEvictableIdleTimeMillis"));

    }

    public String getDruidValidationQuery() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.druid.validationQuery",initParamc.getProperties().getProperty("spring.datasource.druid.validationQuery"));

    }

    public String getDruidTestWhileIdle() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.druid.testWhileIdle",initParamc.getProperties().getProperty("spring.datasource.druid.testWhileIdle"));

    }

    public String getDruidTestOnBorrow() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.druid.testOnBorrow",initParamc.getProperties().getProperty("spring.datasource.druid.testOnBorrow"));
    }

    public String getDruiTestOnReturn() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.druid.testOnReturn",initParamc.getProperties().getProperty("spring.datasource.druid.testOnReturn"));

    }

    public String getDruidConnectionProperties() {
        apolloConfig = getAbolConfig();
        return  apolloConfig.getProperty("spring.datasource.druid.connectionProperties",initParamc.getProperties().getProperty("spring.datasource.druid.connectionProperties"));
    }
}
