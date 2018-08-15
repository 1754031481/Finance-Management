package com.zc.entity.wjconfig;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @author  史云顺
 * @Description: 维基代付实体
 * @since 1.0,2018-04-20 09:51:28
 */
public class WjCashConfig implements Serializable {

    private static final long serialVersionUID = 3193262953639441L;

    private Long id;//主键ids

    private String createUser;//创建用户

    private String createdIp;//创建ip

    private Date createdTime;//创建时间

    private Date updateTime;//更新时间

    private String name;//配置名称

    private String method;//接口名称

    private String merchantNo;//商户编号

    private String cardType;//支付卡类型

    private String bankAccountType;//收款银行账户类型   BC，银行对公；BU：银行对私

    private String orderSubject;//订单描述

    private String notifyUrl;//异步通知地址

    private String publicKey;//秘钥

    private String requestAdressDf;//代付请求地址

    private String queryAddress;//代付查询地址

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public String getBankAccountType() {
        return this.bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
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

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getRequestAdressDf() {
        return this.requestAdressDf;
    }

    public void setRequestAdressDf(String requestAdressDf) {
        this.requestAdressDf = requestAdressDf;
    }

    public String getQueryAddress() {
        return this.queryAddress;
    }

    public void setQueryAddress(String queryAddress) {
        this.queryAddress = queryAddress;
    }

}
