package com.zc.controller.wjcash;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.entity.wjconfig.WjCashConfig;
import com.zc.service.systemfrom.SystemFromService;
import com.zc.service.wjconfig.WjCashconfigService;
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
 * @package : com.zc.controller.wjcash
 * @progect : Finance-Management
 * @Description :维基代付渠道
 * @Description : logger切Cat日志监控  更新时间2018年07月09日14:20
 * @Author by :史云顺
 * @Creation Date ：2018年04月20
 */
@Controller
@RequestMapping("configuration/WJDFConfig")
public class WjCashController {

    private Logger logger = Logger.getLogger(WjCashController.class);

    @Reference
    private WjCashconfigService wjCashconfigService;
    @Reference
    private SystemFromService systemFromService;

/*
    *//**
     * @return
     * @description: 跳转维基代付
     * @author: 史云顺
     * @date: 2018-04-20
     * @version: 1.0.0
     */
    @RequestMapping("cashListPage")
    public String showToFind() {
        return "wjconfig/wjcash";
    }


    /**
     * @param pageBean
     * @return
     * @description: 维基代付配置信息分页展示
     * @author: 史云顺
     * @date: 2018-04-20
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object showWjCashConfig(PageBean pageBean) {
       // logger.info("--------------获取维基代付配置数据Conntroller-----------------");
        Cat.logEvent("维基代付配置Controller", "维基代付配置信息分页展示");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Map<String, Object> map = wjCashconfigService.getWjConfig(pageBean);
        Cat.logEvent("维基代付配置Controller", "维基代付配置信息分页展示出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }

    /**
     * @param WjCashId
     * @return
     * @description: 查看信息
     * @author: 史云顺
     * @date: 2018-04-20
     * @version: 1.0.0
     */
    @RequestMapping("findWjCash")
    @ResponseBody
    public Object findCash(Long WjCashId) {
       // logger.info("--------------查看维基代付配置信息Conntroller,id-----------------"+WjCashId);
        Cat.logEvent("维基代付配置Controller", "查看维基代付配置信息", Event.SUCCESS, "参数="+WjCashId);
        if(null==WjCashId){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Map<String, Object> map = wjCashconfigService.findConfig(WjCashId);
        Cat.logEvent("维基代付配置Controller", "查看维基代付配置信息出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }


/**
     * @param wjCashConfig
     * @return
     * @description: 维基代付配置修改（添加）
     * @author: 史云顺
     * @date: 2018-04-20
     * @version: 1.0.0
     */



    @RequestMapping(value = "updateOrSaveWjCash", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrSavevWjCashConfig(WjCashConfig wjCashConfig) {
      //  logger.info("--------------获取维基代付配置数据Conntroller-----------------"+ JSON.toJSON(wjCashConfig));
        Cat.logEvent("维基代付配置Controller", "获取维基代付配置数据入参", Event.SUCCESS, "参数="+JSON.toJSON(wjCashConfig));
        if(StringUtils.isBlank(wjCashConfig.getName())){
            return ResultUtil.setResult(false,"配置名称不能为空！");
        }else if(StringUtils.isBlank(wjCashConfig.getMethod())){
            return ResultUtil.setResult(false,"接口名称不能为空！");
        }else if(StringUtils.isBlank(wjCashConfig.getMerchantNo())){
            return ResultUtil.setResult(false,"商户编号不能为空！");
        }else if(StringUtils.isBlank(wjCashConfig.getCardType())){
            return ResultUtil.setResult(false,"支付卡类型不能为空！");
        }else if(StringUtils.isBlank(wjCashConfig.getBankAccountType())){
            return ResultUtil.setResult(false,"账户类型不能为空！");
        }else if(StringUtils.isBlank(wjCashConfig.getOrderSubject())){
            return ResultUtil.setResult(false,"订单描述不能为空！");
        }else if(StringUtils.isBlank(wjCashConfig.getNotifyUrl())){
            return ResultUtil.setResult(false,"异步通知地址不能为空！");
        }else if(StringUtils.isBlank(wjCashConfig.getPublicKey())){
            return ResultUtil.setResult(false,"秘钥不能为空！");
        }else if(StringUtils.isBlank(wjCashConfig.getRequestAdressDf())){
            return ResultUtil.setResult(false,"代付请求地址不能为空！");
        }else if(StringUtils.isBlank(wjCashConfig.getQueryAddress())){
            return ResultUtil.setResult(false,"代付查询地址不能为空！");
        }
        if (wjCashConfig.getId() == null) {
            wjCashConfig.setCreatedTime(new Date());
            wjCashConfig.setUpdateTime(new Date());
           // logger.info("-------------------执行维基代付添加方法--------------------");
            Cat.logEvent("维基代付配置Controller", "执行维基代付添加方法");
            Map<String, Object>  map = wjCashconfigService.addWjCashConfig(wjCashConfig);
            Cat.logEvent("维基代付配置Controller", "执行维基代付添加出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        } else {
           // logger.info("-------------------执行维基代付修改方法--------------------");
            Cat.logEvent("维基代付配置Controller", "执行维基代付修改方法");
            Map<String, Object>  map = wjCashconfigService.updateWjCashConfig(wjCashConfig);
            Cat.logEvent("维基代付配置Controller", "执行维基代付修改出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        }

    }







}
