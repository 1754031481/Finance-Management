package com.zc.entity.thirdpaytype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @description:  支付接口表
 * @author:  ZhaoJunBiao
 * @date:  2018-04-13 17:15
 * @version: 1.0.0
 */
@Entity
@Table(name = "third_pay_type")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class ThirdPayType extends BaseIdEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String thirdPayTypeName;//支持的接口名称描述 (非空)
	
	private Long systemFromId;///支持的项目    
	
	private Long payConfigId;//支持的配置组    (非空)

	private Long commonPayConfigId;//通用支付配置组    (非空)
	
	private Long interFaceId;//接口配置   (非空)

	public Long getInterFaceId() {
		return interFaceId;
	}

	public void setInterFaceId(Long interFaceId) {
		this.interFaceId = interFaceId;
	}

	public String getThirdPayTypeName() {
		return thirdPayTypeName;
	}

	public void setThirdPayTypeName(String thirdPayTypeName) {
		this.thirdPayTypeName = thirdPayTypeName;
	}

	public Long getSystemFromId() {
		return systemFromId;
	}

	public void setSystemFromId(Long systemFromId) {
		this.systemFromId = systemFromId;
	}

	public Long getPayConfigId() {
		return payConfigId;
	}

	public void setPayConfigId(Long payConfigId) {
		this.payConfigId = payConfigId;
	}

	public Long getCommonPayConfigId() {
		return commonPayConfigId;
	}

	public void setCommonPayConfigId(Long commonPayConfigId) {
		this.commonPayConfigId = commonPayConfigId;
	}
}
