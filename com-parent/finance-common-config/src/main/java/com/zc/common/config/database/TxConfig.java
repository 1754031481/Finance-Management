package com.zc.common.config.database;


import com.zc.common.core.properties.InitParamc;
import org.springframework.context.annotation.Configuration;

/**
 * @author ywj
 * @since 1.8
 **/
//@PropertySource(value ="classpath:redis.properties")
@Configuration
public class TxConfig {

    private InitParamc initParamc = new InitParamc("/web_base/com-common-config/resources/tx.properties");

    private String url;
    private String dbType;

    public String getUrl() {
        return initParamc.getProperties().getProperty("url");
    }

    public String getDbType() {
        return initParamc.getProperties().getProperty("compensate.db.dbType");
    }
}
