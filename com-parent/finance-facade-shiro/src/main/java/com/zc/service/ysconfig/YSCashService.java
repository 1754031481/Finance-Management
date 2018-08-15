package com.zc.service.ysconfig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.result.Result;
import com.zc.entity.ysconfig.YSCashConfig;

/**
 * @author 杨文杰
 * @Title: YSPayService
 * @Description: 银盛代付配置
 * @date 2018/4/11
 */
public interface YSCashService {
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
    Result updateYsCash(YSCashConfig YSCashConfig);

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/10
     * @version 1.0.0
     * @param
     *
     */
    Result getYsCash(Long id);

    /**
     * 展示下拉框
     * @author : 杨文杰
     * @Creation Date ： 2018/4/10
     * @version 1.0.0
     * @param
     *
     */
    Result getSelect();
}
