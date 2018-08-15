package com.zc.dao.ffconfig;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.ffconfig.FFPayConfig;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.dao.ffconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月08日15:49
 */
public interface FfPayConfigMapper {

    List<Map<String,Object>> findFfPayConfig();

    List<Map<String,Object>> findConfig(Long ffPayId);

    void  updateFfPayConfig(FFPayConfig ffPayConfig);

    void addFfPayConfig(FFPayConfig ffPayConfig);
}
