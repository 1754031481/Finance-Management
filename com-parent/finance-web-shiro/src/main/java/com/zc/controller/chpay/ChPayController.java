package com.zc.controller.chpay;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.entity.chconfig.ChPayConfig;
import com.zc.service.chcofig.ChconfigService;
import com.zc.service.systemfrom.SystemFromService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @package : com.zc.controller.chpay
 * @progect : Finance-Management
 * @Description :传化支付渠道
 * @Description : logger切Cat日志监控  更新时间2018年07月09日14:20
 * @Author by :史云顺
 * @Creation Date ：2018年04月12
 */
@Controller
@RequestMapping("configuration/CHZFConfig")
public class ChPayController {

    private Logger logger = Logger.getLogger(ChPayController.class);

    @Reference
    private ChconfigService chconfigService;
    @Reference
    private SystemFromService systemFromService;


    /**
     * @return
     * @description: 跳转传化支付
     * @author: 史云顺
     * @date: 2018-04-12
     * @version: 1.0.0
     */
    @RequestMapping("payListPage")
    public String showToFind() {
        return "chconfig/chpay";
    }


    /**
     * @param pageBean
     * @return
     * @description: 传化支付配置信息分页展示
     * @author: 史云顺
     * @date: 2018-04-12
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object showFfPayConfig(PageBean pageBean) {
       // logger.info("--------------获取传化支付配置数据Conntroller-----------------");
        Cat.logEvent("传化支付配置Controller", "传化支付配置信息分页展示");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Map<String, Object> map = chconfigService.getChConfig(pageBean);
        Cat.logEvent("传化支付配置Controller", "传化支付配置信息分页展示出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }

    /**
     * @param ChPayId
     * @return
     * @description: 查看
     * @author: 史云顺
     * @date: 2018-04-12
     * @version: 1.0.0
     */
    @RequestMapping("findChPay")
    @ResponseBody
    public Object findChPay(Long ChPayId) {
       // logger.info("--------------查看传化支付配置信息Conntroller,id-----------------"+ChPayId);
        Cat.logEvent("传化支付配置Controller", "查看传化支付配置信息入参", Event.SUCCESS, "参数="+ChPayId);
        if(null==ChPayId){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Map<String, Object> map = chconfigService.findConfig(ChPayId);
        Cat.logEvent("传化支付配置Controller", "查看传化支付配置信息出参", Event.SUCCESS, "参数="+map.toString()
        );
        return map;
    }


/**
     * @param chPayConfig
     * @return
     * @description: 传化支付配置修改（添加）
     * @author: 史云顺
     * @date: 2018-04-12
     * @version: 1.0.0
     */

    @RequestMapping(value = "updateOrSaveChPay", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrSavevFfPayConfig(ChPayConfig chPayConfig) {
       // logger.info("--------------获取传化支付配置数据Conntroller-----------------"+ JSON.toJSON(chPayConfig));
        Cat.logEvent("传化支付配置Controller", "获取传化支付配置信息入参", Event.SUCCESS, "参数="+JSON.toJSON(chPayConfig));
        if(StringUtils.isBlank(chPayConfig.getAppid())){
            return ResultUtil.setResult(false,"Appid不能为空！");
        }else  if(StringUtils.isBlank(chPayConfig.getBackurl())){
            return ResultUtil.setResult(false,"回调地址不能为空！");
        }else if(StringUtils.isBlank(chPayConfig.getBusinesstype())){
            return ResultUtil.setResult(false,"业务种类不能为空！");
        }else if(StringUtils.isBlank(chPayConfig.getCashiermode())){
            return ResultUtil.setResult(false,"代付类型不能为空！");
        }else if(StringUtils.isBlank(chPayConfig.getDogSk())){
            return ResultUtil.setResult(false,"网关码不能为空！");
        }else if(StringUtils.isBlank(chPayConfig.getFronturl())){
            return ResultUtil.setResult(false,"跳转地址不能为空！");
        }else if(StringUtils.isBlank(chPayConfig.getKind())){
            return ResultUtil.setResult(false,"消费描述不能为空！");
        }else if(StringUtils.isBlank(chPayConfig.getName())){
            return ResultUtil.setResult(false,"配置名称不能为空！");
        }else if(StringUtils.isBlank(chPayConfig.getRootUrl())){
            return ResultUtil.setResult(false,"请求地址不能为空！");
        }else if(StringUtils.isBlank(chPayConfig.getServiceId())){
            return ResultUtil.setResult(false,"服务id不能为空！");
        }else if(StringUtils.isBlank(chPayConfig.getSubject())){
            return ResultUtil.setResult(false,"描述不能为空！");
        }else if(StringUtils.isBlank(chPayConfig.getTerminal())){
            return ResultUtil.setResult(false,"终端不能为空！");
        }else if(StringUtils.isBlank(chPayConfig.getToaccountnumber())){
            return ResultUtil.setResult(false,"账户号不能为空！");
        }
        if (chPayConfig.getId() == null) {
            chPayConfig.setCreatedTime(new Date());
            chPayConfig.setUpdateTime(new Date());
           // logger.info("--------------------执行传化支付添加方法-------------");
            Cat.logEvent("传化支付配置Controller", "执行传化支付添加方法");
            Map<String, Object>  map = chconfigService.addChPayConfig(chPayConfig);
            Cat.logEvent("传化支付配置Controller", "传化支付添加信息出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        } else {
           // logger.info("--------------------执行传化支付修改方法-------------");
            Cat.logEvent("传化支付配置Controller", "执行传化支付修改方法");
            Map<String, Object>   map = chconfigService.updateChPayConfig(chPayConfig);
            Cat.logEvent("传化支付配置Controller", "传化支付修改信息出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        }

    }





}
