package com.zc.vo.configuration;

import java.io.Serializable;

/**
 * @author: 王楠
 * @version:
 * @Description: 中信扫码列表VO
 **/
public class CITICQRcodeVO implements Serializable {

    /*主键id*/
    private Long id;

    /*商户id*/
    private String merchantId;

    /*通知地址*/
    private String notifyUrl;

    /*公钥*/
    private String publicKey;

    /*请求地址*/
    private String rootUrl;

    /*服务类型*/
    private String serviceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "CITICQRcodeVO{" +
                "id=" + id +
                ", merchantId='" + merchantId + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", rootUrl='" + rootUrl + '\'' +
                ", serviceType='" + serviceType + '\'' +
                '}';
    }
}
