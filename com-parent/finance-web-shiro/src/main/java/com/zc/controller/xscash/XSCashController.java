package com.zc.controller.xscash;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.common.util.result.Result;
import com.zc.entity.xsconfig.XSCashConfig;
import com.zc.service.xsconfig.XSCashService;
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
 * @Title: XsCashController
 * @Description: 新生代付配置
 * @date 2018/4/11
 */

@Controller
@RequestMapping("/web/xsCash")
public class XSCashController {

    private static Logger logger = LoggerFactory.getLogger(XSCashController.class);

    @Reference
    private XSCashService XSCashService;


    /**
     * 跳转页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/10
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toXsCashPage",method = {RequestMethod.POST,RequestMethod.GET})
    public  String toXsCashPage(){
        return "xsconfig/xsCashList";
    }

    /**
     * 分页获取新生代付配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toXsCash",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getXsCashList(PageBean pageBean){
        //logger.info("--------------获取新生代付配置数据Conntroller-----------------");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Result result = XSCashService.getList(pageBean);
        Cat.logEvent("XSCashController","获取新生代付配置列表", Event.SUCCESS,"result="+JSON.toJSON(result.getData()));
        //logger.info("--------------获取新生代付配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
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
    @RequestMapping(value = "/updateXsCash",method = {RequestMethod.POST})
    @ResponseBody
    public  Result updateXsCash(XSCashConfig XSCashConfig){
        //logger.info("--------------获取新生代付配置数据Conntroller,入参-----------------"+JSON.toJSON(XSCashConfig));
        Cat.logEvent("XSCashController","添加修改新生代付配置入参", Event.SUCCESS,"XSCashConfig="+JSON.toJSON(XSCashConfig));
        if(!validate(XSCashConfig)){
            return ResultUtil.setResult(false,"参数为空！");
        }
            Result result = XSCashService.updateXsCash(XSCashConfig);
        Cat.logEvent("XSCashController","添加修改新生代付配置出参", Event.SUCCESS,"XSCashConfig="+JSON.toJSON(result.getData()));
        //logger.info("--------------获取新生代付配置数据Conntroller,出参"+JSON.toJSON(result.getData()));
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
    @RequestMapping(value = "/getXsCash",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getXsCash(Long id){
        Cat.logEvent("XSCashController","查看新生代付配置信息入参", Event.SUCCESS,"id="+id);
        //logger.info("--------------查看新生代付配置信息Conntroller,id-----------------"+id);
        if(null==id){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Result result = XSCashService.getXsCash(id);
        Cat.logEvent("XSCashController","查看新生代付配置信息出参", Event.SUCCESS,"result="+JSON.toJSON(result));
        //logger.info("--------------获取新生代付配置数据Conntroller,出参"+JSON.toJSON(result));
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
        Result result = XSCashService.getSelect();
        logger.info("--------------展示下拉框Conntroller,出参"+JSON.toJSON(result));
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
