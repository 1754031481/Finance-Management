package com.zc.controller.zjpay;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.entity.zjconfig.ZjPayConfig;
import com.zc.service.systemfrom.SystemFromService;
import com.zc.service.zjconfig.ZjPayconfigService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @package : com.zc.controller.zjpay
 * @progect : Finance-Management
 * @Description :中金支付渠道
 * @Description : logger切Cat日志监控  更新时间2018年07月09日14:20
 * @Author by :史云顺
 * @Creation Date ：2018年04月17
 */
@Controller
@RequestMapping("configuration/ZJZFConfig")
public class ZjPayController {

    private Logger logger = Logger.getLogger(ZjPayController.class);

    @Reference
    private ZjPayconfigService zjPayconfigService;
    @Reference
    private SystemFromService systemFromService;


    /**
     * @return
     * @description: 跳转中金支付
     * @author: 史云顺
     * @date: 2018-04-17
     * @version: 1.0.0
     */
    @RequestMapping("payListPage")
    public String showToFind() {
        return "zjconfig/zjpay";
    }


    /**
     * @param pageBean
     * @return
     * @description: 中金支付配置信息分页展示
     * @author: 史云顺
     * @date: 2018-04-17
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object showZjPayConfig(PageBean pageBean) {
        Cat.logEvent("中金支付配置Controller", "中金代付配置信息分页展示");
        Map<String, Object> map = zjPayconfigService.getZjConfig(pageBean);
        Cat.logEvent("中金支付配置Controller", "中金代付配置信息分页展示出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }

    /**
     * @param ZjPayId
     * @return
     * @description: 查看
     * @author: 史云顺
     * @date: 2018-04-17
     * @version: 1.0.0
     */
    @RequestMapping("findZjPay")
    @ResponseBody
    public Object findZjPay(Long ZjPayId) {
       // logger.info("--------------查看中金支付配置信息Conntroller,id-----------------"+ZjPayId);
        Cat.logEvent("中金支付配置Controller", "查看中金支付配置信息入参", Event.SUCCESS, "参数="+ZjPayId);
        if(null==ZjPayId){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Map<String, Object> map = zjPayconfigService.findConfig(ZjPayId);
        Cat.logEvent("中金支付配置Controller", "中金支付配置信息展示出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }


/**
     * @param zjPayConfig
     * @return
     * @description: 中金支付配置修改（添加）
     * @author: 史云顺
     * @date: 2018-04-17
     * @version: 1.0.0
     */

    @RequestMapping(value = "updateOrSaveZjPay", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrSaveZjPayConfig(ZjPayConfig zjPayConfig) {
        //logger.info("--------------获取中金支付配置数据Conntroller-----------------"+ JSON.toJSON(zjPayConfig));
        Cat.logEvent("中金支付配置Controller", "中金支付配置信息展示出参", Event.SUCCESS, "参数="+JSON.toJSON(zjPayConfig));
        if(StringUtils.isBlank(zjPayConfig.getAccountType())){
            return ResultUtil.setResult(false,"账户类型不能为空！");
        }else if(StringUtils.isBlank(zjPayConfig.getInstitutionid())){
            return ResultUtil.setResult(false,"机构id不能为空！");
        }else if(StringUtils.isBlank(zjPayConfig.getMethod())){
            return ResultUtil.setResult(false,"API不能为空！");
        }else if(StringUtils.isBlank(zjPayConfig.getName())){
            return ResultUtil.setResult(false,"配置名称不能为空！");
        }else if(StringUtils.isBlank(zjPayConfig.getNotificationurl())){
            return ResultUtil.setResult(false,"支付成功地址不能为空！");
        }else if(StringUtils.isBlank(zjPayConfig.getPageUrl())){
            return ResultUtil.setResult(false,"同步地址不能为空！");
        }else if(StringUtils.isBlank(zjPayConfig.getPrivatePfxMima())){
            return ResultUtil.setResult(false,"私钥密码不能为空！");
        }else if(StringUtils.isBlank(zjPayConfig.getPrivatePfxUrl())){
            return ResultUtil.setResult(false,"私钥地址不能为空！");
        }else if(StringUtils.isBlank(zjPayConfig.getPublicCerUrl())){
            return ResultUtil.setResult(false,"公钥地址不能为空！");
        }else if(StringUtils.isBlank(zjPayConfig.getRsaName())){
            return ResultUtil.setResult(false,"RSA格式不能为空！");
        }else if(StringUtils.isBlank(zjPayConfig.getSettlementFlag())){
            return ResultUtil.setResult(false,"结算方式不能为空！");
        }else if(StringUtils.isBlank(zjPayConfig.getWGetwayUrlDf())){
            return ResultUtil.setResult(false,"代付请求地址不能为空！");
        }else if(StringUtils.isBlank(zjPayConfig.getWGetwayUrlZf())){
            return ResultUtil.setResult(false,"支付请求地址不能为空！");
        }
        if (zjPayConfig.getId() == null) {
            zjPayConfig.setCreatedTime(new Date());
            zjPayConfig.setUpdateTime(new Date());
            logger.info("-------------------执行中金支付添加方法----------------");
            Cat.logEvent("中金支付配置Controller", "执行中金支付添加方法");
            Map<String, Object> map = zjPayconfigService.addZjPayConfig(zjPayConfig);
            Cat.logEvent("中金支付配置Controller", "执行中金支付添加方法出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        } else {
            logger.info("-------------------执行中金支付修改方法----------------");
            Cat.logEvent("中金支付配置Controller", "执行中金支付修改方法");
            Map<String, Object>  map = zjPayconfigService.updateZjPayConfig(zjPayConfig);
            Cat.logEvent("中金支付配置Controller", "执行中金支付修改方法出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        }

    }





}
