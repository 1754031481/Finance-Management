package com.zc.entity.configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;


/**
 * @author: 王楠
 * @version:
 * @Description: 裕福代付配置实体
 **/
@Entity
@Table(name="yf_cash_config")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class YFCashConfig extends BaseIdEntity {

	@Column(name = "version")
	private String version;

	@Column(name = "merchant_id")
	private String merchantId;//商户号

	@Column(name = "merchant_order_time")
	private String merchantOrderTime;

	@Column(name = "batch_pay_file_name")
	private String batchPayFileName;//文件名称

	@Column(name = "batch_pay_file_path")
	private String batchPayFilePath;//申请代付文件路径

	@Column(name = "back_file_path")
	private String backFilePath;//回调代付文件路径

	@Column(name = "batch_pay_file_digest")
	private String batchPayFileDigest;

	@Column(name = "back_url")
	private String backUrl; //回调地址

	@Column(name = "request_url")
	private String requestUrl; //请求地址

	@Column(name = "query_url")
	private String queryUrl;//查询作业地址

	@Column(name = "query_task_url")
	private String queryTaskUrl;//查询作业文件是否生成地址

	@Column(name = "query_dwon_url")
	private String queryDwonUrl;//下载代付文件

	@Column(name = "yufucerp_path")
	private String yufucerpPath; //裕福公钥路径

	@Column(name = "yufucerp_address")
	private String yufucerpAddress;//裕福私钥路径

	@Column(name = "private_sign")
	private String privateSign;//私钥密码 

	@Column(name = "money_id")
	private String moneyId;//余额Id

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantOrderTime() {
		return merchantOrderTime;
	}

	public void setMerchantOrderTime(String merchantOrderTime) {
		this.merchantOrderTime = merchantOrderTime;
	}

	public String getBatchPayFileName() {
		return batchPayFileName;
	}

	public void setBatchPayFileName(String batchPayFileName) {
		this.batchPayFileName = batchPayFileName;
	}

	public String getBatchPayFilePath() {
		return batchPayFilePath;
	}

	public void setBatchPayFilePath(String batchPayFilePath) {
		this.batchPayFilePath = batchPayFilePath;
	}

	public String getBackFilePath() {
		return backFilePath;
	}

	public void setBackFilePath(String backFilePath) {
		this.backFilePath = backFilePath;
	}

	public String getBatchPayFileDigest() {
		return batchPayFileDigest;
	}

	public void setBatchPayFileDigest(String batchPayFileDigest) {
		this.batchPayFileDigest = batchPayFileDigest;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getQueryUrl() {
		return queryUrl;
	}

	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}

	public String getQueryTaskUrl() {
		return queryTaskUrl;
	}

	public void setQueryTaskUrl(String queryTaskUrl) {
		this.queryTaskUrl = queryTaskUrl;
	}

	public String getQueryDwonUrl() {
		return queryDwonUrl;
	}

	public void setQueryDwonUrl(String queryDwonUrl) {
		this.queryDwonUrl = queryDwonUrl;
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

	public String getMoneyId() {
		return moneyId;
	}

	public void setMoneyId(String moneyId) {
		this.moneyId = moneyId;
	}

	@Override
	public String toString() {
		return "YFCashConfig{" +
				"version='" + version + '\'' +
				", merchantId='" + merchantId + '\'' +
				", merchantOrderTime='" + merchantOrderTime + '\'' +
				", batchPayFileName='" + batchPayFileName + '\'' +
				", batchPayFilePath='" + batchPayFilePath + '\'' +
				", backFilePath='" + backFilePath + '\'' +
				", batchPayFileDigest='" + batchPayFileDigest + '\'' +
				", backUrl='" + backUrl + '\'' +
				", requestUrl='" + requestUrl + '\'' +
				", queryUrl='" + queryUrl + '\'' +
				", queryTaskUrl='" + queryTaskUrl + '\'' +
				", queryDwonUrl='" + queryDwonUrl + '\'' +
				", yufucerpPath='" + yufucerpPath + '\'' +
				", yufucerpAddress='" + yufucerpAddress + '\'' +
				", privateSign='" + privateSign + '\'' +
				", moneyId='" + moneyId + '\'' +
				", id=" + id +
				", createUser='" + createUser + '\'' +
				", createdTime=" + createdTime +
				", updateTime=" + updateTime +
				", createdIp='" + createdIp + '\'' +
				'}';
	}
}
