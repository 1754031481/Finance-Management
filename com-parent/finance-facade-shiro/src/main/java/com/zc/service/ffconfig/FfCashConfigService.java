package com.zc.service.ffconfig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.ffconfig.FFCashConfig;
import com.zc.entity.ffconfig.FFPayConfig;

import java.util.Map;

/**
 * @package : com.zc.service.ffconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月08日15:24
 */
public interface FfCashConfigService {


    Map<String,Object>  findCashConfig(Long ffCashId);

    Map<String,Object> updateFfCashConfig(FFCashConfig ffCashConfig);

    Map<String,Object> addFfCashConfig(FFCashConfig ffCashConfig);


    Map<String,Object> getFfCashConfig(PageBean pageBean);
}
