package com.zc.service.project;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.vo.project.ProjectSupportVO;

import java.util.HashMap;

/**
 * @author: 王楠
 * @version:
 * @Description: 项目支持服务层
 **/
public interface ProjectSupportService {

    /**
     * @param page 分页信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 分页获取项目支持列表
     */
    HashMap<String,Object> getProjectSupportlist(PageBean page);

    /**
     * @param projectSupportVO 项目支持信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加项目支持
     */
    Result addProjectSupport(ProjectSupportVO projectSupportVO);

    /**
     * @param projectSupportVO 项目支持信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改项目支持
     */
    Result updateProjectSupport(ProjectSupportVO projectSupportVO);

    /**
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取支付代付接口列表
     */
    Result getInterface();
}
