package com.zc.service.xsconfig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.result.Result;
import com.zc.entity.xsconfig.XSCashConfig;

/**
 * @author 杨文杰
 * @Title: XSCashService
 * @Description: 新生代付配置
 * @date 2018/4/12
 */
public interface XSCashService {
    /**
     * 分页获取配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/12
     * @version 1.0.0
     * @param
     *
     */
    Result getList(PageBean pageBean);
    /**
     * 添加修改配置信息
     * @author : 杨文杰
     * @Creation Date ： 2018/4/12
     * @version 1.0.0
     * @param
     *
     */
    Result updateXsCash(XSCashConfig XSCashConfig);

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/12
     * @version 1.0.0
     * @param
     *
     */
    Result getXsCash(Long id);

    /**
     * 展示下拉框
     * @author : 杨文杰
     * @Creation Date ： 2018/4/12
     * @version 1.0.0
     * @param
     *
     */
    Result getSelect();
}
