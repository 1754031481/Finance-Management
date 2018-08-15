package com.zc.controller.ldpay;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.common.util.result.Result;
import com.zc.entity.ldconfig.LDPayConfig;
import com.zc.service.ldconfig.LDPayService;
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

;

/**
 * @author 杨文杰
 * @Title: LdPayController
 * @Description: 联动优势支付配置
 * @date 2018/4/11
 */

@Controller
@RequestMapping("/web/ldPay")
public class LDPayController {

    private static Logger logger = LoggerFactory.getLogger(LDPayController.class);

    @Reference
    private LDPayService LDPayService;


    /**
     * 跳转页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/10
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toLdPayPage",method = {RequestMethod.POST,RequestMethod.GET})
    public  String toLdPayPage(){
        return "ldconfig/ldPayList";
    }

    /**
     * 分页获取联动优势支付配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toLdPay",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getLdPayList(PageBean pageBean){
        //logger.info("--------------获取联动优势支付配置数据Conntroller-----------------");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Result result = LDPayService.getList(pageBean);
        Cat.logEvent("LDPayController","获取联动优势支付配置列表", Event.SUCCESS,"result="+JSON.toJSON(result.getData()));
        //logger.info("--------------获取联动优势支付配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
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
    @RequestMapping(value = "/updateLdPay",method = {RequestMethod.POST})
    @ResponseBody
    public  Result updateLdPay(LDPayConfig LDPayConfig){
        Cat.logEvent("LDPayController","添加修改联动优势支付配置入参", Event.SUCCESS,"LDPayConfig="+JSON.toJSON(LDPayConfig));
        //logger.info("--------------获取联动优势支付配置数据Conntroller,入参-----------------"+JSON.toJSON(LDPayConfig));
        if(!validate(LDPayConfig)){
            return ResultUtil.setResult(false,"参数为空！");
        }
            Result result = LDPayService.updateLdPay(LDPayConfig);
        Cat.logEvent("LDPayController","添加修改联动优势支付配置出参", Event.SUCCESS,"LDPayConfig="+JSON.toJSON(result.getData()));
        //logger.info("--------------获取联动优势支付配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
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
    @RequestMapping(value = "/getLdPay",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getLdPay(Long id){
        Cat.logEvent("LDPayController","查看联动优势支付配置信息入参", Event.SUCCESS,"id="+id);
        //logger.info("--------------查看联动优势支付配置信息Conntroller,id-----------------"+id);
        if(null==id){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Result result = LDPayService.getLdPay(id);
        Cat.logEvent("LDPayController","查看联动优势支付配置信息出参", Event.SUCCESS,"result="+JSON.toJSON(result));
        //logger.info("--------------获取联动优势支付配置数据Conntroller,出参"+JSON.toJSON(result));
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
