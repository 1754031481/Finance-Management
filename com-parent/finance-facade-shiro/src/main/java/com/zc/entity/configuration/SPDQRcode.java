package com.zc.entity.configuration;

import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author: 王楠
 * @version:
 * @Description: 浦发扫码渠道实体
 **/
@Entity
@Table(name = "pf_bank_nativa")
public class SPDQRcode extends BaseIdEntity {

    /**
     * 详情
     */
    @Column(name = "detail")
    private String detail;

    /**
     * 秘钥
     */
    @Column(name = "keya")
    private String secretKey;

    /**
     * 商户号
     */
    @Column(name = "mch_id")
    private String merchantId;

    /**
     * 跳转地址
     */
    @Column(name = "notify_url")
    private String notifyUrl;

    /**
     * 产品id
     */
    @Column(name = "product_id")
    private String productId;

    /**
     * 请求地址
     */
    @Column(name = "req_url")
    private String reqUrl;

    /**
     * 标识
     */
    @Column(name = "simit_pay")
    private String simitPay;

    /**
     * 交易ip
     */
    @Column(name = "spbill_createip")
    private String spbillCreateIp;

    /**
     * 交易类型
     */
    @Column(name = "trade_type")
    private String tradeType;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getSimitPay() {
        return simitPay;
    }

    public void setSimitPay(String simitPay) {
        this.simitPay = simitPay;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    @Override
    public String toString() {
        return "SPDQRcode{" +
                "detail='" + detail + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", productId='" + productId + '\'' +
                ", reqUrl='" + reqUrl + '\'' +
                ", simitPay='" + simitPay + '\'' +
                ", spbillCreateIp='" + spbillCreateIp + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", id=" + id +
                ", createUser='" + createUser + '\'' +
                ", createdTime=" + createdTime +
                ", updateTime=" + updateTime +
                ", createdIp='" + createdIp + '\'' +
                '}';
    }
}
