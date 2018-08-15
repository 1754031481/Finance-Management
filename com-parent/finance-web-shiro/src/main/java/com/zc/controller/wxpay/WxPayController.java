package com.zc.controller.wxpay;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.common.util.result.Result;
import com.zc.entity.finance.WxPayConfig;
import com.zc.service.finance.WxPayService;

;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author 杨文杰
 * @Title: WxPayController
 * @Description: 微信配置
 * @date 2018/4/10
 */

@Controller
@RequestMapping("/web/wxPay")
public class WxPayController {

    private static Logger logger = LoggerFactory.getLogger(WxPayController.class);

    @Reference
    private WxPayService wxPayService;


    /**
     * 跳转页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/10
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toWxPayPage",method = {RequestMethod.POST,RequestMethod.GET})
    public  String toWxPayPage(){
        return "wxpay/wxPayList";
    }

    /**
     * 分页获取微信配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toWxPay",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getWxPayList(PageBean pageBean){
        //logger.info("--------------获取微信配置数据Conntroller-----------------");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Result result = wxPayService.getList(pageBean);
        Cat.logEvent("WxPayController","获取微信配置列表", Event.SUCCESS,"result="+JSON.toJSON(result.getData()));
        //logger.info("--------------获取微信配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
        HashMap<String,Object> map= (HashMap<String, Object>) result.getData();
        return  map;
    }

    /**
     * 添加修改通用配置
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/updateWxPay",method = {RequestMethod.POST})
    @ResponseBody
    public  Result updateWxPay(WxPayConfig wxPayConfig){
        Cat.logEvent("WxPayController","添加修改微信配置入参", Event.SUCCESS,"wxPayConfig="+JSON.toJSON(wxPayConfig));
        //logger.info("--------------获取微信配置数据Conntroller,入参-----------------"+JSON.toJSON(wxPayConfig));
        if(!validate(wxPayConfig)){
            return ResultUtil.setResult(false,"参数为空！");
        }
            Result result = wxPayService.updateWxPay(wxPayConfig);
        Cat.logEvent("WxPayController","添加修改微信配置出参", Event.SUCCESS,"wxPayConfig="+JSON.toJSON(result.getData()));
        //logger.info("--------------获取微信配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
        return  result;
    }


    /**
     * 回显
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/getWxPay",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getWxPay(Long id){
        Cat.logEvent("WxPayController","查看微信配置信息入参", Event.SUCCESS,"id="+id);
        //logger.info("--------------查看微信配置信息Conntroller,id-----------------"+id);
        if(null==id){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Result result = wxPayService.getWxPay(id);
        Cat.logEvent("WxPayController","查看微信配置信息出参", Event.SUCCESS,"result="+JSON.toJSON(result));
        //logger.info("--------------获取微信配置数据Conntroller,出参"+JSON.toJSON(result));
        return  result;
    }

    /**
     * 下拉框展示
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/getWxSelect",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Result getWxSelect(){

        //logger.info("--------------展示下拉框Controller-----------------");
        Result result = wxPayService.getSelect();
        //logger.info("--------------展示下拉框Conntroller,出参"+JSON.toJSON(result));
        Cat.logEvent("WxPayController","展示下拉框", Event.SUCCESS,"result="+JSON.toJSON(result));
        return  result;
    }

    public boolean validate(Object obj){
        if(obj==null){
            return false;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields){
            boolean access = field.isAccessible();
            if(!access) {
                field.setAccessible(true);
            }
            try {
                Object o = field.get(obj);
                if(o instanceof String){
                    if(o==null||"".equals(o.toString().trim())){
                        return false;
                    }
                    field.set(obj,o.toString().trim());
                }else{
                    if(o==null){
                        return false;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
