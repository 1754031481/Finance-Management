package com.zc.service.finance;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;

import java.util.HashMap;

/**
 * @author: 王楠
 * @version:
 * @Description: 代付交易历史记录服务层
 **/
public interface CashmentHistoryService {

    /**
     * @param page 分页信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 分页获取代付交易历史记录列表
     */
    HashMap<String,Object> getlist(PageBean page);

    /**
     * @param project 分页信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取一个项目一周代付交易历史记录列表
     */
    Result getWeekData(String project);

    /**
     * @param id
     * @return
     * @throws
     * @author ywj
     * @version
     * @Description: 更改启用状态
     */
    Result updateStatus(String ip,String name,Long id,Integer status);
}
