package com.zc.service.push;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.push.PushTask;
import com.zc.entity.push.PushTaskPay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 王楠
 * @version:
 * @Description: 关于推送订单服务层
 **/
public interface OrderService {

    /**
     * @param page 页面信息
     * @param pushTask 条件查询参数
     * @return 代付订单列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取关于推送代付订单列表
     */
    HashMap<String,Object> getCashList(PageBean page,PushTask pushTask);

    /**
     * @param pushTask 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加关于推送代付订单
     */
    Result addCash(PushTask pushTask);

    /**
     * @param pushTask 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改关于推送代付订单
     */
    Result updateCash(PushTask pushTask);

    /**
     * @return 代付订单下拉列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取关于推送代付订单下拉列表
     */
    Map<String,List> getCashSelect();

    /**
     * @param page 页面信息
     * @return 支付订单列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取关于推送支付订单列表
     */
    HashMap<String,Object> getPayList(PageBean page,PushTaskPay pushTaskPay);

    /**
     * @param pushTaskPay 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加关于推送支付订单
     */
    Result addPay(PushTaskPay pushTaskPay);

    /**
     * @param pushTaskPay 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改关于推送支付订单
     */
    Result updatePay(PushTaskPay pushTaskPay);

    /**
     * @param id
     * @return Result
     * @throws
     * @author ywj
     * @version
     * @Description: 删除推送
     */
    Result deletePush(Long id);

    /**
     * @param id
     * @return Result
     * @throws
     * @author ywj
     * @version
     * @Description: 删除代付推送
     */
    Result deletePushCash(Long id);
}
