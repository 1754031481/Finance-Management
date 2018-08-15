package com.zc.entity.configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;


/**
 * @author: 王楠
 * @version:
 * @Description: 裕福支付配置实体
 **/
@Entity
@Table(name="yf_pay_config")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class YFPayConfig extends BaseIdEntity{

	@Column(name = "merchant_id")
	private String merchantId;//商户Id

	@Column(name = "merchant_order_currency")
	private String merchantOrderCurrency;//订单金额币种

	@Column(name = "gw_type")
	private String gwType;//网关类型

	@Column(name = "back_url")
	private String backUrl;//回调url

	@Column(name = "front_url")
	private String frontUrl;//返回url

	@Column(name = "user_type")
	private String userType;//买家类型 01-个人，02-企业

	@Column(name = "merchant_user_id")
	private String merchantUserId;//买家在商户平台唯一标识

	@Column(name = "merchant_settle_info")
	private String merchantSettleInfo;//商户结算信息

	@Column(name = "request_address")
	private String requestAddress;//请求地址;

	@Column(name = "zd_address")
	private String zdAddress;//置单请求地址；

	@Column(name = "version")
	private String version;//固定值：1.0.0

	@Column(name = "yufucerp_path")
	private String yufucerpPath; //裕福公钥路径

	@Column(name = "yufucerp_address")
	private String yufucerpAddress;//裕福私钥路径

	@Column(name = "private_sign")
	private String privateSign;//私钥密码 




	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantOrderCurrency() {
		return merchantOrderCurrency;
	}

	public void setMerchantOrderCurrency(String merchantOrderCurrency) {
		this.merchantOrderCurrency = merchantOrderCurrency;
	}

	public String getGwType() {
		return gwType;
	}

	public void setGwType(String gwType) {
		this.gwType = gwType;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getFrontUrl() {
		return frontUrl;
	}

	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getMerchantUserId() {
		return merchantUserId;
	}

	public void setMerchantUserId(String merchantUserId) {
		this.merchantUserId = merchantUserId;
	}

	public String getMerchantSettleInfo() {
		return merchantSettleInfo;
	}

	public void setMerchantSettleInfo(String merchantSettleInfo) {
		this.merchantSettleInfo = merchantSettleInfo;
	}

	public String getRequestAddress() {
		return requestAddress;
	}

	public void setRequestAddress(String requestAddress) {
		this.requestAddress = requestAddress;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getYufucerpPath() {
		return yufucerpPath;
	}

	public void setYufucerpPath(String yufucerpPath) {
		this.yufucerpPath = yufucerpPath;
	}

	public String getYufucerpAddress() {
		return yufucerpAddress;
	}

	public void setYufucerpAddress(String yufucerpAddress) {
		this.yufucerpAddress = yufucerpAddress;
	}

	public String getPrivateSign() {
		return privateSign;
	}

	public void setPrivateSign(String privateSign) {
		this.privateSign = privateSign;
	}

	public String getZdAddress() {
		return zdAddress;
	}

	public void setZdAddress(String zdAddress) {
		this.zdAddress = zdAddress;
	}

	@Override
	public String toString() {
		return "YFPayConfig{" +
				"merchantId='" + merchantId + '\'' +
				", merchantOrderCurrency='" + merchantOrderCurrency + '\'' +
				", gwType='" + gwType + '\'' +
				", backUrl='" + backUrl + '\'' +
				", frontUrl='" + frontUrl + '\'' +
				", userType='" + userType + '\'' +
				", merchantUserId='" + merchantUserId + '\'' +
				", merchantSettleInfo='" + merchantSettleInfo + '\'' +
				", requestAddress='" + requestAddress + '\'' +
				", zdAddress='" + zdAddress + '\'' +
				", version='" + version + '\'' +
				", yufucerpPath='" + yufucerpPath + '\'' +
				", yufucerpAddress='" + yufucerpAddress + '\'' +
				", privateSign='" + privateSign + '\'' +
				", id=" + id +
				", createUser='" + createUser + '\'' +
				", createdTime=" + createdTime +
				", updateTime=" + updateTime +
				", createdIp='" + createdIp + '\'' +
				'}';
	}
}
