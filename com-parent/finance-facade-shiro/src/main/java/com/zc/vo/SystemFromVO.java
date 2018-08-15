package com.zc.vo;

import java.io.Serializable;

/**
 * @author 杨文杰
 * @Title: SystemFromVO
 * @Description: 支持项目
 * @date 2018/4/9
 */
public class SystemFromVO implements Serializable{

    private Long id;

    private String name;

    private String systemFromName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemFromName() {
        return systemFromName;
    }

    public void setSystemFromName(String systemFromName) {
        this.systemFromName = systemFromName;
    }
}
