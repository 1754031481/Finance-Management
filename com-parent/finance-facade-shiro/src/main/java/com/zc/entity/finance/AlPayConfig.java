package com.zc.entity.finance;

import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 杨文杰
 * @Title: alPayConfig
 * @Description: 支付宝配置实体
 * @date 2018/4/9
 */
@Table(name = "al_pay_config")
public class AlPayConfig extends BaseIdEntity implements Serializable {

    /**
     * 支付宝公钥
     */
    @Column(name = "alipay_public_key")
    private String alipayPublicKey;

    /**
     * appid
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 应用私钥
     */
    @Column(name = "app_private_key")
    private String appPrivateKey;

    /**
     * 编码格式
     */
    @Column(name = "charset")
    private String charset;

    /**
     * 请求格式
     */
    @Column(name = "format")
    private String format;

    /**
     * 加签类型
     */
    @Column(name = "sign_type")
    private String signType;

    /**
     * 请求地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 应用公钥
     */
    @Column(name = "app_public_key")
    private String appPublicKey;

    /**
     * 配置名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 系统id
     */
    @Column(name = "system_from_id")
    private Long systemFromId;

    /**
     * 通知地址
     */
    @Column(name = "notify_url")
    private String notifyUrl;

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppPublicKey() {
        return appPublicKey;
    }

    public void setAppPublicKey(String appPublicKey) {
        this.appPublicKey = appPublicKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSystemFromId() {
        return systemFromId;
    }

    public void setSystemFromId(Long systemFromId) {
        this.systemFromId = systemFromId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
