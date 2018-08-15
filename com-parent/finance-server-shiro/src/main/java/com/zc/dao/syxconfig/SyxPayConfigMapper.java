package com.zc.dao.syxconfig;

import com.zc.entity.syxconfig.SYXPayConfig;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.dao.syxconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月10日9:55
 */
public interface SyxPayConfigMapper extends BaseMapper<SYXPayConfig> {

    List<Map<String,Object>> findSyxPayList();

    void updateSyxPayConfig(SYXPayConfig syxPayConfig);

    List<Map<String,Object>>   findSystemForm();
}
