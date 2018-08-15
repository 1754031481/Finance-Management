package com.zc.service.ldconfig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.result.Result;
import com.zc.entity.ldconfig.LDCashConfig;

/**
 * @author 杨文杰
 * @Title: LDPayService
 * @Description: 联动优势代付配置
 * @date 2018/4/11
 */
public interface LDCashService {
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
    Result updateLdCash(LDCashConfig LDCashConfig);

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/10
     * @version 1.0.0
     * @param
     *
     */
    Result getLdCash(Long id);


}
