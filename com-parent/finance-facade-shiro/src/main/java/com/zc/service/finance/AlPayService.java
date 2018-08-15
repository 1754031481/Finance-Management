package com.zc.service.finance;

import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.result.Result;
import com.zc.entity.finance.AlPayConfig;

/**
 * @author 杨文杰
 * @Title: AlPayService
 * @Description: 支付宝配置
 * @date 2018/4/8
 */
public interface AlPayService {
    /**
     * 分页获取支付宝配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    Result getList(PageBean pageBean);
    /**
     * 添加修改配置信息
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    Result updateAlPay(AlPayConfig alPayConfig);

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    Result getAlPay(Long id);

    /**
     * 展示下拉框
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    Result getSelect();
}
