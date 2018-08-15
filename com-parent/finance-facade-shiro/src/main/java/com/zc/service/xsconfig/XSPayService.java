package com.zc.service.xsconfig;

import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.result.Result;
import com.zc.entity.xsconfig.XSPayConfig;

/**
 * @author 杨文杰
 * @Title: XSPayService
 * @Description: 新生支付配置
 * @date 2018/4/11
 */
public interface XSPayService {
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
    Result updateXsPay(XSPayConfig XSPayConfig);

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/10
     * @version 1.0.0
     * @param
     *
     */
    Result getXsPay(Long id);

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
