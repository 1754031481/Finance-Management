package com.zc.entity.chconfig;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @author  史云顺
 * @Description: 传化代付实体
 * @since 1.0,2018-04-12 14:14:15
 */
public class CHCashConfig implements Serializable {

    private static final long serialVersionUID = 6690118752841684L;

    private Long id;//   主键id

    private String createUser;//   创建id

    private String createdIp;//    创建ip

    private Date createdTime;//   创建时间

    private Date updateTime;//    更新时间

    private String appid;//   商户id

    private String backurl;//   回调地址

    private String bankaccounttype;//  卡种

    private String bankcardtype;//  卡类

    private String dogSk;// 网关码

    private String fromaccountnumber;//  账户号

    private String name;//  配置名称

    private String rootUrl;//  请求地址

    private String serviceId;//  服务id

    private String subject;//  描述

    private String terminal;//  终端

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

    public String getAppid() {
        return this.appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getBackurl() {
        return this.backurl;
    }

    public void setBackurl(String backurl) {
        this.backurl = backurl;
    }

    public String getBankaccounttype() {
        return this.bankaccounttype;
    }

    public void setBankaccounttype(String bankaccounttype) {
        this.bankaccounttype = bankaccounttype;
    }

    public String getBankcardtype() {
        return this.bankcardtype;
    }

    public void setBankcardtype(String bankcardtype) {
        this.bankcardtype = bankcardtype;
    }

    public String getDogSk() {
        return this.dogSk;
    }

    public void setDogSk(String dogSk) {
        this.dogSk = dogSk;
    }

    public String getFromaccountnumber() {
        return this.fromaccountnumber;
    }

    public void setFromaccountnumber(String fromaccountnumber) {
        this.fromaccountnumber = fromaccountnumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRootUrl() {
        return this.rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTerminal() {
        return this.terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

}
