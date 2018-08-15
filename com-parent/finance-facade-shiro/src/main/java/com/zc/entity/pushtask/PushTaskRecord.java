package com.zc.entity.pushtask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "push_task_record")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class PushTaskRecord extends BaseIdEntity {

	private static final long serialVersionUID = 1L;

	public String systemName; // 项目名称

	public String systemFromName; // 缩写

	public String orderNum; // 订单号

	public String payType; // 0支付1代付

	public String payStatus; // 1处理中2成功3失败
	
	public String batchNo; //  流水号
	
	public String otherNo; // 其他 备用
	
	public String aynUrl; // 异步通知地址
	
	public String thirdPayType; // 接口类型
	
	public Long money; // 交易金额

	public String descibe; // 当前环节描述 
	
	public Integer sendCount;//(收到success不再发送) 0 成功     1 首次推送  2产生结果后10分钟  3产生结果后60分钟   4产生结果后12小时 5产生结果后24小时

	public Integer getSendCount() {
		return sendCount;
	}

	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
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

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getOtherNo() {
		return otherNo;
	}

	public void setOtherNo(String otherNo) {
		this.otherNo = otherNo;
	}

	public String getAynUrl() {
		return aynUrl;
	}

	public void setAynUrl(String aynUrl) {
		this.aynUrl = aynUrl;
	}

}
