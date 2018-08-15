package com.zc.dao.abouttesting;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.logs.TestOrderLogs;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @package : com.zc.service.abouttesting
 * @progect : Finance-Management
 * @Description :
 * @Author by :王楠
 * @Creation Date ：2018年06月04日10:42
 */
@MyBatisRepository
@Component
public interface TestOrderLogsMapper extends BaseMapper<TestOrderLogs> {


    /**
     * @return List<TestOrderLogs> 测试订单日志
     * @param testOrderLogs 筛选条件
     * @throws
     * @author 王楠
     * @version
     * @Description: 查询测试订单修改日志
     */
    List<TestOrderLogs> getTestOrderLogs(TestOrderLogs testOrderLogs);
}
