package com.zc.dao.zjconfig;

import com.zc.entity.wconfig.WPayConfig;
import com.zc.entity.zjconfig.ZjPayConfig;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.dao.zjconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月17
 */
public interface ZjPayConfigMapper {

    List<Map<String,Object>> findZjPayConfig();

    List<Map<String,Object>> findConfig(Long ZjPayId);

    void  updateZjPayConfig(ZjPayConfig zjPayConfig1);

    void addZjPayConfig(ZjPayConfig zjPayConfig1);
}
