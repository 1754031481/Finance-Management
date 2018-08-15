package com.zc.service.impl.logs;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.logs.LoginOperatingLogsMapper;
import com.zc.entity.logs.LoginOperatingLogs;
import com.zc.service.logs.LoginOperatingLogsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @package : com.zc.service.impl.logs
 * @progect : Finance-Management
 * @Description :
 * @Author by :王楠
 * @Creation Date ：2018年06月07日10:42
 */
@Service
public class LoginOperatingLogsServiceImpl implements LoginOperatingLogsService {


    Logger logger = LoggerFactory.getLogger(LoginOperatingLogsServiceImpl.class);

    @Autowired
    private LoginOperatingLogsMapper loginOperatingLogsMapper;


    /**
     * @param page 分页信息
     * @return HashMap<String, Object>
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取用户登录及操作日志列表
     */
    @Override
    public HashMap<String, Object> getLoginOperatingListPage(PageBean page,LoginOperatingLogs loginOperatingLogs) {
        logger.info("===================================获取用户登录及操作日志列表服务开始入参 page："+JSONObject.toJSONString(page)+"loginOperatingLogs："+loginOperatingLogs);
        HashMap<String,Object> map=new HashMap<>();
        try {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<LoginOperatingLogs> list = loginOperatingLogsMapper.getLoginOperatingListPage(loginOperatingLogs);
            logger.info("---------------------------数据库查询用户登录及操作日志列表返回数据："+JSONObject.toJSONString(list));
            PageInfo<LoginOperatingLogs> info = new PageInfo<>(list);
            map.put("code",200);
            map.put("msg","操作成功");
            map.put("content",info.getList());
            map.put("count",info.getTotal());
            logger.info("===============================获取用户登录及操作日志列表服务返回："+JSONObject.toJSONString(map));
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","服务器内部异常");
            logger.error("===============================获取用户登录及操作日志列表服务异常====================================");
        }
        return map;
    }

    /**
     * @param loginOperatingLogs 日志信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加用户登录及操作日志
     **/
    @Override
    public Result addLoginOperatingLogs(LoginOperatingLogs loginOperatingLogs) {
        logger.info("===================================添加用户登录及操作日志服务开始入参 loginOperatingLogs："+JSONObject.toJSONString(loginOperatingLogs));
        Result result = new Result();
        loginOperatingLogs.setCreatedTime(new Date());
        loginOperatingLogs.setUpdateTime(new Date());
        loginOperatingLogsMapper.insert(loginOperatingLogs);
        result.setCode(1);
        result.setMsg("日志插入成功成功");
        logger.info("===============================添加用户登录及操作日志服务返回参数："+JSONObject.toJSONString(result));
        return result;
    }
}
