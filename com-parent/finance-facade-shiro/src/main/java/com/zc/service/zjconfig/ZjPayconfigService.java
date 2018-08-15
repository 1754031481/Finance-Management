package com.zc.service.zjconfig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.wconfig.WPayConfig;
import com.zc.entity.zjconfig.ZjPayConfig;

import java.util.Map;

/**
 * @package : com.zc.service.zjconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月17
 */
public interface ZjPayconfigService {

    Map<String,Object>  getZjConfig(PageBean pageBean);

    Map<String,Object>  findConfig(Long ZjPayId);

    Map<String,Object> updateZjPayConfig(ZjPayConfig zjPayConfig1);

    Map<String,Object> addZjPayConfig(ZjPayConfig zjPayConfig1);


}
