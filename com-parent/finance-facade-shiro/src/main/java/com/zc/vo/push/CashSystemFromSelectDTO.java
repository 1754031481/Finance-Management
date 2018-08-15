package com.zc.vo.push;

import java.io.Serializable;
/**
 * @author: 王楠
 * @version:
 * @Description: 代付订单项目下拉框
 **/
public class CashSystemFromSelectDTO implements Serializable {

    private Long id;

    // 项目标识
    private String systemFromName;

    //项目名称
    private String systemName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemFromName() {
        return systemFromName;
    }

    public void setSystemFromName(String systemFromName) {
        this.systemFromName = systemFromName;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @Override
    public String toString() {
        return "CashSystemFromSelectDTO{" +
                "id=" + id +
                ", systemFromName='" + systemFromName + '\'' +
                ", systemName='" + systemName + '\'' +
                '}';
    }
}
