package com.zc.controller.wpay;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.entity.wconfig.WPayConfig;
import com.zc.service.systemfrom.SystemFromService;
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
 * @package : com.zc.controller.Wpay
 * @progect : Finance-Management
 * @Description :W支付渠道
 * @Description : logger切Cat日志监控  更新时间2018年07月09日14:20
 * @Author by :史云顺
 * @Creation Date ：2018年04月16
 */
@Controller
@RequestMapping("configuration/WZFConfig")
public class WPayController {

    private Logger logger = Logger.getLogger(WPayController.class);

    @Reference
    private WPayconfigService wPayconfigService;
    @Reference
    private SystemFromService systemFromService;


    /**
     * @return
     * @description: 跳转W支付
     * @author: 史云顺
     * @date: 2018-04-16
     * @version: 1.0.0
     */
    @RequestMapping("payListPage")
    public String showToFind() {
        return "wconfig/wpay";
    }


    /**
     * @param pageBean
     * @return
     * @description: W支付配置信息分页展示
     * @author: 史云顺
     * @date: 2018-04-16
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object showFfPayConfig(PageBean pageBean) {
       // logger.info("--------------获取W支付配置数据Conntroller-----------------");
        Cat.logEvent("W支付配置Controller", "W支付配置信息分页展示出参");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Map<String, Object> map = wPayconfigService.getWConfig(pageBean);
        Cat.logEvent("W支付配置Controller", "W支付配置信息分页展示出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }

    /**
     * @param WPayId
     * @return
     * @description: 查看
     * @author: 史云顺
     * @date: 2018-04-16
     * @version: 1.0.0
     */
    @RequestMapping("findWPay")
    @ResponseBody
    public Object findWPay(Long WPayId) {
      //  logger.info("--------------查看W支付配置信息Conntroller,id-----------------"+WPayId);
        Cat.logEvent("W支付配置Controller", "查看W支付配置信息入参", Event.SUCCESS, "参数="+WPayId);
        if(null==WPayId){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Map<String, Object> map = wPayconfigService.findConfig(WPayId);
        Cat.logEvent("W支付配置Controller", "查看W支付配置信息出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }


/**
     * @param wPayConfig
     * @return
     * @description: W支付配置修改（添加）
     * @author: 史云顺
     * @date: 2018-04-16
     * @version: 1.0.0
     */

    @RequestMapping(value = "updateOrSaveChPay", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrSavevWPayConfig(WPayConfig wPayConfig) {
       // logger.info("--------------获取W支付配置数据Conntroller-----------------"+ JSON.toJSON(wPayConfig));
        Cat.logEvent("W支付配置Controller", "W支付配置修改（添加）入参", Event.SUCCESS, "参数="+JSON.toJSON(wPayConfig));
        if(StringUtils.isBlank(wPayConfig.getMerUrl())){
            return ResultUtil.setResult(false,"商户地址不能为空！");
        }else if(StringUtils.isBlank(wPayConfig.getMerchantNo())){
            return ResultUtil.setResult(false,"商户号不能为空！");
        }else if(StringUtils.isBlank(wPayConfig.getMethod())){
            return ResultUtil.setResult(false,"API不能为空！");
        }else if(StringUtils.isBlank(wPayConfig.getName())){
            return ResultUtil.setResult(false,"配置名称不能为空！");
        }else if(StringUtils.isBlank(wPayConfig.getPageUrl())){
            return ResultUtil.setResult(false,"同步地址不能为空！");
        }else if(StringUtils.isBlank(wPayConfig.getPrivateKey())){
            return ResultUtil.setResult(false,"私钥不能为空！");
        }else if(StringUtils.isBlank(wPayConfig.getPublicKey())){
            return ResultUtil.setResult(false,"公钥不能为空！");
        }else  if(StringUtils.isBlank(wPayConfig.getWGetwayUrlDf())){
            return ResultUtil.setResult(false,"代付请求地址不能为空！");
        }else if(StringUtils.isBlank(wPayConfig.getWGetwayUrlZf())){
            return ResultUtil.setResult(false,"支付请求地址不能为空！");
        }else if(StringUtils.isBlank(wPayConfig.getWGetwayUrlZf())){
            return ResultUtil.setResult(false,"支付请求地址不能为空！");
        }
        if (wPayConfig.getId() == null) {
            wPayConfig.setCreatedTime(new Date());
            wPayConfig.setUpdateTime(new Date());
          //  logger.info("------------------执行W支付添加方法-----------------");
            Cat.logEvent("W支付配置Controller", "执行W支付添加方法");
            Map<String, Object>  map = wPayconfigService.addWPayConfig(wPayConfig);
            Cat.logEvent("W支付配置Controller", "W支付配置添加出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        } else {
            //logger.info("------------------执行W支付修改方法-----------------");
            Cat.logEvent("W支付配置Controller", "执行W支付修改方法");
            Map<String, Object> map = wPayconfigService.updateWPayConfig(wPayConfig);
            Cat.logEvent("W支付配置Controller", "执行W支付修改方法出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        }

    }





}
