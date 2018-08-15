package com.zc.service.configuration;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.configuration.YFCashConfig;
import com.zc.entity.configuration.YFPayConfig;

import java.util.HashMap;

/**
 * @author: 王楠
 * @version:
 * @Description: 裕福配置服务层
 **/
public interface YFConfigService {

    /**
     * @author 王楠
     * @version
     * @param page 分页信息
     * @Description: 获取裕福渠道支付配置列表
     * @return 裕福渠道支付配置列表
     * @exception
     **/
    HashMap<String,Object> getPayList(PageBean page);

    /**
     * @author 王楠
     * @version
     * @param yfPayConfig 添加信息
     * @Description: 添加裕福渠道支付配置
     * @return Result
     * @exception
     **/
    Result addPay(YFPayConfig yfPayConfig);

    /**
     * @author 王楠
     * @version
     * @param yfPayConfig 修改信息
     * @Description: 修改裕福渠道支付配置
     * @return Result
     * @exception
     **/
    Result updatePay(YFPayConfig yfPayConfig);

    /**
     * @author 王楠
     * @version
     * @param page 分页信息
     * @Description: 获取裕福渠道代付配置列表
     * @return 裕福渠道代付配置列表
     * @exception
     **/
    HashMap<String,Object> getCashList(PageBean page);

    /**
     * @author 王楠
     * @version
     * @param yfCashConfig 添加信息
     * @Description: 添加裕福渠道代付配置
     * @return Result
     * @exception
     **/
    Result addCash(YFCashConfig yfCashConfig);

    /**
     * @author 王楠
     * @version
     * @param yfCashConfig 修改信息
     * @Description: 添加裕福渠道代付配置
     * @return Result
     * @exception
     **/
    Result updateCash(YFCashConfig yfCashConfig);
}
