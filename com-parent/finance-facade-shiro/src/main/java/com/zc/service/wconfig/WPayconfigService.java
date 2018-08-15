package com.zc.service.wconfig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.chconfig.ChPayConfig;
import com.zc.entity.wconfig.WPayConfig;

import java.util.Map;

/**
 * @package : com.zc.service.wconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月16
 */
public interface WPayconfigService {

    Map<String,Object>  getWConfig(PageBean pageBean);

    Map<String,Object>  findConfig(Long WPayId);

    Map<String,Object> updateWPayConfig(WPayConfig wPayConfig1);

    Map<String,Object> addWPayConfig(WPayConfig wPayConfig1);


}
