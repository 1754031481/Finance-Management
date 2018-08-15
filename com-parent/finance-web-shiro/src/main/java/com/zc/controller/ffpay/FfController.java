package com.zc.controller.ffpay;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.ffconfig.FFPayConfig;
import com.zc.service.ffconfig.FfconfigService;
import com.zc.service.systemfrom.SystemFromService;
import org.apache.commons.collections.map.HashedMap;
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
 * @Description :丰付渠道
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月08日14:20
 */
@Controller
@RequestMapping("pc/view/ffConfig")
public class FfController {

    private Logger logger = Logger.getLogger(FfController.class);

    @Reference
    private FfconfigService ffconfigService;
    @Reference
    private SystemFromService systemFromService;


    /**
     * @return
     * @description: 跳转丰付支付
     * @author: ZhaoJunBiao
     * @date: 2018-04-08 14:35
     * @version: 1.0.0
     */
    @RequestMapping("showToFind")
    public String showToFind() {
        return "ffconfig/ffpay";
    }


    /**
     * @param pageBean
     * @return
     * @description: 丰付支付配置信息分页展示
     * @author: ZhaoJunBiao
     * @date: 2018-04-08 14:37
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object showFfPayConfig(PageBean pageBean) {
        Map<String, Object> map = ffconfigService.getFfConfig(pageBean);
        ;
        return map;
    }

    /**
     * @param ffPayId
     * @return
     * @description: 查看
     * @author: ZhaoJunBiao
     * @date: 2018-04-08 17:42
     * @version: 1.0.0
     */
    @RequestMapping("findFfPay")
    @ResponseBody
    public Object findFfPay(Long ffPayId) {
        Map<String, Object> map = ffconfigService.findConfig(ffPayId);
        return map;
    }


    /**
     * @description:  丰付支付配置修改（添加）
     * @author:  ZhaoJunBiao
     * @date:  2018-04-23 16:08
     * @version: 1.0.0
     * @param ffPayConfig
     * @return
     */
    @RequestMapping(value = "updateOrSaveFfPay", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrSavevFfPayConfig(FFPayConfig ffPayConfig) {
//        logger.info("丰付支付配置添加方法调用");
        Cat.logEvent("FfController","添加修改丰付支付配置入参", Event.SUCCESS,"FFPayConfig="+JSON.toJSON(ffPayConfig));
        Map<String, Object> map = new HashedMap();
        if (ffPayConfig.getId() == null) {
            ffPayConfig.setCreatedTime(new Date());
            ffPayConfig.setUpdateTime(new Date());
            map = ffconfigService.addFfPayConfig(ffPayConfig);
            return map;
        } else {
            map = ffconfigService.updateFfPayConfig(ffPayConfig);
            return map;
        }
    }


}
