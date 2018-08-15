package com.zc.controller.configuration;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ParamVerificationUtil;
import com.zc.entity.configuration.CITICQRcode;
import com.zc.entity.configuration.SPDQRcode;
import com.zc.service.configuration.SPDService;
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
 * @Description: 浦发控制器层
 **/
@Controller
@RequestMapping("configuration/SPD")
public class SPDController {

    //注入浦发服务
    @Reference
    private SPDService spdService;

    /**
     * @author 王楠
     * @version
     * @Description: 跳转浦发扫码渠道
     * @return 页面路径
     * @exception
     **/
    @RequestMapping("/QRcodeListPage")
    public String gotoQRcodeListPage(){

        return "configuration/SPDQRcode";
    }

    /**
     * @author 王楠
     * @version
     * @Description: 获取浦发扫码渠道列表
     * @return 浦发扫码渠道列表
     * @exception
     **/
    @RequestMapping("/QRcodeList")
    @ResponseBody
    public Object getQRcodeList(@ModelAttribute PageBean page){
        HashMap<String,Object> hashMap = spdService.getQRcodeList(page);
        return hashMap;
    }

    /**
     * @author 王楠
     * @version
     * @Description: 添加浦发扫码渠道
     * @return Result
     * @exception
     **/
    @RequestMapping(value = "/addQRcode",method = RequestMethod.POST)
    @ResponseBody
    public Result addQRcode(SPDQRcode spdqRcode){
        if (!ParamVerificationUtil.validate(spdqRcode)){
            return ResultUtils.returnError("传入参数错误");
        }
        Result result =  spdService.addQRcode(spdqRcode);
        return result;
    }

    /**
     * @author 王楠
     * @version
     * @Description: 修改浦发扫码渠道
     * @return Result
     * @exception
     **/
    @RequestMapping(value = "/updateQRcode",method = RequestMethod.POST)
    @ResponseBody
    public Result updateQRcode(SPDQRcode spdqRcode){
        if (!ParamVerificationUtil.validate(spdqRcode)){
            return ResultUtils.returnError("传入参数错误");
        }
        Result result = spdService.updateQRcode(spdqRcode);
        return result;
    }

}
