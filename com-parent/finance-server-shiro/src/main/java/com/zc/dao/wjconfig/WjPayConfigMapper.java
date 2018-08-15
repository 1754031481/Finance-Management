package com.zc.dao.wjconfig;

import com.zc.entity.wjconfig.WjPayConfig;
import com.zc.entity.zjconfig.ZjPayConfig;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.dao.wjconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月18
 */
public interface WjPayConfigMapper {

    List<Map<String,Object>> findWjPayConfig();

    List<Map<String,Object>> findConfig(Long WjPayId);

   void  updateWjPayConfig(WjPayConfig wjPayConfig1);

    void addWjPayConfig(WjPayConfig wjPayConfig1);
}
