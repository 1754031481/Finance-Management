package com.zc.dao.zjconfig;

import com.zc.entity.zjconfig.ZjCashConfig;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.dao.zjconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月18
 */
public interface ZjCashConfigMapper {

    List<Map<String,Object>> findZjCashConfig();

    List<Map<String,Object>> findConfig(Long ZjCashId);

    void  updateZjCashConfig(ZjCashConfig zjCashConfig1);

    void  addZjCashConfig(ZjCashConfig zjCashConfig1);
}
