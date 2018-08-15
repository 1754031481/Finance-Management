package com.zc.entity.ysconfig;

import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 杨文杰
 * @Title: YSPayConfig
 * @Description: 银盛支付实体
 * @date 2018/4/11
 */
@Table(name = "ys_pay_config")
public class YSPayConfig extends BaseIdEntity implements Serializable {

    /**
     * 商户号
     */
    @Column(name = "business_code")
    private String businessCode;

    /**
     * 编码
     */
    @Column(name = "charset")
    private String charset;

    /**
     * api
     */
    @Column(name = "method")
    private String method;

    /**
     * 配置名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 回调地址
     */
    @Column(name = "notify_url")
    private String notifyUrl;

    /**
     * 合作者ID
     */
    @Column(name = "partner_id")
    private String partnerId;

    /**
     * 商户证书名称
     */
    @Column(name = "private_pfx_mima")
    private String privatePfxMima;

    /**
     * 商户证书地址
     */
    @Column(name = "private_pfx_url")
    private String privatePfxUrl;

    /**
     * 业务证书地址
     */
    @Column(name = "public_cer_url")
    private String publicCerUrl;

    /**
     * 跳转地址
     */
    @Column(name = "return_url")
    private String returnUrl;

    /**
     * RSA格式
     */
    @Column(name = "rsa_name")
    private String rsaName;

    /**
     * 商家ID
     */
    @Column(name = "seller_id")
    private String sellerId;

    /**
     * 商家名称
     */
    @Column(name = "seller_name")
    private String sellerName;

    /**
     * 签名类型
     */
    @Column(name = "sign_type")
    private String signType;

    /**
     * 描述
     */
    @Column(name = "subject")
    private String subject;

    /**
     * 版本
     */
    @Column(name = "version")
    private String version;

    /**
     * 代付请求地址
     */
    @Column(name = "ys_getway_url_df")
    private String ysGetwayUrlDf;

    /**
     * 支付请求地址
     */
    @Column(name = "ys_getway_url_zf")
    private String ysGetwayUrlZf;

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrivatePfxMima() {
        return privatePfxMima;
    }

    public void setPrivatePfxMima(String privatePfxMima) {
        this.privatePfxMima = privatePfxMima;
    }

    public String getPrivatePfxUrl() {
        return privatePfxUrl;
    }

    public void setPrivatePfxUrl(String privatePfxUrl) {
        this.privatePfxUrl = privatePfxUrl;
    }

    public String getPublicCerUrl() {
        return publicCerUrl;
    }

    public void setPublicCerUrl(String publicCerUrl) {
        this.publicCerUrl = publicCerUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getRsaName() {
        return rsaName;
    }

    public void setRsaName(String rsaName) {
        this.rsaName = rsaName;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getYsGetwayUrlDf() {
        return ysGetwayUrlDf;
    }

    public void setYsGetwayUrlDf(String ysGetwayUrlDf) {
        this.ysGetwayUrlDf = ysGetwayUrlDf;
    }

    public String getYsGetwayUrlZf() {
        return ysGetwayUrlZf;
    }

    public void setYsGetwayUrlZf(String ysGetwayUrlZf) {
        this.ysGetwayUrlZf = ysGetwayUrlZf;
    }
}
