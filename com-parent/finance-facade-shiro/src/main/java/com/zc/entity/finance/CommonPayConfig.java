package com.zc.entity.finance;

import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 杨文杰
 * @Title: CommonPayConfig
 * @Description: 通用配置实体
 * @date 2018/4/8
 */
@Table(name = "common_pay_config")
public class CommonPayConfig extends BaseIdEntity implements Serializable {

    /**
     * 单笔限额
     */
    @Column(name = "single_a_limit")
    private String singleLimit;

    /**
     * 单日限额
     */
    @Column(name = "single_day_limit")
    private String singleDayLimit;

    /**
     * 开启时间
     */
    @Column(name = "start_time")
    private String startTime;

    /**
     * 关闭时间
     */
    @Column(name = "stop_time")
    private String stopTime;

    /**
     * 是否支持周末
     */
    @Column(name = "weekend")
    private String weekend;

    /**
     * 配置名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 单笔最大限额
     */
    @Column(name = "single_max_limit")
    private String singleMaxLimit;

    /**
     * 单笔最小限额
     */
    @Column(name = "single_min_limit")
    private String singleMinLimit;

    /**
     * 代付回调api地址
     */
    @Column(name = "ayn_url")
    private String aynUrl;

    /**
     * 是否开启时间限制
     */
    @Column(name = "enable_time_validate")
    private String enableTimeValidate;

    /**
     * 支付回调api地址
     */
    @Column(name = "ayn_url2")
    private String aynUrl2;

    /**
     * 是否延迟
     */
    @Column(name = "is_delayed")
    private String isDelayed;

    public String getSingleLimit() {
        return singleLimit;
    }

    public void setSingleLimit(String singleLimit) {
        this.singleLimit = singleLimit;
    }

    public String getSingleDayLimit() {
        return singleDayLimit;
    }

    public void setSingleDayLimit(String singleDayLimit) {
        this.singleDayLimit = singleDayLimit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getWeekend() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend = weekend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSingleMaxLimit() {
        return singleMaxLimit;
    }

    public void setSingleMaxLimit(String singleMaxLimit) {
        this.singleMaxLimit = singleMaxLimit;
    }

    public String getSingleMinLimit() {
        return singleMinLimit;
    }

    public void setSingleMinLimit(String singleMinLimit) {
        this.singleMinLimit = singleMinLimit;
    }

    public String getAynUrl() {
        return aynUrl;
    }

    public void setAynUrl(String aynUrl) {
        this.aynUrl = aynUrl;
    }

    public String getEnableTimeValidate() {
        return enableTimeValidate;
    }

    public void setEnableTimeValidate(String enableTimeValidate) {
        this.enableTimeValidate = enableTimeValidate;
    }

    public String getAynUrl2() {
        return aynUrl2;
    }

    public void setAynUrl2(String aynUrl2) {
        this.aynUrl2 = aynUrl2;
    }

    public String getIsDelayed() {
        return isDelayed;
    }

    public void setIsDelayed(String isDelayed) {
        this.isDelayed = isDelayed;
    }
}
