package com.zc.controller.configuration;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ParamVerificationUtil;
import com.zc.entity.configuration.CITICQRcode;
import com.zc.service.configuration.CITICService;
import com.zc.vo.configuration.CITICQRcodeVO;
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
 * @Description: 中信控制器层
 **/
@Controller
@RequestMapping("configuration/CITIC")
public class CITICController {


    //注入中信服务
    @Reference
    private CITICService citicService;


    /**
     * @author 王楠
     * @version
     * @Description: 跳转中信扫码渠道
     * @return 页面路径
     * @exception
     **/
    @RequestMapping("/QRcodeListPage")
    public String gotoQRcodeListPage(){

        return "configuration/CITICQRcode";
    }

    /**
     * @author 王楠
     * @version
     * @Description: 获取中信扫码渠道列表
     * @return 中信扫码渠道列表
     * @exception
     **/
    @RequestMapping("/QRcodeList")
    @ResponseBody
    public Object getQRcodeList(@ModelAttribute PageBean page){
        HashMap<String,Object> hashMap = citicService.getQRcodeList(page);
        return hashMap;
    }

    /**
     * @author 王楠
     * @version
     * @Description: 添加中信扫码渠道
     * @return Result
     * @exception
     **/
    @RequestMapping(value = "/addQRcode",method = RequestMethod.POST)
    @ResponseBody
    public Result addQRcode(CITICQRcode citicqRcode){
        if (!ParamVerificationUtil.validate(citicqRcode)){
            return ResultUtils.returnError("传入参数错误");
        }
        Result result = citicService.addQRcode(citicqRcode);
        return result;
    }

    /**
     * @author 王楠
     * @version
     * @Description: 修改中信扫码渠道
     * @return Result
     * @exception
     **/
    @RequestMapping(value = "/updateQRcode",method = RequestMethod.POST)
    @ResponseBody
    public Result updateQRcode(CITICQRcode citicqRcode){
        if (!ParamVerificationUtil.validate(citicqRcode)){
            return ResultUtils.returnError("传入参数错误");
        }
        Result result = null;
        try {
            result = citicService.updateQRcode(citicqRcode);
        } catch (Exception e) {
            result = ResultUtils.returnError("服务器内部异常");
            e.printStackTrace();
        }
        return result;
    }
}
