package com.zc.dao.wconfig;

import com.zc.entity.wconfig.WCashConfig;
import com.zc.entity.wconfig.WPayConfig;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.dao.wconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月17
 */
public interface WCashConfigMapper {

    List<Map<String,Object>> findWCashConfig();

    List<Map<String,Object>> findConfig(Long WCashId);

   void  updateWCashConfig(WCashConfig wCashConfig1);

    void addWCashConfig(WCashConfig wCashConfig1);
}
