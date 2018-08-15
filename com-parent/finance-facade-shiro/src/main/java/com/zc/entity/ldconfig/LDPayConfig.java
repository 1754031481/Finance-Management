package com.zc.entity.ldconfig;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;

/**
 * 联动优势支付
 * @Description: TODO
 * @author dinglanlan
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年9月11日 上午10:41:18
 * BeeCloud.registerApp(appId, testSecret, appSecret, masterSecret);
 */
@Entity
@Table(name = "ldys_pay_config")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class LDPayConfig extends BaseIdEntity {

	private static final long serialVersionUID = 1L;
	
	private String name;//支持项目描述
	
	private String appId;
	
	private String testSecret;//测试专用
	
	private String appSecret;//APP秘钥
	
	private String masterSecret;//主密钥
	
	private String channel;//渠道
	
	private String bcGatewayReturnUrl;//支付跳转地址
	
	private String notifyUrl;//通知地址
	
	
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getBcGatewayReturnUrl() {
		return bcGatewayReturnUrl;
	}
	public void setBcGatewayReturnUrl(String bcGatewayReturnUrl) {
		this.bcGatewayReturnUrl = bcGatewayReturnUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTestSecret() {
		return testSecret;
	}
	public void setTestSecret(String testSecret) {
		this.testSecret = testSecret;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getMasterSecret() {
		return masterSecret;
	}
	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
	
	
}
