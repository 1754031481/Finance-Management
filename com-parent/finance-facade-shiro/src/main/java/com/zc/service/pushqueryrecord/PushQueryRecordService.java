package com.zc.service.pushqueryrecord;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.pushqueryrecord.PushQueryRecord;
import com.zc.vo.PushQueryRecordVO;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.pushqueryrecord
 * @progect : Finance-Management
 * @Description : 已处理订单查询service接口层
 * @Author by :shiyunshun
 * @Creation Date ：2018年06月05日9:37
 */
public interface PushQueryRecordService {
    /**
     * @return 已处理订单列表
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 获取已处理订单列表
     */
    Map<String,Object> findOrderList(PageBean pageBean, PushQueryRecordVO pushQueryRecord);

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
     * @Description: 已处理记录下载查询
     */
    List<PushQueryRecord> getPushQueryRecord(String[] chk_value);

}
