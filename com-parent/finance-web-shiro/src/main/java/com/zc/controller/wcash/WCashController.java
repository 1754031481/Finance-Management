package com.zc.controller.wcash;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.entity.wconfig.WCashConfig;
import com.zc.entity.wconfig.WPayConfig;
import com.zc.service.systemfrom.SystemFromService;
import com.zc.service.wconfig.WCashconfigService;
import com.zc.service.wconfig.WPayconfigService;
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
import java.util.Objects;

/**
 * @package : com.zc.controller.Wcash
 * @progect : Finance-Management
 * @Description :W代付渠道
 * @Description : logger切Cat日志监控  更新时间2018年07月09日14:20
 * @Author by :史云顺
 * @Creation Date ：2018年04月17
 */
@Controller
@RequestMapping("configuration/WDFConfig")
public class WCashController {

    private Logger logger = Logger.getLogger(WCashController.class);

    @Reference
    private WCashconfigService wCashconfigService;
    @Reference
    private SystemFromService systemFromService;

/*
    *//**
     * @return
     * @description: 跳转W代付
     * @author: 史云顺
     * @date: 2018-04-16
     * @version: 1.0.0
     */
    @RequestMapping("cashListPage")
    public String showToFind() {
        return "wconfig/wcash";
    }


    /**
     * @param pageBean
     * @return
     * @description: W代付配置信息分页展示
     * @author: 史云顺
     * @date: 2018-04-17
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object showWPayConfig(PageBean pageBean) {
      //  logger.info("--------------获取W代付配置数据Conntroller-----------------");
        Cat.logEvent("W代付配置Controller", "W代付配置信息分页展示");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Map<String, Object> map = wCashconfigService.getWConfig(pageBean);
        Cat.logEvent("W代付配置Controller", "W代付配置信息分页展示出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }

    /**
     * @param WCashId
     * @return
     * @description: 查看信息
     * @author: 史云顺
     * @date: 2018-04-17
     * @version: 1.0.0
     */
    @RequestMapping("findWCash")
    @ResponseBody
    public Object findCash(Long WCashId) {
       // logger.info("--------------查看W代付配置信息Conntroller,id-----------------"+WCashId);
        Cat.logEvent("W代付配置Controller", "查看W代付配置信息入参", Event.SUCCESS, "参数="+WCashId);
        if(null==WCashId){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Map<String, Object> map = wCashconfigService.findConfig(WCashId);
        Cat.logEvent("W代付配置Controller", "查看W代付配置信息出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }


/**
     * @param wCashConfig
     * @return
     * @description: W代付配置修改（添加）
     * @author: 史云顺
     * @date: 2018-04-17
     * @version: 1.0.0
     */

    @RequestMapping(value = "updateOrSaveWCash", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrSavevWCashConfig(WCashConfig wCashConfig) {
       // logger.info("--------------获取W代付配置数据Conntroller-----------------"+ JSON.toJSON(wCashConfig));
        Cat.logEvent("W代付配置Controller", "W代付配置修改（添加）配置信息入参", Event.SUCCESS, "参数="+JSON.toJSON(wCashConfig));
        if(StringUtils.isBlank(wCashConfig.getMerUrl())){
            return ResultUtil.setResult(false,"商户地址不能为空！");
        }else if(StringUtils.isBlank(wCashConfig.getMerchantNo())){
            return ResultUtil.setResult(false,"商户号不能为空！");
        }else if(StringUtils.isBlank(wCashConfig.getMethod())){
            return ResultUtil.setResult(false,"API不能为空！");
        }else if(StringUtils.isBlank(wCashConfig.getName())){
            return ResultUtil.setResult(false,"配置名称不能为空！");
        }else if(StringUtils.isBlank(wCashConfig.getPageUrl())){
            return ResultUtil.setResult(false,"同步地址不能为空！");
        }else if(StringUtils.isBlank(wCashConfig.getPrivateKey())){
            return ResultUtil.setResult(false,"私钥不能为空！");
        }else if(StringUtils.isBlank(wCashConfig.getPublicKey())){
            return ResultUtil.setResult(false,"公钥不能为空！");
        }else  if(StringUtils.isBlank(wCashConfig.getWGetwayUrlDf())){
            return ResultUtil.setResult(false,"代付请求地址不能为空！");
        }else if(StringUtils.isBlank(wCashConfig.getWGetwayUrlZf())){
            return ResultUtil.setResult(false,"支付请求地址不能为空！");
        }
        if (wCashConfig.getId() == null) {
            wCashConfig.setCreatedTime(new Date());
            wCashConfig.setUpdateTime(new Date());
            logger.info("------------------执行W代付添加方法--------------------");
            Cat.logEvent("W代付配置Controller", "执行W代付添加方法");
            Map<String, Object>  map = wCashconfigService.addWCashConfig(wCashConfig);
            Cat.logEvent("W代付配置Controller", "W代付添加配置信息出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        } else {
            logger.info("------------------执行W代付修改方法--------------------");
            Cat.logEvent("W代付配置Controller", "执行W代付修改方法");
            Map<String, Object> map = wCashconfigService.updateWCashConfig(wCashConfig);
            Cat.logEvent("W代付配置Controller", "W代付修改配置信息出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        }

    }





}
