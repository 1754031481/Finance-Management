package com.zc.controller.project;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.CommonConstants;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ParamVerificationUtil;
import com.zc.entity.logs.LoginOperatingLogs;
import com.zc.service.finance.FinanceService;
import com.zc.service.logs.LoginOperatingLogsService;
import com.zc.service.project.ProjectSupportService;
import com.zc.service.systemfrom.SystemFromService;
import com.zc.utils.IpUtil;
import com.zc.vo.permission.SessionUserVO;
import com.zc.vo.project.ProjectSupportVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 王楠
 * @version:
 * @Description: 项目支持控制器层
 **/
@Controller
@RequestMapping("project/projectSupport")
public class ProjectSupportController {


    @Reference
    private ProjectSupportService projectSupportService;
    @Reference
    private FinanceService financeService;
    @Reference
    private SystemFromService systemFromService;
    @Reference
    private LoginOperatingLogsService loginOperatingLogsService;

    /**
     * @author 王楠
     * @version
     * @Description: 跳转项目支持页面
     * @return 页面路径
     * @exception
     **/
    @RequestMapping("/listPage")
    public String gotoProjectSupport(){

        return "project/projectSupport";
    }

    /**
     * @param page 分页信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 分页获取项目支持列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getProjectSupportlist(@ModelAttribute PageBean page) {
        HashMap<String,Object> hashMap = projectSupportService.getProjectSupportlist(page);
        return hashMap;
    }

    /**
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取支付代付接口列表
     */
    @RequestMapping(value = "/getInterface", method = RequestMethod.GET)
    @ResponseBody
    public Result getInterface() {
        Result result = projectSupportService.getInterface();
        return result;
    }

    /**
     * @param projectSupportVO 添加
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加项目支持
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addProjectSupport(ProjectSupportVO projectSupportVO){
        /*if (!ParamVerificationUtil.validate(projectSupportVO)){
            return ResultUtils.returnError("传入参数错误");
        }*/
        if (StringUtils.isEmpty(projectSupportVO.getCashInterface())){
            projectSupportVO.setCashInterface(null);
        }
        if (StringUtils.isEmpty(projectSupportVO.getPayInterface())){
            projectSupportVO.setPayInterface(null);
        }
        Result result = projectSupportService.addProjectSupport(projectSupportVO);
        return result;
    }



    /**
     * @param projectSupportVO 项目支持信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改项目支持
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateProjectSupport(ProjectSupportVO projectSupportVO) {
        /*if (!ParamVerificationUtil.validate(projectSupportVO)){
            return ResultUtils.returnError("传入参数错误");
        }*/
        if (StringUtils.isEmpty(projectSupportVO.getCashInterface())){
            projectSupportVO.setCashInterface(null);
        }
        if (StringUtils.isEmpty(projectSupportVO.getPayInterface())){
            projectSupportVO.setPayInterface(null);
        }
        Result result = projectSupportService.updateProjectSupport(projectSupportVO);
        return result;
    }

    /**
     * @description: 重发今日代付
     * @author:  ZhaoJunBiao
     * @date:  2018-04-24 17:25
     * @version: 1.0.0
     * @param systemName
     * @return
     */
    @RequestMapping("rechargeToDayPayment")
    @ResponseBody
    public Result rechargeToDayPayment(String systemName,HttpSession session,HttpServletRequest request,String projectName){
        SessionUserVO userVO = (SessionUserVO)session.getAttribute(CommonConstants.LOGIN_BACK_USER_SESSION);
        if (userVO == null){
            return ResultUtils.returnError("用户未登录");
        }
        String userName = userVO.getUserName();
        if(StringUtils.isBlank(systemName)){
            return ResultUtils.returnError("项目标识不能为null");
        }
        Result result = financeService.rechargeToDayPayment(systemName);
        if (result.getCode()==0){
            LoginOperatingLogs loginOperatingLogs = new LoginOperatingLogs();
            loginOperatingLogs.setCreateUser(userName);
            loginOperatingLogs.setCreatedIp(IpUtil.getIpAddr(request));
            loginOperatingLogs.setOperationLogs("用户："+userName+",重发"+projectName+"项目失败代付订单");
            loginOperatingLogsService.addLoginOperatingLogs(loginOperatingLogs);
        }

        return 	result;
    }

    /**
     * @description: 审核失败回调
     * @author:  ZhaoJunBiao
     * @date:  2018-04-25 9:30
     * @version: 1.0.0
     * @param systemName
     * @return
     */
    @RequestMapping("auditFailedCallBack")
    @ResponseBody
    public Result auditFailedCallBack(String  systemName){
        if(StringUtils.isBlank(systemName)){
            return ResultUtils.returnError("项目标识不能为null");
        }
        return 	systemFromService.sendFailCash(systemName);
    }
}
