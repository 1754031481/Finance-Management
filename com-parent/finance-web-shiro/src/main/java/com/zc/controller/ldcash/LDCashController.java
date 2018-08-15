package com.zc.controller.ldcash;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.common.util.result.Result;
import com.zc.entity.ldconfig.LDCashConfig;
import com.zc.service.ldconfig.LDCashService;
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
 * @Title: LdCashController
 * @Description: 联动优势代付配置
 * @date 2018/4/11
 */

@Controller
@RequestMapping("/web/ldCash")
public class LDCashController {

    private static Logger logger = LoggerFactory.getLogger(LDCashController.class);

    @Reference
    private LDCashService LDCashService;


    /**
     * 跳转页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/10
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toLdCashPage",method = {RequestMethod.POST,RequestMethod.GET})
    public  String toLdCashPage(){
        return "ldconfig/ldCashList";
    }

    /**
     * 分页获取联动优势代付配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toLdCash",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getLdCashList(PageBean pageBean){
        //logger.info("--------------获取联动优势代付配置数据Conntroller-----------------");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Result result = LDCashService.getList(pageBean);
        Cat.logEvent("LDCashController","获取联动优势代付配置列表", Event.SUCCESS,"result="+JSON.toJSON(result.getData()));
        //logger.info("--------------获取联动优势代付配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
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
    @RequestMapping(value = "/updateLdCash",method = {RequestMethod.POST})
    @ResponseBody
    public  Result updateLdCash(LDCashConfig LDCashConfig){
        Cat.logEvent("LDCashController","添加修改联动优势代付配置入参", Event.SUCCESS,"LDCashConfig="+JSON.toJSON(LDCashConfig));
        //logger.info("--------------获取联动优势代付配置数据Conntroller,入参-----------------"+JSON.toJSON(LDCashConfig));
            Result result = LDCashService.updateLdCash(LDCashConfig);
        if(!validate(LDCashConfig)){
            return ResultUtil.setResult(false,"参数为空！");
        }
        Cat.logEvent("LDCashController","添加修改联动优势代付配置出参", Event.SUCCESS,"LDCashConfig="+JSON.toJSON(result.getData()));
        //logger.info("--------------获取联动优势代付配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
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
    @RequestMapping(value = "/getLdCash",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getLdCash(Long id){
        Cat.logEvent("LDCashController","查看联动优势代付配置信息入参", Event.SUCCESS,"id="+id);
        //logger.info("--------------查看联动优势代付配置信息Conntroller,id-----------------"+id);
        if(null==id){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Result result = LDCashService.getLdCash(id);
        Cat.logEvent("LDCashController","查看联动优势代付配置信息出参", Event.SUCCESS,"result="+JSON.toJSON(result));
        //logger.info("--------------获取联动优势代付配置数据Conntroller,出参"+JSON.toJSON(result));
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
