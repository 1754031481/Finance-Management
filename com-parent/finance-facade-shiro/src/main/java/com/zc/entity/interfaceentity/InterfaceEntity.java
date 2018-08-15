package com.zc.entity.interfaceentity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "interface_entity")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class InterfaceEntity extends BaseIdEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Integer type;//接口类型   0-支付   1-代付
	
	private String supportStatue;// 0 支持 1不支持
	
	private String address;// 渠道访问地址
	
	private String addressReturn;// 渠道处理异步请求地址
	
	private String channelPublicKey;// 渠道公钥
	
	private String isShunting;// 是否加入分流
	
	private String isShuntingCash;// 代付是否加入分流
	
	private String weight;//权重
	
	private String weightCash;//权重
	
	private Long weekLimitMoney;//周限额
	
	private Long DayLimitMoney;//代付日限额
	
	private String crsTaskAddress;// 访问渠道作业地址

	private String returnAddress;//Channel回调地址（回调服务器业务专用）

	
	
	public String getCrsTaskAddress() {
		return crsTaskAddress;
	}

	public void setCrsTaskAddress(String crsTaskAddress) {
		this.crsTaskAddress = crsTaskAddress;
	}

	public Long getDayLimitMoney() {
		return DayLimitMoney;
	}

	public void setDayLimitMoney(Long dayLimitMoney) {
		DayLimitMoney = dayLimitMoney;
	}

	public Long getWeekLimitMoney() {
		return weekLimitMoney;
	}

	public void setWeekLimitMoney(Long weekLimitMoney) {
		this.weekLimitMoney = weekLimitMoney;
	}

	public String getChannelPublicKey() {
		return channelPublicKey;
	}

	public void setChannelPublicKey(String channelPublicKey) {
		this.channelPublicKey = channelPublicKey;
	}

	public String getIsShunting() {
		return isShunting;
	}

	public void setIsShunting(String isShunting) {
		this.isShunting = isShunting;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAddressReturn() {
		return addressReturn;
	}

	public void setAddressReturn(String addressReturn) {
		this.addressReturn = addressReturn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSupportStatue() {
		return supportStatue;
	}

	public void setSupportStatue(String supportStatue) {
		this.supportStatue = supportStatue;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsShuntingCash() {
		return isShuntingCash;
	}

	public void setIsShuntingCash(String isShuntingCash) {
		this.isShuntingCash = isShuntingCash;
	}

	public String getWeightCash() {
		return weightCash;
	}

	public void setWeightCash(String weightCash) {
		this.weightCash = weightCash;
	}

	public String getReturnAddress() {
		return returnAddress;
	}

	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}
}
