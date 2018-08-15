package com.zc.controller.syxconfig;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.syxconfig.SYXCashConfig;
import com.zc.entity.syxconfig.SYXPayConfig;
import com.zc.service.syxconfig.SyxCashConfigService;
import com.zc.service.syxconfig.SyxPayConfigService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @package : com.zc.controller.syxconfig
 * @progect : Finance-Management
 * @Description :   商银信代付控制层
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月10日9:28
 */
@Controller
@RequestMapping("pc/view/syxCashView")
public class SyxCashConfigController {

    private Logger logger = Logger.getLogger(SyxCashConfigController.class);

    @Reference
    private SyxCashConfigService syxCashConfigService;

    /**
     * @return
     * @description: 跳转商银信代付
     * @author: ZhaoJunBiao
     * @date: 2018-04-10 9:34
     * @version: 1.0.0
     */
    @RequestMapping("toSyxCashView")
    public String toSyxPayView() {
        return "syxconfig/syxcash";
    }

    /**
     * @return
     * @description: 商银信代付配置列表
     * @author: ZhaoJunBiao
     * @date: 2018-04-10 9:41
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object findSyxPayList(PageBean pageBean) {
       Map<String,Object> syxList =  syxCashConfigService.findSyxCashList(pageBean);
        return syxList;
    }


    /**
     * @description: 商银信代付配置修改（增加）
     * @author:  ZhaoJunBiao
     * @date:  2018-04-10 11:49
     * @version: 1.0.0
     * @param syxCashConfig
     * @return
     */
    @RequestMapping("updateOrSave")
    @ResponseBody
    public Object updateOrSaveSyxPayConfig(SYXCashConfig syxCashConfig){
//        logger.info("商银信代付配置添加方法调用");
        Cat.logEvent("SyxCashConfigController","添加修改商银信代付配置入参", Event.SUCCESS,"SYXCashConfig="+JSON.toJSON(syxCashConfig));
        Result result = new Result();
        if(syxCashConfig.getId() == null){
             result = syxCashConfigService.addSyxCashConfig(syxCashConfig);
        }else{
            result = syxCashConfigService.updateSyxCashConfig(syxCashConfig);
        }
        return  result;
    }


}
