package com.zc.controller.alpay;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.common.util.result.Result;
import com.zc.entity.finance.AlPayConfig;
import com.zc.service.finance.AlPayService;
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
 * @Title: AlPayController
 * @Description: 支付宝配置
 * @date 2018/4/8
 */

@Controller
@RequestMapping("/web/alPay")
public class AlPayController {

    private static Logger logger = LoggerFactory.getLogger(AlPayController.class);

    @Reference
    private AlPayService alPayService;


    /**
     * 跳转页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toAlPayPage",method = {RequestMethod.POST,RequestMethod.GET})
    public  String toAlPayPage(){
        return "alpay/alPayList";
    }

    /**
     * 分页获取支付宝配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toAlPay",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getAlPayList(PageBean pageBean){
      //  logger.info("--------------获取支付宝配置数据Conntroller-----------------");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Result result = alPayService.getList(pageBean);
       // logger.info("--------------获取支付宝配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
        Cat.logEvent("AlPayController","获取支付宝配置列表", Event.SUCCESS,"result="+JSON.toJSON(result.getData()));
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
    @RequestMapping(value = "/updateAlPay",method = {RequestMethod.POST})
    @ResponseBody
    public  Result updateAlPay(AlPayConfig alPayConfig){
       // logger.info("--------------获取支付宝配置数据Conntroller,入参-----------------"+JSON.toJSON(alPayConfig));
        Cat.logEvent("AlPayController","添加修改支付宝配置入参", Event.SUCCESS,"AlPayConfig="+JSON.toJSON(alPayConfig));
        if(!validate(alPayConfig)){
            return ResultUtil.setResult(false,"参数为空！");
        }
            Result result = alPayService.updateAlPay(alPayConfig);
        //logger.info("--------------获取支付宝配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
        Cat.logEvent("支付宝配置Controller","添加修改支付宝配置出参", Event.SUCCESS,"AlPayConfig="+JSON.toJSON(result.getData()));
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
    @RequestMapping(value = "/getAlPay",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getAlPay(Long id){
       // logger.info("--------------查看支付宝配置信息Conntroller,id-----------------"+id);
        Cat.logEvent("AlPayController","查看支付宝配置信息入参", Event.SUCCESS,"id="+id);
        if(null==id){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Result result = alPayService.getAlPay(id);
        //logger.info("--------------获取支付宝配置数据Conntroller,出参"+JSON.toJSON(result));
        Cat.logEvent("AlPayController","查看支付宝配置信息出参", Event.SUCCESS,"result="+JSON.toJSON(result));
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
    @RequestMapping(value = "/getSelect",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Result getSelect(){
        //logger.info("--------------展示下拉框Controller-----------------");
        Result result = alPayService.getSelect();
       // logger.info("--------------展示下拉框Conntroller,出参"+JSON.toJSON(result));
        Cat.logEvent("AlPayController","展示下拉框", Event.SUCCESS,"result="+JSON.toJSON(result));
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
