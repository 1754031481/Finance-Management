package com.zc.entity.cashreceivestation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: crs提现记录表
 * @author:  ZhaoJunBiao
 * @date:  2018-05-04 14:18
 * @version: 1.0.0
 */
@Entity
@Table(name = "cash_receive_station")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@XmlAccessorType(XmlAccessType.FIELD)
//XML文件中的根标识
@XmlRootElement(name = "CashReceiveStation")
//控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = {
		"id","createUser","createdTime","updateTime",
		"createdIp","versionID","cssId","cssType",
		"approveDate","status",
		"bankName",
		"cardNo",
		"comments",
		"personName",
		"personNo",
		"tel",
		"ip",
		"money",
		"bankSub",
		"name",
		"bankPrv",
		"bankCity",
		"oraStatus",
		"describer",
		"oraName",
		"createTime",
		"merSeqId",
		"merDate",
		"chkValue",
		"feeIncome",
		"signDate",
		"bankAbbr",
		"auditStatus",
		"auditTime",
		"thirdPayType",
		"fromSystem",
		"systemRole",
		"currentDescribe",
		"currentBalance",
		"afterBalance",
		"notifyUrl",
		"title",
		"crsTaskAddress"
})
public class CashReceiveStation implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private Long id;

	private String createUser = "";
	@DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
	private Date createdTime;

	private Date updateTime;

	private String createdIp = "";

	private String versionID = "shenhuiyuan20170122";

	private Integer money;//金额，以分为单位；

	private Integer cssId;//商户id 或者 代理商id 会员id

	private Integer cssType;//类型1顾客 2配送点 3供应商 (普惠项目使用systemRole判断)

	private Date approveDate;//批准时间      ***** (未使用) *****

	private Integer status;//1已申请 2打款成功 3打款失败 4业务处理中(发起代付后维护字段为4)

	private String bankName;//银行名称；

	private String cardNo;//银行卡号

	private String comments;//备注信息      ***** (未使用) *****

	private String personName;//收款人名称

	private String personNo;//收款人卡号

	private String tel;//电话

	private String ip;//ip地址

	private String name;//提现发起角色姓名       ***** (未使用) *****

	private String bankPrv;//省      ***** (未使用) *****

	private String bankCity;//市      ***** (未使用) *****

	private String bankSub;//开户支行

	private String oraStatus;//银行返回状态  0：正在处理中  1：提现成功  2：提现失败      ***** (未使用) *****

	private String describer;//记录提现失败的原因

	private String oraName;//      ***** (未使用) *****

	private Date createTime;//创建时间

	private String merSeqId;//流水号

	private String  merDate;//商户日期      ***** (未使用) *****

	private String chkValue;//银联代付签名值

	private Integer feeIncome;//手续费；

	private String signDate;//京东代付  签名数据

	private String bankAbbr;//京东代付必须字段 银行缩写

	private Integer auditStatus;//0待审核  1审核通过  2审核不通过  null自动提现

	private Date auditTime;//审核时间

	private String thirdPayType;//第三方接口类型

	private String fromSystem;//提现发起来源项目--用于记录来源

	private String systemRole;//提现发起角色--用于特殊项目区分角色 普惠项目:1=商户 2=区代理 3=县代理 4=市代理 5=会员

	private String currentDescribe;//当前环节描述--用于查看数据

	private Long currentBalance;//提现时余额

	private Long afterBalance ;//提现操作后余额

	private String notifyUrl;//订单异步通知地址

	private String title;//订单异步通知地址

	private String crsTaskAddress;// 访问渠道作业地址(分流用)



	public String getCrsTaskAddress() {
		return crsTaskAddress;
	}

	public void setCrsTaskAddress(String crsTaskAddress) {
		this.crsTaskAddress = crsTaskAddress;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Long getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Long currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Long getAfterBalance() {
		return afterBalance;
	}

	public void setAfterBalance(Long afterBalance) {
		this.afterBalance = afterBalance;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getBankAbbr() {
		return bankAbbr;
	}

	public void setBankAbbr(String bankAbbr) {
		this.bankAbbr = bankAbbr;
	}

	public Integer getFeeIncome() {
		return feeIncome;
	}

	public void setFeeIncome(Integer feeIncome) {
		this.feeIncome = feeIncome;
	}

	public String getDescriber() {
		return describer;
	}

	public void setDescriber(String describer) {
		this.describer = describer;
	}

	public String getMerSeqId() {
		return merSeqId;
	}

	public void setMerSeqId(String merSeqId) {
		this.merSeqId = merSeqId;
	}

	public String getMerDate() {
		return merDate;
	}

	public void setMerDate(String merDate) {
		this.merDate = merDate;
	}

	public String getChkValue() {
		return chkValue;
	}

	public void setChkValue(String chkValue) {
		this.chkValue = chkValue;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getCssId() {
		return cssId;
	}

	public void setCssId(Integer cssId) {
		this.cssId = cssId;
	}

	public Integer getCssType() {
		return cssType;
	}

	public void setCssType(Integer cssType) {
		this.cssType = cssType;
	}
	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonNo() {
		return personNo;
	}

	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankPrv() {
		return bankPrv;
	}

	public void setBankPrv(String bankPrv) {
		this.bankPrv = bankPrv;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public String getBankSub() {
		return bankSub;
	}

	public void setBankSub(String bankSub) {
		this.bankSub = bankSub;
	}

	public String getOraStatus() {
		return oraStatus;
	}

	public void setOraStatus(String oraStatus) {
		this.oraStatus = oraStatus;
	}

	public String getOraName() {
		return oraName;
	}

	public void setOraName(String oraName) {
		this.oraName = oraName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "CashReceiveStation [id=" + id + ", createUser=" + createUser + ", createdTime=" + createdTime
				+ ", updateTime=" + updateTime + ", createdIp=" + createdIp + ", versionID=" + versionID + ", money="
				+ money + ", cssId=" + cssId + ", cssType=" + cssType + ", approveDate=" + approveDate + ", status="
				+ status + ", bankName=" + bankName + ", cardNo=" + cardNo + ", comments=" + comments + ", personName="
				+ personName + ", personNo=" + personNo + ", tel=" + tel + ", ip=" + ip + ", name=" + name
				+ ", bankPrv=" + bankPrv + ", bankCity=" + bankCity + ", bankSub=" + bankSub + ", oraStatus="
				+ oraStatus + ", describer=" + describer + ", oraName=" + oraName + ", createTime=" + createTime
				+ ", merSeqId=" + merSeqId + ", merDate=" + merDate + ", chkValue=" + chkValue + ", feeIncome="
				+ feeIncome + ", signDate=" + signDate + ", bankAbbr=" + bankAbbr + ", auditStatus=" + auditStatus
				+ ", auditTime=" + auditTime + ", thirdPayType=" + thirdPayType + ", fromSystem=" + fromSystem
				+ ", systemRole=" + systemRole + ", currentDescribe=" + currentDescribe + ", currentBalance="
				+ currentBalance + ", afterBalance=" + afterBalance + ", notifyUrl=" + notifyUrl + ", title=" + title
				+ ", crsTaskAddress=" + crsTaskAddress + "]";
	}


}

