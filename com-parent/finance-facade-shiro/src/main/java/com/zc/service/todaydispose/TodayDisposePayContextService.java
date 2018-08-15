package com.zc.service.todaydispose;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.checklog.CheckLog;
import com.zc.entity.paycontext.PayContext;
import com.zc.vo.CheckLogVO;
import com.zc.vo.PayContextVO;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.paycontext
 * @progect : Finance-Management
 * @Description : 订单查询service接口层
 * @Author by :shiyunshun
 * @Creation Date ：2018年04月24日9:37
 */
public interface TodayDisposePayContextService {


    /**
     * @return
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 今日处理中列表
     */
    Map<String,Object> findOrderList(PageBean pageBean,PayContextVO payContext);

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
     * @Description: 今日处理中添加(单条，批量)
     */
    Result  getBatchInsert(String[] chk_value);
    /**
     * @return
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 今日处理中复审记录下载查询
     */
    List<PayContext> getPayContext(String[] chk_value);

}
