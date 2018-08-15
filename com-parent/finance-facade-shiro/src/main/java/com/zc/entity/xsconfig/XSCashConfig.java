package com.zc.entity.xsconfig;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;

@Entity
@Table(name="xs_cash_config")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class XSCashConfig extends BaseIdEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;//支持的项目名称
	private String version; //版本号    2.0
	private String tranCode; //交易代码  ADP01
	private String merId;//商户ID 
	private String signType;//签名类型   1 ：RSA
	private String charset;//编码 1:UTF-8
	private String notifyUrl;//回调地址
	private String rootUrl;//请求地址
	private String privateUrl;//私钥地址
	private String publicKey;//公钥
	private String storepass;
	private String alias;
	private String pwd;
	private String payType; //付款类型  1银行 2账户  默认1
	private String payeeType;//收款类型 个人1
	private String queryUrl;//查询地址
	private String tranCodeQuery;
	
	public String getTranCodeQuery() {
		return tranCodeQuery;
	}
	public void setTranCodeQuery(String tranCodeQuery) {
		this.tranCodeQuery = tranCodeQuery;
	}
	public String getQueryUrl() {
		return queryUrl;
	}
	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
	public String getPayeeType() {
		return payeeType;
	}
	public void setPayeeType(String payeeType) {
		this.payeeType = payeeType;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getStorepass() {
		return storepass;
	}
	public void setStorepass(String storepass) {
		this.storepass = storepass;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPrivateUrl() {
		return privateUrl;
	}
	public void setPrivateUrl(String privateUrl) {
		this.privateUrl = privateUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTranCode() {
		return tranCode;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getRootUrl() {
		return rootUrl;
	}
	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}
	
}
