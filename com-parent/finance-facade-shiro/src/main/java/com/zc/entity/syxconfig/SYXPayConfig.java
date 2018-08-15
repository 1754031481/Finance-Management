package com.zc.entity.syxconfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="syx_pay_config")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class SYXPayConfig extends BaseIdEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String name;//支持的项目名称
	private String service; //接口名称---目前只有directPay(必填)
	private String merchantId; //商户号---合作商户的商户号，由商银信支付公司分配(必填)
	private String notifyUrl;  //通知URL---针对该交易的交易状态同步通知接收URL(如不填写,则收不到同步通知)(非必填)
	private String returnUrl;  //返回URL---结果返回URL，仅适用于立即返回处理结果的接口。商银信支付处理完请求后，立即将处理结果返回给这个URL(如不填写，则不返回合作伙伴网站)(非必填)
	private String signType;  //签名类型---目前支持RSA、CA(必填)
	private String inputCharset;   //参数编码字符集---支持UTF-8和GBK编码
	private String gateway;//三方的访问路径
	private String verifyway;//三方的验签路径
	private String publicSign;
	private String privateSign;
	private String queryUrl;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVerifyway() {
		return verifyway;
	}
	public void setVerifyway(String verifyway) {
		this.verifyway = verifyway;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
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
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
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

	public String getQueryUrl() {
		return queryUrl;
	}

	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
}
