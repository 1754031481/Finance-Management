package com.zc.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @package : com.zc.vo
 * @progect : Finance-Management
 * @Description :
 * @Author by :王楠
 * @Creation Date ：2018年06月04日10:42
 */
public class SystemFromCashShuntingVO implements Serializable {

    //项目英文缩写
    private String fromSystem;

    //渠道名称
    private String thirdPayType;

    //分流金额预估
    private String totalMoney;

    //分流笔数预估
    private String totalNum;

    //最终修改时间
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFromSystem() {
        return fromSystem;
    }

    public void setFromSystem(String fromSystem) {
        this.fromSystem = fromSystem;
    }

    public String getThirdPayType() {
        return thirdPayType;
    }

    public void setThirdPayType(String thirdPayType) {
        this.thirdPayType = thirdPayType;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return "SystemFromCashShuntingVO{" +
                "fromSystem='" + fromSystem + '\'' +
                ", thirdPayType='" + thirdPayType + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                ", totalNum='" + totalNum + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
