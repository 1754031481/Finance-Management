package com.zc.entity.ffconfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ff_pay_config")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class FFPayConfig extends BaseIdEntity{

	private static final long serialVersionUID = 1L;
	
	private String gateId;//网关号
	private String merId;//商户号
	private String liqType;//结算方式 T0 当天结算 T1 隔天结算
	private String reqUrl;//异步通知地址
	private String retUrl;//同步地址
	private String name;//支持的项目名称
	private String userId;//商户系统用户号
	private String reqType;//请求类型
	private String rootUrl;//请求服务器路径
	private String keyCheck;//秘钥
	private String bankUrl;
	
	public String getBankUrl() {
		return bankUrl;
	}
	public void setBankUrl(String bankUrl) {
		this.bankUrl = bankUrl;
	}
	public String getKeyCheck() {
		return keyCheck;
	}
	public void setKeyCheck(String keyCheck) {
		this.keyCheck = keyCheck;
	}
	public String getRootUrl() {
		return rootUrl;
	}
	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGateId() {
		return gateId;
	}
	public void setGateId(String gateId) {
		this.gateId = gateId;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getLiqType() {
		return liqType;
	}
	public void setLiqType(String liqType) {
		this.liqType = liqType;
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
}
