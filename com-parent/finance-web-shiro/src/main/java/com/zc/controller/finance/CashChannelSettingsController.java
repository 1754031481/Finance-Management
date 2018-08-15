package com.zc.controller.finance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ParamVerificationUtil;
import com.zc.service.finance.CashChannelSettingsService;
import com.zc.vo.project.ProjectSupportVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author: 王楠
 * @version:
 * @Description: 项目支持控制器层
 **/
@Controller
@RequestMapping("finance/cashChannelSettings")
public class CashChannelSettingsController {

    @Reference
    private CashChannelSettingsService cashChannelSettingsService;

    /**
     * @author 王楠
     * @version
     * @Description: 跳转代付渠道设置页面
     * @return 页面路径
     * @exception
     **/
    @RequestMapping("/listPage")
    public String gotoProjectSupport(){

        return "finance/cashChannelSettings";
    }

    /**
     * @param page 分页信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 分页代付渠道设置列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getlist(@ModelAttribute PageBean page) {
        HashMap<String,Object> hashMap = cashChannelSettingsService.getlist(page);
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
        Result result = cashChannelSettingsService.getInterface();
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
    public Result updateSettings(ProjectSupportVO projectSupportVO) {
        /*if (StringUtils.isEmpty(projectSupportVO.getCashInterface()) || StringUtils.isEmpty(projectSupportVO.getSupportStatue())){
            Result result = ResultUtils.returnError("传入参数错误");
            return result;
        }*/
        if (StringUtils.isEmpty(projectSupportVO.getCashInterface())){
            projectSupportVO.setCashInterface(null);
        }
        Result result = cashChannelSettingsService.updateSettings(projectSupportVO);
        return result;
    }
}
