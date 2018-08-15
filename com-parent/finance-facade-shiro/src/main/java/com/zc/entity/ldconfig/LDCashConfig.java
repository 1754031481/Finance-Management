package com.zc.entity.ldconfig;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;

/**
 * 联动优势代付
 * @Description: TODO
 * @author dinglanlan
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年9月11日 上午10:41:18
 * BeeCloud.registerApp(appId, testSecret, appSecret, masterSecret);
 */
@Entity
@Table(name = "ldys_cash_config")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class LDCashConfig extends BaseIdEntity {

	private static final long serialVersionUID = 1L;
	
	private String name;//支持项目描述
	
	private String appId;
	
	private String testSecret;//测试专用
	
	private String appSecret;
	
	private String masterSecret;
	
	private String tradeSource;//UTF8编码格式，目前只能填写OUT_PC
	
	private String cardType;//DE代表借记卡，CR代表信用卡，其他值为非法
	
	private String accountType;//帐户类型，P代表私户，C代表公户，其他值为非法
	
	private String channel;//BC_TRANSFER
	
	private String bcCashReturnUrl;//回调url
	
	
	
	public String getTradeSource() {
		return tradeSource;
	}
	public void setTradeSource(String tradeSource) {
		this.tradeSource = tradeSource;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBcCashReturnUrl() {
		return bcCashReturnUrl;
	}
	public void setBcCashReturnUrl(String bcCashReturnUrl) {
		this.bcCashReturnUrl = bcCashReturnUrl;
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
