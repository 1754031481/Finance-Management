package com.zc.service.wconfig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.wconfig.WCashConfig;
import com.zc.entity.wconfig.WPayConfig;

import java.util.Map;

/**
 * @package : com.zc.service.wconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月17
 */
public interface WCashconfigService {

    Map<String,Object>  getWConfig(PageBean pageBean);

   Map<String,Object>  findConfig(Long WCashId);

    Map<String,Object> updateWCashConfig(WCashConfig wCashConfig1);

    Map<String,Object> addWCashConfig(WCashConfig wCashConfig1);


}
