package com.zc.interceptor;

import com.zc.common.core.init.InitDecodeParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author xwolf
 * @since 1.8
 **/
@PropertySource(value ="classpath:init.properties")
@Configuration
public class InterceptorConfig{

    @Value("${mark}")
    private String mark;

    @Value("${publicurl}")
    private String publicurl;

    private String[] publicurls;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setPublicurl(String publicurl) {
        this.publicurl = publicurl;
    }

    public String getPublicurl() {
        return publicurl;
    }

    public String[] getPublicurls() {
        if(!publicurl.equals(null)) {
            publicurls = publicurl.split(",");
        }
        return publicurls;
    }
}
