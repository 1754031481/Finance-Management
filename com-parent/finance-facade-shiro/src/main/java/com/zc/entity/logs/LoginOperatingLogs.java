package com.zc.entity.logs;

import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "login_operating_logs")
public class LoginOperatingLogs extends BaseIdEntity {

    @Column(name = "operation_logs")
    private String operationLogs;

    public String getOperationLogs() {
        return operationLogs;
    }

    public void setOperationLogs(String operationLogs) {
        this.operationLogs = operationLogs;
    }

    @Override
    public String toString() {
        return "LoginOperatingLogs{" +
                "operationLogs='" + operationLogs + '\'' +
                ", id=" + id +
                ", createUser='" + createUser + '\'' +
                ", createdTime=" + createdTime +
                ", updateTime=" + updateTime +
                ", createdIp='" + createdIp + '\'' +
                '}';
    }
}
