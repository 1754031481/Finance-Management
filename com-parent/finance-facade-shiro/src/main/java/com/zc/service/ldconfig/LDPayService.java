package com.zc.service.ldconfig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.result.Result;
import com.zc.entity.ldconfig.LDPayConfig;


/**
 * @author 杨文杰
 * @Title: LDPayService
 * @Description: 联动优势支付配置
 * @date 2018/4/11
 */
public interface LDPayService {
    /**
     * 分页获取配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/10
     * @version 1.0.0
     * @param
     *
     */
    Result getList(PageBean pageBean);
    /**
     * 添加修改配置信息
     * @author : 杨文杰
     * @Creation Date ： 2018/4/10
     * @version 1.0.0
     * @param
     *
     */
    Result updateLdPay(LDPayConfig LDPayConfig);

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/10
     * @version 1.0.0
     * @param
     *
     */
    Result getLdPay(Long id);


}
