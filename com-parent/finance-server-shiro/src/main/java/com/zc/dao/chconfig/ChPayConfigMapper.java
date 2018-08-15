package com.zc.dao.chconfig;

import com.zc.entity.chconfig.ChPayConfig;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.dao.chconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月13
 */
public interface ChPayConfigMapper {

    List<Map<String,Object>> findChPayConfig();

    List<Map<String,Object>> findConfig(Long ChPayId);

    void  updateChPayConfig(ChPayConfig chPayConfig);

    void addChPayConfig(ChPayConfig chPayConfig);
}
