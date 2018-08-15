package com.zc.service.checklog;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.checklog.CheckLog;
import com.zc.vo.CheckLogVO;
import com.zc.vo.PayContextVO;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.checklog
 * @progect : Finance-Management
 * @Description : 订单查询service接口层
 * @Author by :shiyunshun
 * @Creation Date ：2018年04月24日9:37
 */
public interface CheckLogService {
    Map<String,Object> findOrderList(PageBean pageBean, CheckLogVO checkLog);

    /**
     * @return 订单下拉列表
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 获取复审订单下拉列表
     */
      Result getChannelSel();
    /**
     * @return
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 复审记录添加(单条，批量)
     */
    Result  getBatchInsert(String[] chk_value);
    /**
     * @return
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 复审记录下载查询
     */
    List<CheckLog> getCheckLog(String[] chk_value);

    void saveInfo(Map<String,Object> initialize, String thirdPayType, CashReceiveStation crs,Integer type,String status);

    /**
     * @description: 根据订单号和项目来源查询checkLog表
     * @author:  ZhaoJunBiao
     * @date:  2018-06-25 11:05
     * @version: 1.0.0
     * @param orderNum
     * @param thirdPayType
     * @return
     */
    CheckLog entityByNumThirdPayType(String orderNum, String thirdPayType);
}
