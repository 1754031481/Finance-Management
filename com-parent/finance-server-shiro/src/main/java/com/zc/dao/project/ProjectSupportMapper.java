package com.zc.dao.project;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.vo.project.ProjectSupportVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: 王楠
 * @version:
 * @Description: 项目支持DAO层
 **/
@MyBatisRepository
@Component
public interface ProjectSupportMapper {

    /**
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 分页获取项目支持列表
     */
    List<ProjectSupportVO> getProjectSupportlist();

    /**
     * @param projectSupportVO 项目支持信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加项目支持
     */
    void addProjectSupport(ProjectSupportVO projectSupportVO);

    /**
     * @param projectSupportVO 项目支持信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改项目支持
     */
    void updateProjectSupport(ProjectSupportVO projectSupportVO);


    /**
     * @return 支付接口列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取支付接口列表
     */
    List<String> getPayInterface();


    /**
     * @return 代付接口列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取代付接口列表
     */
    List<String> getCashInterface();

}
