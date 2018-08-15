package com.zc.dao.finance;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.vo.project.ProjectSupportVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: 王楠
 * @version:
 * @Description: 代付渠道设置DAO
 **/
@MyBatisRepository
@Component
public interface CashChannelSettingsMapper {

    /**
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 分页获代付渠道设置列表
     */
    List<ProjectSupportVO> getlist();

    /**
     * @param projectSupportVO 代付渠道设置信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改代付渠道设置
     */
    void updateSettings(ProjectSupportVO projectSupportVO);
}
