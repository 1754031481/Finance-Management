package com.zc.service.chcofig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.chconfig.ChPayConfig;

import java.util.Map;

/**
 * @package : com.zc.service.chconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月13
 */
public interface ChconfigService {

    Map<String,Object>  getChConfig(PageBean pageBean);

    Map<String,Object>  findConfig(Long ChPayId);

   Map<String,Object> updateChPayConfig(ChPayConfig ChpayConfig);

    Map<String,Object> addChPayConfig(ChPayConfig ChpayConfig);


}
