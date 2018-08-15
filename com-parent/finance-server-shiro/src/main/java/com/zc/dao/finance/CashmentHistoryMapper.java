package com.zc.dao.finance;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.finance.Finance;
import com.zc.entity.logs.LoginOperatingLogs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author: 王楠
 * @version:
 * @Description: 代付渠道设置DAO
 **/
@MyBatisRepository
@Component
public interface CashmentHistoryMapper extends BaseMapper<Finance> {

    /**
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 分页获取代付交易历史记录列表
     */
    List<Finance> getlist();

    /**
     * @param project 分页信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取一个项目一周代付交易历史记录列表
     */
    List<Finance> getWeekData(String project);

    int updateStatus(@Param("id") Long id,@Param("status") Integer status);

    void insertLog(LoginOperatingLogs loginOperatingLogs);

    Finance selectById(Long id);
}
