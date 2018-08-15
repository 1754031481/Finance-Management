package com.zc.controller.logs;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.logs.LoginOperatingLogs;
import com.zc.service.logs.LoginOperatingLogsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @package : com.zc.controller.logs
 * @progect : Finance-Management
 * @Description :
 * @Author by :王楠
 * @Creation Date ：2018年06月07日10:42
 */
@Controller
@RequestMapping("logs/loginOperating")
public class LoginOperatingLogsController {

    @Reference
    private LoginOperatingLogsService loginOperatingLogsService;


    /**
     * @author 王楠
     * @version
     * @Description: 跳转操作日志登录及操作页面
     * @return 页面路径
     * @exception
     **/
    @RequestMapping("listPage")
    public String gotoLoginOperatingListPage(){
        return "logs/loginOperating";
    }

    /**
     * @author 王楠
     * @version
     * @Description: 获取用户登录及操作日志列表
     * @return 用户登录及操作日志列表
     * @exception
     **/
    @RequestMapping("/loginOperatingList")
    @ResponseBody
    public Object getLoginOperatingList(@ModelAttribute PageBean page, LoginOperatingLogs loginOperatingLogs,String createTime){

        HashMap<String,Object> hashMap = null;

        if (StringUtils.isBlank(loginOperatingLogs.getCreateUser())){
            loginOperatingLogs.setCreateUser(null);
        }

        if (StringUtils.isBlank(createTime)){
            createTime = null;
        }

        if (createTime != null){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = dateFormat.parse(createTime);
                loginOperatingLogs.setCreatedTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
                hashMap.put("code",500);
                hashMap.put("msg","时间格式错误");
                return hashMap;
            }
        }

        hashMap = loginOperatingLogsService.getLoginOperatingListPage(page,loginOperatingLogs);
        return hashMap;
    }
}
