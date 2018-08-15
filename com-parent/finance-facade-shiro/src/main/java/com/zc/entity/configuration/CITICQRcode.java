package com.zc.entity.configuration;

import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author: 王楠
 * @version:
 * @Description: 中信扫码渠道实体
 **/
@Entity
@Table(name = "zx_bank_nativa")
public class CITICQRcode extends BaseIdEntity {

    /*商户id*/
    @Column(name = "mch_id")
    private String merchantId;

    /*通知地址*/
    @Column(name = "notify_url")
    private String notifyUrl;

    /*公钥*/
    @Column(name = "public_keys")
    private String publicKey;

    /*请求地址*/
    @Column(name = "root_url")
    private String rootUrl;

    /*服务类型*/
    @Column(name = "service")
    private String serviceType;


    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public String toString() {
        return "CITICQRcode{" +
                "merchantId='" + merchantId + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", rootUrl='" + rootUrl + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", id=" + id +
                ", createUser='" + createUser + '\'' +
                ", createdTime=" + createdTime +
                ", updateTime=" + updateTime +
                ", createdIp='" + createdIp + '\'' +
                '}';
    }
}
