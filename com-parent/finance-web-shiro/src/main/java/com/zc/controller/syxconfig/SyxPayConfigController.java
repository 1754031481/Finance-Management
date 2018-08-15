package com.zc.controller.syxconfig;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.ffconfig.FFPayConfig;
import com.zc.entity.syxconfig.SYXPayConfig;
import com.zc.service.syxconfig.SyxPayConfigService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @package : com.zc.controller.syxconfig
 * @progect : Finance-Management
 * @Description :   商银信支付控制层
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月10日9:28
 */
@Controller
@RequestMapping("pc/view/syxPayView")
public class SyxPayConfigController {

    private Logger logger = Logger.getLogger(SyxPayConfigController.class);

    @Reference
    private SyxPayConfigService syxPayConfigService;

    /**
     * @return
     * @description: 跳转商银信支付
     * @author: ZhaoJunBiao
     * @date: 2018-04-10 9:34
     * @version: 1.0.0
     */
    @RequestMapping("toSyxView")
    public String toSyxPayView() {
        return "syxconfig/syxpay";
    }

    /**
     * @return
     * @description: 商银信支付配置列表
     * @author: ZhaoJunBiao
     * @date: 2018-04-10 9:41
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object findSyxPayList(PageBean pageBean) {
       Map<String,Object> syxList =  syxPayConfigService.findSyxPayList(pageBean);
        return syxList;
    }


    /**
     * @description: 商银信支付配置修改（增加）
     * @author:  ZhaoJunBiao
     * @date:  2018-04-10 11:49
     * @version: 1.0.0
     * @param syxPayConfig
     * @return
     */
    @RequestMapping("updateOrSave")
    @ResponseBody
    public Object updateOrSaveSyxPayConfig(SYXPayConfig syxPayConfig){
//        logger.info("商银信支付配置添加方法调用");
        Cat.logEvent("SyxPayConfigController","添加修改商银信支付配置入参", Event.SUCCESS,"SYXPayConfig="+JSON.toJSON(syxPayConfig));
        Result result = new Result();
        if(syxPayConfig.getId() == null){
             result = syxPayConfigService.addSyxPayConfig(syxPayConfig);
        }else{
            result = syxPayConfigService.updateSyxPayConfig(syxPayConfig);
        }
        return  result;
    }

    /**
     * @description: 查看SystemForm
     * @author:  ZhaoJunBiao
     * @date:  2018-04-10 13:33
     * @version: 1.0.0
     * @return
     */
    @RequestMapping("findSystemForm")
    @ResponseBody
    public Result findSystemForm(){
        Result result = syxPayConfigService.findSystemForm();
        return  result;
    }

}
