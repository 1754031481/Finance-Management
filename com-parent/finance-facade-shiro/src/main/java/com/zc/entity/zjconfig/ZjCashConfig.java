package com.zc.entity.zjconfig;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @author  史云顺
 * @Description: 中金代付实体
 * @since 1.0,2018-04-18 10:05:01
 */
public class ZjCashConfig implements Serializable {

    private static final long serialVersionUID = 8383347674933081L;

    private Long id;//  主键id

    private String createUser;//创建用户

    private String createdIp;//创建ip

    private Date createdTime;//创建时间

    private Date updateTime;//更新时间

    private String accountType;//账户类型

    private String institutionid;//机构id

    private String method;//api

    private String name;//配置名称

    private String notificationurl;//接收支付成功通知的url（异步通知地址）

    private String pageUrl;//通知页面地址

    private String privatePfxMima;//商家私钥证书密码

    private String privatePfxUrl;//私钥证书路径

    private String publicCerUrl;//公钥证书路径

    private String rsaName;//rsa算法名

    private String settlementFlag;//

    private String wGetwayUrlDf;//代付请求路径

    private String wGetwayUrlZf;//支付请求路径

    private String paymentAccountName;//机构支付账户名称  写死

    private String paymentAccountNumber;//机构支付账户号码 写死

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

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getInstitutionid() {
        return this.institutionid;
    }

    public void setInstitutionid(String institutionid) {
        this.institutionid = institutionid;
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

    public String getNotificationurl() {
        return this.notificationurl;
    }

    public void setNotificationurl(String notificationurl) {
        this.notificationurl = notificationurl;
    }

    public String getPageUrl() {
        return this.pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPrivatePfxMima() {
        return this.privatePfxMima;
    }

    public void setPrivatePfxMima(String privatePfxMima) {
        this.privatePfxMima = privatePfxMima;
    }

    public String getPrivatePfxUrl() {
        return this.privatePfxUrl;
    }

    public void setPrivatePfxUrl(String privatePfxUrl) {
        this.privatePfxUrl = privatePfxUrl;
    }

    public String getPublicCerUrl() {
        return this.publicCerUrl;
    }

    public void setPublicCerUrl(String publicCerUrl) {
        this.publicCerUrl = publicCerUrl;
    }

    public String getRsaName() {
        return this.rsaName;
    }

    public void setRsaName(String rsaName) {
        this.rsaName = rsaName;
    }

    public String getSettlementFlag() {
        return this.settlementFlag;
    }

    public void setSettlementFlag(String settlementFlag) {
        this.settlementFlag = settlementFlag;
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

    public String getPaymentAccountName() {
        return this.paymentAccountName;
    }

    public void setPaymentAccountName(String paymentAccountName) {
        this.paymentAccountName = paymentAccountName;
    }

    public String getPaymentAccountNumber() {
        return this.paymentAccountNumber;
    }

    public void setPaymentAccountNumber(String paymentAccountNumber) {
        this.paymentAccountNumber = paymentAccountNumber;
    }

}
