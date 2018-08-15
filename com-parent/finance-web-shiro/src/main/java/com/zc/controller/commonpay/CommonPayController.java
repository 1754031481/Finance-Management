package com.zc.controller.commonpay;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.common.util.result.Result;
import com.zc.entity.finance.CommonPayConfig;
import com.zc.service.finance.CommonPayService;
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
 * @Title: CommonPayController
 * @Description: 通用配置
 * @date 2018/4/8
 */

@Controller
@RequestMapping("/web/commonPay")
public class CommonPayController {

    private static Logger logger = LoggerFactory.getLogger(CommonPayController.class);

    @Reference
    private CommonPayService commonPayService;


    /**
     * 跳转页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toCommonPayPage",method = {RequestMethod.POST,RequestMethod.GET})
    public  String toCommonPayPage(){
        return "commonpay/commonPayList";
    }

    /**
     * 分页获取通用配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toCommonPay",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getCommonPayList(PageBean pageBean){
        //logger.info("--------------获取通用配置数据Conntroller-----------------");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Result result = commonPayService.getList(pageBean);
        Cat.logEvent("CommonPayController","获取通用配置列表", Event.SUCCESS,"result="+JSON.toJSON(result.getData()));
        //logger.info("--------------获取通用配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
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
    @RequestMapping(value = "/updateCommonPay",method = {RequestMethod.POST})
    @ResponseBody
    public  Result updateCommonPay(CommonPayConfig commonPayConfig){
        //logger.info("--------------获取通用配置数据Conntroller,入参-----------------"+JSON.toJSON(commonPayConfig));
        Cat.logEvent("CommonPayController","添加修改通用配置入参", Event.SUCCESS,"commonPayConfig="+JSON.toJSON(commonPayConfig));
        if(!validate(commonPayConfig)){
            return ResultUtil.setResult(false,"参数为空！");
        }
        Result result = commonPayService.updateCommonPay(commonPayConfig);
        Cat.logEvent("CommonPayController","添加修改通用配置出参", Event.SUCCESS,"commonPayConfig="+JSON.toJSON(result.getData()));
        //logger.info("--------------获取通用配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
        return  result;
    }


    /**
     * 回显
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/getCommonPay",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getCommonPay(Long id){
        Cat.logEvent("CommonPayController","查看通用配置信息入参", Event.SUCCESS,"id="+id);
        //logger.info("--------------查看通用配置信息Conntroller,id-----------------"+id);
        if(null==id){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Result result = commonPayService.getCommonPay(id);
        Cat.logEvent("CommonPayController","查看通用配置信息出参", Event.SUCCESS,"result="+JSON.toJSON(result));
        //logger.info("--------------获取通用配置数据Conntroller,出参"+JSON.toJSON(result));
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
