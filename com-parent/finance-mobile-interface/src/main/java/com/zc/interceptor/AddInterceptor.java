package com.zc.interceptor;

import com.zc.common.core.interceptor.SpringValidateSignatureInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2017/9/12.
 */
@Configuration
public class AddInterceptor extends WebMvcConfigurerAdapter {
    @Autowired
    private InterceptorConfig interceptorConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        /**
         *  手机端验签拦截器
         */
        registry.addInterceptor(new SpringValidateSignatureInterceptor(interceptorConfig.getMark(), interceptorConfig.getPublicurls()));
        /**
         *  PC端token防爆拦截器
         */
        //registry.addInterceptor(new AvoidDuplicateSubmissionInterceptor());
    }
}
