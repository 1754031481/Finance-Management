package com.zc.dao.chconfig;

import com.zc.entity.chconfig.CHCashConfig;


import java.util.List;
import java.util.Map;

/**
 * @description: 丰付代付配置
 * @author:  史云顺
 * @date:  2018-04-13
 * @version: 1.0.0
 */
public interface ChCashConfigMapper {

    List<Map<String,Object>> findChCashConfig();

    List<Map<String,Object>> findCashConfig(Long chid);

    void addChCashConfig(CHCashConfig ChCashConfig);

    void updateChCashConfig(CHCashConfig ChCashConfig);
}
