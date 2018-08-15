package com.zc.service.wjconfig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.wjconfig.WjCashConfig;
import com.zc.entity.zjconfig.ZjCashConfig;

import java.util.Map;

/**
 * @package : com.zc.service.zjconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月20
 */
public interface WjCashconfigService {

    Map<String,Object>  getWjConfig(PageBean pageBean);

   Map<String,Object>  findConfig(Long WjCashId);

    Map<String,Object> addWjCashConfig(WjCashConfig wjCashConfig1);

    Map<String,Object> updateWjCashConfig(WjCashConfig wjCashConfig1);


}
