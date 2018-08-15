package com.zc.entity.paycontext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**pay工程 上下文记录表 
 * ClassName:CashReceiveStation <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年11月24日 下午12:06:23 <br/>
 * @author   shen.joe
 * @version  支付工程-提现记录表
 * @since    JDK 1.8
 * @see 	 
 */
@Entity
@Table(name = "pay_context")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class PayContext implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;//主键ID
	
	private Date createdTime;//创建时间
	
	private Date updateTime;//更新时间
	
	private String createdIp;//创建ip
	
	private String versionID = "shenhuiyuan20170122";//版本号
	
	private String orderNum;//订单号 与项目做主要关联 非空
	
	private String sendContext;//请求报文====重要参数
	
	private String currentReturnMsg;//请求报文====重要参数
	
	private String synReturnContext;//返回报文====重要参数
	
	private String thirdPayType;//第三方接口类型 非空
	
	private String fromSystem;//提现发起来源项目--用于记录来源 非空
	
	private String systemRole;//提现发起角色--用于特殊项目区分角色
	
	private String currentDescribe;//当前环节描述--用于查看数据
	
	private String aynUrl;//异步回调地址
	
	private String batchNo;//交易流水号
	
	private Long money;//交易金额
	
	private String payStatus;//订单状态  1处理中 2成功 3失败 
	
	private String isPushSuccess;//推送结果到源项目(收到success不再发送) 0 成功     1 首次推送  2产生结果后10分钟  3产生结果后60分钟   4产生结果后12小时 5产生结果后24小时
	
	private String type;//用于区分充值代付，0充值  1代付
	
	private String isDelay;//是否延迟提现
	
	public String getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public String getIsPushSuccess() {
		return isPushSuccess;
	}

	public void setIsPushSuccess(String isPushSuccess) {
		this.isPushSuccess = isPushSuccess;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreatedIp() {
		return createdIp;
	}

	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
	}

	public String getVersionID() {
		return versionID;
	}

	public void setVersionID(String versionID) {
		this.versionID = versionID;
	}

	public String getThirdPayType() {
		return thirdPayType;
	}

	public void setThirdPayType(String thirdPayType) {
		this.thirdPayType = thirdPayType;
	}

	public String getFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}

	public String getSystemRole() {
		return systemRole;
	}

	public void setSystemRole(String systemRole) {
		this.systemRole = systemRole;
	}

	public String getCurrentDescribe() {
		return currentDescribe;
	}

	public void setCurrentDescribe(String currentDescribe) {
		this.currentDescribe = currentDescribe;
	}

	public String getSendContext() {
		return sendContext;
	}

	public void setSendContext(String sendContext) {
		this.sendContext = sendContext;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getCurrentReturnMsg() {
		return currentReturnMsg;
	}

	public void setCurrentReturnMsg(String currentReturnMsg) {
		this.currentReturnMsg = currentReturnMsg;
	}

	public String getSynReturnContext() {
		return synReturnContext;
	}

	public void setSynReturnContext(String synReturnContext) {
		this.synReturnContext = synReturnContext;
	}

	public String getAynUrl() {
		return aynUrl;
	}

	public void setAynUrl(String aynUrl) {
		this.aynUrl = aynUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}

