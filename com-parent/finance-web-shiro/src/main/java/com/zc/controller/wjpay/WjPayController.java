package com.zc.controller.wjpay;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.entity.wjconfig.WjPayConfig;
import com.zc.service.systemfrom.SystemFromService;
import com.zc.service.wjconfig.WjPayconfigService;
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
 * @package : com.zc.controller.wjpay
 * @progect : Finance-Management
 * @Description :维基支付渠道
 * @Description : logger切Cat日志监控  更新时间2018年07月09日14:20
 * @Author by :史云顺
 * @Creation Date ：2018年04月18
 */
@Controller
@RequestMapping("configuration/WJZFConfig")
public class WjPayController {

    private Logger logger = Logger.getLogger(WjPayController.class);

    @Reference
    private WjPayconfigService wjPayconfigService;
    @Reference
    private SystemFromService systemFromService;


    /**
     * @return
     * @description: 跳转维基支付
     * @author: 史云顺
     * @date: 2018-04-18
     * @version: 1.0.0
     */
    @RequestMapping("payListPage")
    public String showToFind() {
        return "wjconfig/wjpay";
    }


    /**
     * @param pageBean
     * @return
     * @description: 维基支付配置信息分页展示
     * @author: 史云顺
     * @date: 2018-04-18
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object showWjPayConfig(PageBean pageBean) {
      //  logger.info("--------------获取维基支付配置数据Conntroller-----------------");
        Cat.logEvent("维基支付配置Controller", "维基支付配置信息分页展示");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Map<String, Object> map = wjPayconfigService.getWjConfig(pageBean);
        Cat.logEvent("维基支付配置Controller", "维基支付配置信息分页展示出参", Event.SUCCESS, "参数="+map.toString());

        return map;
    }

    /**
     * @param WjPayId
     * @return
     * @description: 查看维基配置信息
     * @author: 史云顺
     * @date: 2018-04-18
     * @version: 1.0.0
     */
    @RequestMapping("findWjPay")
    @ResponseBody
    public Object findZjPay(Long WjPayId) {
       // logger.info("--------------查看维基代付配置信息Conntroller,id-----------------"+WjPayId);
        Cat.logEvent("维基支付配置Controller", "查询维基支付配置信息", Event.SUCCESS, "参数="+WjPayId);
        if(null==WjPayId){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Map<String, Object> map = wjPayconfigService.findConfig(WjPayId);
        Cat.logEvent("维基支付配置Controller", "查询维基支付配置信息出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }


/**
     * @param wjPayConfig
     * @return
     * @description: 维基支付配置修改（添加）
     * @author: 史云顺
     * @date: 2018-04-18
     * @version: 1.0.0
     */


    @RequestMapping(value = "updateOrSaveWjPay", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrSaveWjPayConfig(WjPayConfig wjPayConfig) {
       // logger.info("--------------获取维基支付配置数据Conntroller-----------------"+ JSON.toJSON(wjPayConfig));
        Cat.logEvent("维基支付配置Controller", "获取维基支付配置数据入参", Event.SUCCESS, "参数="+ JSON.toJSON(wjPayConfig));
        if(StringUtils.isBlank(wjPayConfig.getName())){
            return ResultUtil.setResult(false,"配置名称不能为空！");
        }else  if(StringUtils.isBlank(wjPayConfig.getMerchantNo())){
            return ResultUtil.setResult(false,"商户编号不能为空！");
        }else if(StringUtils.isBlank(wjPayConfig.getCardType())){
            return ResultUtil.setResult(false,"支付卡类型不能为空！");
        }else if(StringUtils.isBlank(wjPayConfig.getOrderSubject())){
            return ResultUtil.setResult(false,"订单描述不能为空！");
        }else if(StringUtils.isBlank(wjPayConfig.getNotifyUrl())){
            return ResultUtil.setResult(false,"异步通知地址不能为空！");
        }else if(StringUtils.isBlank(wjPayConfig.getReturnUrl())){
            return ResultUtil.setResult(false,"页面回调地址不能为空！");
        }else if(StringUtils.isBlank(wjPayConfig.getPublicKey())){
            return ResultUtil.setResult(false,"商户公钥不能为空！");
        }else if(StringUtils.isBlank(wjPayConfig.getRequestAddress())){
            return ResultUtil.setResult(false,"请求地址不能为空！");
        }else if(StringUtils.isBlank(wjPayConfig.getQueryAddress())){
            return ResultUtil.setResult(false,"订单查询地址不能为空！");
        }
        if (wjPayConfig.getId() == null) {
            wjPayConfig.setCreatedTime(new Date());
            wjPayConfig.setUpdateTime(new Date());
            //logger.info("-------------------执行维基支付添加方法----------------");
            Cat.logEvent("维基支付配置Controller", "执行维基支付添加方法");
            Map<String, Object>  map = wjPayconfigService.addWjPayConfig(wjPayConfig);
            Cat.logEvent("维基支付配置Controller", "执行维基支付添加出参", Event.SUCCESS, "参数="+ map.toString());
            return map;
        } else {
            logger.info("-------------------执行维基支付修改方法----------------");
            Cat.logEvent("维基支付配置Controller", "执行维基支付修改方法");
            Map<String, Object>  map = wjPayconfigService.updateWjPayConfig(wjPayConfig);
            Cat.logEvent("维基支付配置Controller", "执行维基支付修改出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        }

    }






}
