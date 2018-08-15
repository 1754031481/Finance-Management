package com.zc.entity.logs;

import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "test_order_logs")
public class TestOrderLogs extends BaseIdEntity {

    /*订单创建时间修改日志*/
    @Column(name = "create_time_log")
    private String createTimeLog;

    /*银行卡号*/
    @Column(name = "card_no")
    private String cardNo;

    /*收款人名称*/
    @Column(name = "person_name")
    private String personName;

    /*开户行*/
    @Column(name = "bank_sub")
    private String bankSub;

    /*身份证*/
    @Column(name = "comments")
    private String comments;

    /*渠道*/
    @Column(name = "third_pay_type")
    private String thirdPayType;

    /*订单号*/
    @Column(name = "mer_seq_id")
    private String merSeqId;

    public String getMerSeqId() {
        return merSeqId;
    }

    public void setMerSeqId(String merSeqId) {
        this.merSeqId = merSeqId;
    }

    public String getCreateTimeLog() {
        return createTimeLog;
    }

    public void setCreateTimeLog(String createTimeLog) {
        this.createTimeLog = createTimeLog;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getBankSub() {
        return bankSub;
    }

    public void setBankSub(String bankSub) {
        this.bankSub = bankSub;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getThirdPayType() {
        return thirdPayType;
    }

    public void setThirdPayType(String thirdPayType) {
        this.thirdPayType = thirdPayType;
    }

    @Override
    public String toString() {
        return "TestOrderLogs{" +
                "createTimeLog='" + createTimeLog + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", personName='" + personName + '\'' +
                ", bankSub='" + bankSub + '\'' +
                ", comments='" + comments + '\'' +
                ", thirdPayType='" + thirdPayType + '\'' +
                ", merSeqId='" + merSeqId + '\'' +
                ", id=" + id +
                ", createUser='" + createUser + '\'' +
                ", createdTime=" + createdTime +
                ", updateTime=" + updateTime +
                ", createdIp='" + createdIp + '\'' +
                '}';
    }
}
