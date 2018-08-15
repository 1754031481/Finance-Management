package com.zc.entity.xsconfig;

import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 杨文杰
 * @Title: XSPayConfig
 * @Description: 新生支付实体
 * @date 2018/4/11
 */
@Table(name = "xs_pay_config")
public class XSPayConfig extends BaseIdEntity implements Serializable {

    /**
     * 别名
     */
    @Column(name = "alias")
    private String alias;

    /**
     * 编码
     */
    @Column(name = "charset")
    private String charset;

    /**
     * 货币编码
     */
    @Column(name = "currency_code")
    private String currencyCode;

    /**
     * 直接旗帜
     */
    @Column(name = "direct_flag")
    private String directFlag;

    /**
     * 身份类型
     */
    @Column(name = "identity_type")
    private String identityType;

    /**
     * 商户id
     */
    @Column(name = "mer_id")
    private String merId;

    /**
     * 渠道名称
     */
    @Column(name = "name")
    private String name;

    /**
     *支付类型
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     * 私钥地址
     */
    @Column(name = "private_url")
    private String privateUrl;

    /**
     * 公钥
     */
    @Column(name = "puliic_key")
    private String puliicKey;

    /**
     * 密码
     */
    @Column(name = "pwd")
    private String pwd;

    /**
     * 查询地址
     */
    @Column(name = "query_url")
    private String queryUrl;

    /**
     * 请求类型
     */
    @Column(name = "req_type")
    private String reqType;

    /**
     * 请求地址
     */
    @Column(name = "req_url")
    private String reqUrl;

    /**
     * 挑转地址
     */
    @Column(name = "ret_url")
    private String retUrl;

    /**
     * 下单地址
     */
    @Column(name = "root_url")
    private String rootUrl;

    /**
     * 签名类型
     */
    @Column(name = "sign_type")
    private String signType;

    /**
     * storepass
     */
    @Column(name = "storepass")
    private String storepass;

    /**
     * 交易类型
     */
    @Column(name = "tran_type")
    private String tranType;

    /**
     * 支付类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 版本号
     */
    @Column(name = "version")
    private String version;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDirectFlag() {
        return directFlag;
    }

    public void setDirectFlag(String directFlag) {
        this.directFlag = directFlag;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPrivateUrl() {
        return privateUrl;
    }

    public void setPrivateUrl(String privateUrl) {
        this.privateUrl = privateUrl;
    }

    public String getPuliicKey() {
        return puliicKey;
    }

    public void setPuliicKey(String puliicKey) {
        this.puliicKey = puliicKey;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getRetUrl() {
        return retUrl;
    }

    public void setRetUrl(String retUrl) {
        this.retUrl = retUrl;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getStorepass() {
        return storepass;
    }

    public void setStorepass(String storepass) {
        this.storepass = storepass;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
