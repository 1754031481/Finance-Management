package com.zc.service.chcofig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.chconfig.CHCashConfig;

import java.util.Map;

/**
 * @description: 丰付代付配置
 * @author:  史云顺
 * @date:  2018-04-13
 * @version: 1.0.0
 */
public interface ChCashConfigService {


    Map<String,Object>  findCashConfig(Long chid);

    Map<String,Object> updateChCashConfig(CHCashConfig chcashConfig);

    Map<String,Object> addChCashConfig(CHCashConfig chcashConfig);


    Map<String,Object> getChCashConfig(PageBean pageBean);
}
