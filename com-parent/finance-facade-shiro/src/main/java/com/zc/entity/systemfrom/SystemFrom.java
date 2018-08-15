package com.zc.entity.systemfrom;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;

/**
 * @description: 支付支持接口项目
 * @author:  ZhaoJunBiao
 * @date:  2018-04-13 16:41
 * @version: 1.0.0
 */
@Entity
@Table(name = "system_from")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class SystemFrom extends BaseIdEntity {

	private static final long serialVersionUID = 1L;

	private String name;// 支持的项目名称

	private String systemFromName;// 支持的项目名称

	private String ThirdPayTypePay;// 项目要使用的支付接口名称

	private String ThirdPayTypeCash;// 项目要使用的代付接口名称

	private String systemFromAynUrl;// 项目异步通知地址

	private String systemReturnUrl;// 项目异步通知地址

	private String merchantNo;// 商户号

	private String privateKey; // 私钥

	private String publicKey; // 公钥

	private String supportStatue;// 0 支持 1不支持

	private String isShuntingPay;//是否分流支付 true是

	private String isShuntingCash;//是否分流代付 true是

	private String ip;//请求真实Ip


	public String getIsShuntingCash() {
		return isShuntingCash;
	}

	public void setIsShuntingCash(String isShuntingCash) {
		this.isShuntingCash = isShuntingCash;
	}

	public String getIsShuntingPay() {
		return isShuntingPay;
	}

	public void setIsShuntingPay(String isShuntingPay) {
		this.isShuntingPay = isShuntingPay;
	}

	public String getSystemReturnUrl() {
		return systemReturnUrl;
	}

	public void setSystemReturnUrl(String systemReturnUrl) {
		this.systemReturnUrl = systemReturnUrl;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getSupportStatue() {
		return supportStatue;
	}

	public void setSupportStatue(String supportStatue) {
		this.supportStatue = supportStatue;
	}

	public String getThirdPayTypePay() {
		return ThirdPayTypePay;
	}

	public void setThirdPayTypePay(String thirdPayTypePay) {
		ThirdPayTypePay = thirdPayTypePay;
	}

	public String getThirdPayTypeCash() {
		return ThirdPayTypeCash;
	}

	public void setThirdPayTypeCash(String thirdPayTypeCash) {
		ThirdPayTypeCash = thirdPayTypeCash;
	}

	public String getSystemFromAynUrl() {
		return systemFromAynUrl;
	}

	public void setSystemFromAynUrl(String systemFromAynUrl) {
		this.systemFromAynUrl = systemFromAynUrl;
	}

	public String getSystemFromName() {
		return systemFromName;
	}

	public void setSystemFromName(String systemFromName) {
		this.systemFromName = systemFromName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
