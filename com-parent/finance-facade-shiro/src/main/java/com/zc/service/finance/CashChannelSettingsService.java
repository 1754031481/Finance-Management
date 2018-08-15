package com.zc.service.finance;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.vo.project.ProjectSupportVO;

import java.util.HashMap;

/**
 * @author: 王楠
 * @version:
 * @Description: 代付渠道设置控制器层
 **/
public interface CashChannelSettingsService {


    /**
     * @param page 分页信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 分页获代付渠道设置列表
     */
    HashMap<String,Object> getlist(PageBean page);

    /**
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取支付代付接口列表
     */
    Result getInterface();

    /**
     * @param projectSupportVO 代付渠道设置信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改代付渠道设置
     */
    Result updateSettings(ProjectSupportVO projectSupportVO);
}
