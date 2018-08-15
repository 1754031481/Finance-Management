package com.zc.dao.logs;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.logs.LoginOperatingLogs;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @package : com.zc.dao.logs
 * @progect : Finance-Management
 * @Description :
 * @Author by :王楠
 * @Creation Date ：2018年06月07日10:42
 */
@MyBatisRepository
@Component
public interface LoginOperatingLogsMapper extends BaseMapper<LoginOperatingLogs> {

    /**
     * @return List<LoginOperatingLogs> 用户登录及操作日志列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取用户登录及操作日志列表
     */
    List<LoginOperatingLogs> getLoginOperatingListPage(LoginOperatingLogs loginOperatingLogs);
}
