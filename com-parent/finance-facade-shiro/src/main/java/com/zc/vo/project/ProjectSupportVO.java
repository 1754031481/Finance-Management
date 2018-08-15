package com.zc.vo.project;

import com.zc.common.core.orm.hibernate.BaseIdEntity;

import java.io.Serializable;

/**
 * @author: 王楠
 * @version:
 * @Description: 项目支持列表参数VO
 **/
public class ProjectSupportVO extends BaseIdEntity {


    //项目名称
    private String projectName;

    //项目标识
    private String projectSign;

    //支付接口
    private String payInterface;

    //代付接口
    private String cashInterface;

    //异步通知地址
    private String systemFromUrl;

    //是否支持项目
    private String supportStatue;

    //是否代付分流
    private String shuntingCash;

    //是否支付分流
    private String shuntingPay;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectSign() {
        return projectSign;
    }

    public void setProjectSign(String projectSign) {
        this.projectSign = projectSign;
    }

    public String getPayInterface() {
        return payInterface;
    }

    public void setPayInterface(String payInterface) {
        this.payInterface = payInterface;
    }

    public String getCashInterface() {
        return cashInterface;
    }

    public void setCashInterface(String cashInterface) {
        this.cashInterface = cashInterface;
    }

    public String getSystemFromUrl() {
        return systemFromUrl;
    }

    public void setSystemFromUrl(String systemFromUrl) {
        this.systemFromUrl = systemFromUrl;
    }

    public String getSupportStatue() {
        return supportStatue;
    }

    public void setSupportStatue(String supportStatue) {
        this.supportStatue = supportStatue;
    }

    public String getShuntingCash() {
        return shuntingCash;
    }

    public void setShuntingCash(String shuntingCash) {
        this.shuntingCash = shuntingCash;
    }

    public String getShuntingPay() {
        return shuntingPay;
    }

    public void setShuntingPay(String shuntingPay) {
        this.shuntingPay = shuntingPay;
    }

    @Override
    public String toString() {
        return "ProjectSupportVO{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", projectSign='" + projectSign + '\'' +
                ", payInterface='" + payInterface + '\'' +
                ", cashInterface='" + cashInterface + '\'' +
                ", systemFromUrl='" + systemFromUrl + '\'' +
                ", supportStatue='" + supportStatue + '\'' +
                ", shuntingCash='" + shuntingCash + '\'' +
                ", shuntingPay='" + shuntingPay + '\'' +
                '}';
    }
}
