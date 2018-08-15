package com.zc.entity.finance;

import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 杨文杰
 * @Title: wxPayConfig
 * @Description: 微信配置实体
 * @date 2018/4/9
 */
@Table(name = "wx_pay_config")
public class WxPayConfig extends BaseIdEntity implements Serializable {

    /**
     * 商户号
     */
    @Column(name = "wx_merchant_number")
    private String wxMerchantNumber;

    /**
     * 通知地址
     */
    @Column(name = "wx_notifys_address")
    private String wxNotifysAddress;

    /**
     * 请求地址
     */
    @Column(name = "wx_notifys_order_address")
    private String wxNotifysOrderAddress;

    /**
     * openid
     */
    @Column(name = "wx_open_app_id")
    private String wxOpenAppId;

    /**
     * Publicappid
     */
    @Column(name = "wx_publicappid")
    private String wxPublicappid;

    /**
     * 私钥
     */
    @Column(name = "wxkey")
    private String wxkey;

    /**
     * 配置名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 系统ID
     */
    @Column(name = "system_from_id")
    private Long systemFromId;

    /**
     * 查询订单地址
     */
    @Column(name = "wx_query_order_address")

    private String wxQueryOrderAddress;

    public String getWxMerchantNumber() {
        return wxMerchantNumber;
    }

    public void setWxMerchantNumber(String wxMerchantNumber) {
        this.wxMerchantNumber = wxMerchantNumber;
    }

    public String getWxNotifysAddress() {
        return wxNotifysAddress;
    }

    public void setWxNotifysAddress(String wxNotifysAddress) {
        this.wxNotifysAddress = wxNotifysAddress;
    }

    public String getWxNotifysOrderAddress() {
        return wxNotifysOrderAddress;
    }

    public void setWxNotifysOrderAddress(String wxNotifysOrderAddress) {
        this.wxNotifysOrderAddress = wxNotifysOrderAddress;
    }

    public String getWxOpenAppId() {
        return wxOpenAppId;
    }

    public void setWxOpenAppId(String wxOpenAppId) {
        this.wxOpenAppId = wxOpenAppId;
    }

    public String getWxPublicappid() {
        return wxPublicappid;
    }

    public void setWxPublicappid(String wxPublicappid) {
        this.wxPublicappid = wxPublicappid;
    }

    public String getWxkey() {
        return wxkey;
    }

    public void setWxkey(String wxkey) {
        this.wxkey = wxkey;
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

    public String getWxQueryOrderAddress() {
        return wxQueryOrderAddress;
    }

    public void setWxQueryOrderAddress(String wxQueryOrderAddress) {
        this.wxQueryOrderAddress = wxQueryOrderAddress;
    }
}
