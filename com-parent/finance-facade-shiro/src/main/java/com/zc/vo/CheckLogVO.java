package com.zc.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description: TODO
 * @author 史云顺
 * @e-mail
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年11月8日 上午10:48:49
 * 
 */
@Entity
public class CheckLogVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String systemFromName; //商户号
	 
	private String thirdPayType; //渠道名称
	 
	private String orderNum;   //订单号
	 
	private String payStatus;//状态 1处理中 2成功 3失败
	 
	private Integer type; //0充值 1代付
	 
	private Long orderMoney;//订单金额 分单位

	private String createdTime;//创建时间

	private String batchNo; //流水号

	private String createUser;

	private String createdIp;

	private Long configId;//配置id

	public String getCreatedIp() {
		return createdIp;
	}

	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
	}

	public Long getConfigId() {
		return configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getSystemFromName() {
		return systemFromName;
	}

	public void setSystemFromName(String systemFromName) {
		this.systemFromName = systemFromName;
	}

	public String getThirdPayType() {
		return thirdPayType;
	}

	public void setThirdPayType(String thirdPayType) {
		this.thirdPayType = thirdPayType;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Long orderMoney) {
		this.orderMoney = orderMoney;
	}



}
