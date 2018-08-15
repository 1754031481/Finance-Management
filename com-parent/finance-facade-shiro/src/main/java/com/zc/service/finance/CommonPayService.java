package com.zc.service.finance;


import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.result.Result;
import com.zc.entity.finance.CommonPayConfig;

import java.util.Map;

/**
 * @author 杨文杰
 * @Title: CommonPayService
 * @Description: 通用配置
 * @date 2018/4/8
 */
public interface CommonPayService {

    /**
     * 分页获取通用配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    Result getList(PageBean pageBean);

    /**
     * 添加修改配置信息
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    Result updateCommonPay(CommonPayConfig commonPayConfig);

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    Result getCommonPay(Long id);


    /**
     * @description:  根据id查询
     * @author:  ZhaoJunBiao
     * @date:  2018-04-13 17:47
     * @version: 1.0.0
     * @param commonPayConfigId
     * @return
     */
    CommonPayConfig getCommonPayOne(Long commonPayConfigId);


    /**
     * @description: 获取配置
     * @author:  ZhaoJunBiao
     * @date:  2018-04-25 13:40
     * @version: 1.0.0
     * @return
     */
    Map<String,Object> findCommonPayConfigList();
}
