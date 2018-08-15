package com.zc.controller.configuration;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ParamVerificationUtil;
import com.zc.entity.configuration.YFCashConfig;
import com.zc.entity.configuration.YFPayConfig;
import com.zc.service.configuration.YFConfigService;
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
 * @Description: 裕福配置控制层
 **/

@Controller
@RequestMapping("configuration/YFConfig")
public class YFConfigController {

    //注入裕福配置服务
    @Reference
    private YFConfigService yfConfigService;

    /**
     * @return 页面路径
     * @throws
     * @author 王楠
     * @version
     * @Description: 跳转裕福渠道支付配置
     */
    @RequestMapping("/payListPage")
    public String gotoPayListPage(){

        return "configuration/YFpayConfig";
    }

    /**
     * @param page 页面信息
     * @return 裕福渠道支付配置列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取裕福渠道支付配置列表
     */
    @RequestMapping("/payList")
    @ResponseBody
    public Object getPayList(@ModelAttribute PageBean page){
        HashMap<String,Object> hashMap = yfConfigService.getPayList(page);
        return hashMap;
    }

    /**
     * @param yfPayConfig 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加裕福渠道支付配置
     */
    @RequestMapping(value = "/addPay",method = RequestMethod.POST)
    @ResponseBody
    public Result addPay(YFPayConfig yfPayConfig){
        if (!ParamVerificationUtil.validate(yfPayConfig)){
            return ResultUtils.returnError("传入参数错误");
        }
        Result result = yfConfigService.addPay(yfPayConfig);
        return result;
    }

    /**
     * @param yfPayConfig 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改裕福渠道支付配置
     */
    @RequestMapping(value = "/updatePay",method = RequestMethod.POST)
    @ResponseBody
    public Result updatePay(YFPayConfig yfPayConfig){
        if (!ParamVerificationUtil.validate(yfPayConfig)){
            return ResultUtils.returnError("传入参数错误");
        }
        Result result = yfConfigService.updatePay(yfPayConfig);
        return result;
    }

    /**
     * @return 页面路径
     * @throws
     * @author 王楠
     * @version
     * @Description: 跳转裕福渠道代付配置
     */
    @RequestMapping("/cashListPage")
    public String gotoCashListPage(){

        return "configuration/YFcashConfig";
    }

    /**
     * @param page 页面信息
     * @return 裕福渠道支付配置列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取裕福渠道代付配置列表
     */
    @RequestMapping("/cashList")
    @ResponseBody
    public Object getCashList(@ModelAttribute PageBean page){
        HashMap<String,Object> hashMap = yfConfigService.getCashList(page);
        return hashMap;
    }

    /**
     * @param yfCashConfig 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加裕福渠道代付配置
     */
    @RequestMapping(value = "/addCash",method = RequestMethod.POST)
    @ResponseBody
    public Result addCash(YFCashConfig yfCashConfig){
        if (!ParamVerificationUtil.validate(yfCashConfig)){
            return ResultUtils.returnError("传入参数错误");
        }
        Result result = yfConfigService.addCash(yfCashConfig);
        return result;
    }

    /**
     * @param yfCashConfig 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改裕福渠道代付配置
     */
    @RequestMapping(value = "/updateCash",method = RequestMethod.POST)
    @ResponseBody
    public Result updateCash(YFCashConfig yfCashConfig){
        if (!ParamVerificationUtil.validate(yfCashConfig)){
            return ResultUtils.returnError("传入参数错误");
        }
        Result result = yfConfigService.updateCash(yfCashConfig);
        return result;
    }


}
