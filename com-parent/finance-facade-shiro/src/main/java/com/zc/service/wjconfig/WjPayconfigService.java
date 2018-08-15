package com.zc.service.wjconfig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.wjconfig.WjPayConfig;

import java.util.Map;

/**
 * @package : com.zc.service.wjconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月18
 */
public interface WjPayconfigService {

    Map<String,Object>  getWjConfig(PageBean pageBean);

    Map<String,Object>  findConfig(Long WjPayId);

    Map<String,Object> addWjPayConfig(WjPayConfig wjPayConfig1);

    Map<String,Object> updateWjPayConfig(WjPayConfig wjPayConfig1);


}
