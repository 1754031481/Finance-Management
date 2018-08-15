package com.zc.service.abouttesting;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.logs.TestOrderLogs;

import java.util.HashMap;

/**
 * @package : com.zc.service.abouttesting
 * @progect : Finance-Management
 * @Description :
 * @Author by :王楠
 * @Creation Date ：2018年06月04日10:42
 */
public interface TestOrderService {

    /**
     * @author 王楠
     * @version
     * @Description: 获取浦发扫码渠道列表
     * @return 浦发扫码渠道列表
     * @exception
     **/
    HashMap<String,Object> getTestOrderList(PageBean page);

    /**
     * @author 王楠
     * @version
     * @Description: 获取测试银行卡和第三方渠道列表
     * @return  bankNums 测试银行卡列表
     *          channels 第三方渠道列表
     * @exception
     **/
    HashMap<String,Object> getBankNumAndChannelList();

    /**
     * @author 王楠
     * @version
     * @Description: 修改测试订单
     * @return Result
     * @exception
     **/
    Result updateTestOrder(CashReceiveStation cashReceiveStation,String userName);

    /**
     * @author 王楠
     * @param testOrderLogs 条件查询过滤信息
     *
     * @param page
     * @version
     * @Description: 条件查询测试订单日志数据(分页)
     * @return  result
     * @exception
     **/
    HashMap<String,Object> getTestOrderLogs(TestOrderLogs testOrderLogs, PageBean page);
}
