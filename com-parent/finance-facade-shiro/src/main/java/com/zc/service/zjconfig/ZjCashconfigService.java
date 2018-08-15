package com.zc.service.zjconfig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.zjconfig.ZjCashConfig;

import java.util.Map;

/**
 * @package : com.zc.service.zjconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月18
 */
public interface ZjCashconfigService {

    Map<String,Object>  getZjConfig(PageBean pageBean);

   Map<String,Object>  findConfig(Long ZjCashId);

   Map<String,Object> addZjCashConfig(ZjCashConfig zjCashConfig1);

    Map<String,Object> updateZjCashConfig(ZjCashConfig zjCashConfig1);


}
