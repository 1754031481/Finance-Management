package com.zc.entity.pushqueryrecord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description: TODO
 * @author Shen.joe
 * @e-mail sudiluo_java@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月14日 下午4:55:19
 * 
 */
@Entity
@Table(name = "push_query_record")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class PushQueryRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	private  long id;

	public String systemName; // 项目名称

	public String systemFromName; // 缩写

	public String orderNum; // 订单号

	public String payType; // 0支付1代付

	public String payStatus; // 查询订单前状态 1处理中2成功3失败
	
	public String thirdStatus; // 三方当前状态 1处理中2成功3失败
	
	public String thirdPayType; // 接口类型
	
	public Long money; // 交易金额

	public String descibe; // 状态对比描述，字符串拼接。(三方状态为；请求状态为：存在差异。)

	private Date createdTime;//创建时间


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getThirdStatus() {
		return thirdStatus;
	}

	public void setThirdStatus(String thirdStatus) {
		this.thirdStatus = thirdStatus;
	}

	public String getThirdPayType() {
		return thirdPayType;
	}

	public void setThirdPayType(String thirdPayType) {
		this.thirdPayType = thirdPayType;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getDescibe() {
		return descibe;
	}

	public void setDescibe(String descibe) {
		this.descibe = descibe;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemFromName() {
		return systemFromName;
	}

	public void setSystemFromName(String systemFromName) {
		this.systemFromName = systemFromName;
	}

}
