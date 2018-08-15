package com.zc.controller.ffcash;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.ffconfig.FFCashConfig;
import com.zc.service.ffconfig.FfCashConfigService;
import com.zc.service.systemfrom.SystemFromService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * @package : com.zc.controller.ffpay
 * @progect : Finance-Management
 * @Description :丰付代付渠道(增加cat日志埋点)
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月08日14:20
 */
@Controller
@RequestMapping("pc/view/ffCashConfig")
public class FfCashController {

    private Logger logger = Logger.getLogger(FfCashController.class);

    @Reference
    private FfCashConfigService ffCashConfigService;
    @Reference
    private SystemFromService systemFromService;


    /**
     * @return
     * @description: 跳转丰付代付
     * @author: ZhaoJunBiao
     * @date: 2018-04-08 14:35
     * @version: 1.0.0
     */
    @RequestMapping("showToCashFind")
    public String showToFind() {
        return "ffconfig/ffcash";
    }


    /**
     * @param pageBean
     * @return
     * @description: 丰付代付配置信息分页展示
     * @author: ZhaoJunBiao
     * @date: 2018-04-08 14:37
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object showFfPayConfig(PageBean pageBean) {
        Cat.logEvent("丰付代付配置Controller", "传化代付配置信息分页展示");
        Map<String, Object> map = ffCashConfigService.getFfCashConfig(pageBean);
        return map;
    }

    /**
     * @description: 查看
     * @author:  ZhaoJunBiao
     * @date:  2018-04-09 15:37
     * @version: 1.0.0
     * @param ffCashId
     * @return
     */
    @RequestMapping("findFfCash")
    @ResponseBody
    public Object findFfPay(Long ffCashId) {
        Map<String, Object> map = ffCashConfigService.findCashConfig(ffCashId);
        return map;
    }


    /**
     * @param ffCashConfig
     * @return
     * @description: 丰付代付配置修改（添加）
     * @author: ZhaoJunBiao
     * @date: 2018-04-08 19:02
     * @version: 1.0.0
     */
    @RequestMapping(value = "updateOrSaveFfCash", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrSavevFfPayConfig(FFCashConfig ffCashConfig) {
//        logger.info("丰付代付配置添加方法调用");
        Cat.logEvent("FfCashController","添加修改丰付代付配置入参", Event.SUCCESS,"FFCashConfig="+JSON.toJSON(ffCashConfig));
        Map<String, Object> map = null;
            if (ffCashConfig.getId() == null) {
                ffCashConfig.setCreatedTime(new Date());
                ffCashConfig.setUpdateTime(new Date());
                map = ffCashConfigService.addFfCashConfig(ffCashConfig);
                return map;
            } else {
                map = ffCashConfigService.updateFfCashConfig(ffCashConfig);
                return map;
            }
    }



}
