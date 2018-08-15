package com.zc.dao.ffconfig;

import com.zc.common.core.result.Result;
import com.zc.entity.ffconfig.FFCashConfig;
import com.zc.entity.ffconfig.FFPayConfig;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author:  ZhaoJunBiao
 * @date:  2018-04-09 15:31
 * @version: 1.0.0
 */
public interface FfCashConfigMapper {

    List<Map<String,Object>> findFfCashConfig();

    List<Map<String,Object>> findCashConfig(Long ffCashId);

    void addFfCashConfig(FFCashConfig ffCashConfig);

    void updateFfCashConfig(FFCashConfig ffCashConfig);
}
