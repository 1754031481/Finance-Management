package com.zc.dao.wjconfig;

import com.zc.entity.wjconfig.WjCashConfig;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.dao.wjconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月20
 */
public interface WjCashConfigMapper {

    List<Map<String,Object>> findWjCashConfig();

    List<Map<String,Object>> findConfig(Long WjCashId);

    void  updateWjCashConfig(WjCashConfig wjCashConfig1);

    void  addWjCashConfig(WjCashConfig wjCashConfig1);
}
