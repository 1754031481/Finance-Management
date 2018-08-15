package com.zc.entity.wconfig;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @author  史云顺
 * @Description: W支付支付实体
 * @since 1.0,2018-04-16 11:57:08
 */
public class WPayConfig implements Serializable {

    private static final long serialVersionUID = 5245565736541079L;

    private Long id;//  主键ID

    private String createUser;// 创建用户

    private String createdIp;//床架ip

    private Date createdTime;//创建时间

    private Date updateTime;//更新时间

    private String merUrl;//商户地址

    private String merchantNo;//商户号

    private String method;//api

    private String name;//配置名称

    private String pageUrl;//同步地址

    private String privateKey;//私钥

    private String publicKey;//公钥

    private String wGetwayUrlDf;//代付请求地址

    private String wGetwayUrlZf;//支付请求地址

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreatedIp() {
        return this.createdIp;
    }

    public void setCreatedIp(String createdIp) {
        this.createdIp = createdIp;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMerUrl() {
        return this.merUrl;
    }

    public void setMerUrl(String merUrl) {
        this.merUrl = merUrl;
    }

    public String getMerchantNo() {
        return this.merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPageUrl() {
        return this.pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getWGetwayUrlDf() {
        return this.wGetwayUrlDf;
    }

    public void setWGetwayUrlDf(String wGetwayUrlDf) {
        this.wGetwayUrlDf = wGetwayUrlDf;
    }

    public String getWGetwayUrlZf() {
        return this.wGetwayUrlZf;
    }

    public void setWGetwayUrlZf(String wGetwayUrlZf) {
        this.wGetwayUrlZf = wGetwayUrlZf;
    }

}
