package com.zc.service.ffconfig;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.ffconfig.FFPayConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.ffconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月08日15:24
 */
public interface FfconfigService {

    Map<String,Object>  getFfConfig(PageBean pageBean);

    Map<String,Object>  findConfig(Long ffPayId);

    Map<String,Object> updateFfPayConfig(FFPayConfig ffPayConfig);

    Map<String,Object> addFfPayConfig(FFPayConfig ffPayConfig);


}
