package com.zc.service.logs;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.logs.LoginOperatingLogs;

import java.util.HashMap;

/**
 * @package : com.zc.service.logs
 * @progect : Finance-Management
 * @Description :
 * @Author by :王楠
 * @Creation Date ：2018年06月07日10:42
 */
public interface LoginOperatingLogsService {

    /**
     * @author 王楠
     * @version
     * @Description: 获取用户登录及操作日志列表
     * @return 用户登录及操作日志列表
     * @exception
     **/
    HashMap<String,Object> getLoginOperatingListPage(PageBean page, LoginOperatingLogs loginOperatingLogs);

    /**
     * @param loginOperatingLogs 日志信息
     * @author 王楠
     * @version
     * @Description: 添加用户登录及操作日志
     * @return Result
     * @exception
     **/
    Result addLoginOperatingLogs(LoginOperatingLogs loginOperatingLogs);
}
