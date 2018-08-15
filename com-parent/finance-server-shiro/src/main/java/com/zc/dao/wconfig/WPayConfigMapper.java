package com.zc.dao.wconfig;

import com.zc.entity.chconfig.ChPayConfig;
import com.zc.entity.wconfig.WPayConfig;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.dao.wconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月16
 */
public interface WPayConfigMapper {

    List<Map<String,Object>> findWPayConfig();

    List<Map<String,Object>> findConfig(Long ChPayId);

    void  updateWPayConfig(WPayConfig wPayConfig1);

    void addWPayConfig(WPayConfig wPayConfig1);
}
