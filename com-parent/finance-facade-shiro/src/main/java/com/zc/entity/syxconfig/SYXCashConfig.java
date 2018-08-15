package com.zc.entity.syxconfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="syx_cash_config")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class SYXCashConfig extends BaseIdEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String url;//请求路径
	private String queryurl;//代付查询url
	private String name;//支持的项目名称
	private String service;//接口名称 默认(agentpay)
	private String format;//返回格式  json或xml
	private String merchantId;//商户号
	private String signType;//签名类型
	private String inputCharset;//编码格式  UTF-8
	private String returnUrl;//项目回调地址
	private String payMethod;//支付方式--singleAgentPay
	private String publicSign;
	private String privateSign;
	
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getQueryurl() {
		return queryurl;
	}
	public void setQueryurl(String queryurl) {
		this.queryurl = queryurl;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getInputCharset() {
		return inputCharset;
	}
	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}
	public String getPublicSign() {
		return publicSign;
	}
	public void setPublicSign(String publicSign) {
		this.publicSign = publicSign;
	}
	public String getPrivateSign() {
		return privateSign;
	}
	public void setPrivateSign(String privateSign) {
		this.privateSign = privateSign;
	}
	
	
}
