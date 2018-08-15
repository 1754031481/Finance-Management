package com.zc.entity.wjconfig;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @author  史云顺
 * @Description: 维基支付实体
 * @since 1.0,2018-04-19 09:54:45
 */
public class WjPayConfig implements Serializable {

    private static final long serialVersionUID = 4828413032391955L;

    private Long id;//主键id

    private String createUser;//创建用户

    private String createdIp;//创建IP

    private Date createdTime;//  创建时间

    private Date updateTime;//  更新时间

    private String name;//商户名称

    private String merchantNo;//商户编号

    private String cardType;//支付卡类型 cc信用卡   dc借记卡

    private String orderSubject;//订单描述

    private String notifyUrl;//异步回调地址

    private String returnUrl;//页面回调地址

    private String publicKey;//商户公钥

    private String requestAddress;//请求地址

    private String queryAddress;//订单查询地址

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreatedIp() {
        return createdIp;
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

    public String getMerchantNo() {
        return this.merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getOrderSubject() {
        return this.orderSubject;
    }

    public void setOrderSubject(String orderSubject) {
        this.orderSubject = orderSubject;
    }

    public String getNotifyUrl() {
        return this.notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return this.returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getRequestAddress() {
        return this.requestAddress;
    }

    public void setRequestAddress(String requestAddress) {
        this.requestAddress = requestAddress;
    }

    public String getQueryAddress() {
        return this.queryAddress;
    }

    public void setQueryAddress(String queryAddress) {
        this.queryAddress = queryAddress;
    }

}
