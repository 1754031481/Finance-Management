package com.zc.entity.ffconfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ff_cash_config")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class FFCashConfig extends BaseIdEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String reqType;//请求类型
	private String merID;//商户号
	private String userId;//商户系统用户号
	private String rerUrl;//异步通知地址
	private String privateKey;//私钥
	private String rootUrl;//请求地址
	private String voucher;//证件类型  身份证
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVoucher() {
		return voucher;
	}
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getMerID() {
		return merID;
	}
	public void setMerID(String merID) {
		this.merID = merID;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRerUrl() {
		return rerUrl;
	}
	public void setRerUrl(String rerUrl) {
		this.rerUrl = rerUrl;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getRootUrl() {
		return rootUrl;
	}
	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}
	
}
