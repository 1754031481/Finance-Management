package com.zc.controller.xspay;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.common.util.result.Result;
import com.zc.entity.xsconfig.XSPayConfig;
import com.zc.service.xsconfig.XSPayService;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

;

/**
 * @author 杨文杰
 * @Title: XsPayController
 * @Description: 新生支付配置
 * @date 2018/4/11
 */

@Controller
@RequestMapping("/web/xsPay")
public class XSPayController {

    private static Logger logger = LoggerFactory.getLogger(XSPayController.class);

    @Reference
    private XSPayService XSPayService;


    /**
     * 跳转页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/10
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toXsPayPage",method = {RequestMethod.POST,RequestMethod.GET})
    public  String toXsPayPage(){
        return "xsconfig/xsPayList";
    }

    /**
     * 分页获取新生支付配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toXsPay",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getXsPayList(PageBean pageBean){
        //logger.info("--------------获取新生支付配置数据Conntroller-----------------");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Result result = XSPayService.getList(pageBean);
        Cat.logEvent("XSPayController","获取新生支付配置列表", Event.SUCCESS,"result="+JSON.toJSON(result.getData()));
       // logger.info("--------------获取新生支付配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
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
    @RequestMapping(value = "/updateXsPay",method = {RequestMethod.POST})
    @ResponseBody
    public  Result updateXsPay(XSPayConfig XSPayConfig){
        Cat.logEvent("XSPayController","添加修改新生支付配置入参", Event.SUCCESS,"XSPayConfig="+JSON.toJSON(XSPayConfig));
        //logger.info("--------------获取新生支付配置数据Conntroller,入参-----------------"+JSON.toJSON(XSPayConfig));
            Result result = XSPayService.updateXsPay(XSPayConfig);
        Cat.logEvent("XSPayController","添加修改新生支付配置出参", Event.SUCCESS,"XSPayConfig="+JSON.toJSON(result.getData()));
        //logger.info("--------------获取新生支付配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
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
    @RequestMapping(value = "/getXsPay",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getXsPay(Long id){
        Cat.logEvent("XSPayController","查看新生支付配置信息入参", Event.SUCCESS,"id="+id);
       // logger.info("--------------查看新生支付配置信息Conntroller,id-----------------"+id);
        if(null==id){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Result result = XSPayService.getXsPay(id);
        Cat.logEvent("XSPayController","查看新生支付配置信息出参", Event.SUCCESS,"result="+JSON.toJSON(result));
        //logger.info("--------------获取新生支付配置数据Conntroller,出参"+JSON.toJSON(result));
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
    @RequestMapping(value = "/getXsSelect",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Result getXsSelect(Long id){
        logger.info("--------------展示下拉框Controller-----------------");
        Result result = XSPayService.getSelect();
        logger.info("--------------展示下拉框Conntroller,出参"+JSON.toJSON(result));
        return  result;
    }



}
