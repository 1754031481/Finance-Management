package com.zc.controller.zjcash;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.entity.zjconfig.ZjCashConfig;
import com.zc.service.systemfrom.SystemFromService;
import com.zc.service.zjconfig.ZjCashconfigService;
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
 * @package : com.zc.controller.zjcash
 * @progect : Finance-Management
 * @Description :中金代付渠道
 * @Description : logger切Cat日志监控  更新时间2018年07月09日14:20
 * @Author by :史云顺
 * @Creation Date ：2018年04月18
 */
@Controller
@RequestMapping("configuration/ZJDFConfig")
public class ZjCashController {

    private Logger logger = Logger.getLogger(ZjCashController.class);

    @Reference
    private ZjCashconfigService zjCashconfigService;
    @Reference
    private SystemFromService systemFromService;

/*
    *//**
     * @return
     * @description: 跳转中金代付
     * @author: 史云顺
     * @date: 2018-04-18
     * @version: 1.0.0
     */
    @RequestMapping("cashListPage")
    public String showToFind() {
        return "zjconfig/zjcash";
    }


    /**
     * @param pageBean
     * @return
     * @description: 中金代付配置信息分页展示
     * @author: 史云顺
     * @date: 2018-04-18
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object showWPayConfig(PageBean pageBean) {
       // logger.info("--------------获取中金代付配置数据Conntroller-----------------");
        Cat.logEvent("中金代付配置Controller", "中金代付配置信息分页展示出参");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Map<String, Object> map = zjCashconfigService.getZjConfig(pageBean);
        Cat.logEvent("中金代付配置Controller", "中金代付配置信息分页展示出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }

    /**
     * @param ZjCashId
     * @return
     * @description: 查看信息
     * @author: 史云顺
     * @date: 2018-04-17
     * @version: 1.0.0
     */
    @RequestMapping("findZjCash")
    @ResponseBody
    public Object findCash(Long ZjCashId) {
        //logger.info("--------------查看中金代付配置信息Conntroller,id-----------------"+ZjCashId);
        Cat.logEvent("中金代付配置Controller", "查看中金代付配置信息如入参", Event.SUCCESS, "参数="+ZjCashId);
        if(null==ZjCashId){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Map<String, Object> map = zjCashconfigService.findConfig(ZjCashId);
        Cat.logEvent("中金代付配置Controller", "查看中金代付配置信息如出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }


/**
     * @param request
     * @return
     * @description: 中金代付配置修改（添加）
     * @author: 史云顺
     * @date: 2018-04-18
     * @version: 1.0.0
     */


    @RequestMapping(value = "updateOrSaveZjCash", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrSavevWCashConfig(HttpServletRequest request,ZjCashConfig zjCashConfig) {
       // logger.info("--------------获取中金代付配置数据Conntroller-----------------"+JSON.toJSON(zjCashConfig));
        Cat.logEvent("中金代付配置Controller", "中金代付配置修改（添加）入参", Event.SUCCESS, "参数="+JSON.toJSON(zjCashConfig));
        if(StringUtils.isBlank(zjCashConfig.getAccountType())){
            return ResultUtil.setResult(false,"账户类型不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getInstitutionid())){
            return ResultUtil.setResult(false,"机构id不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getMethod())){
            return ResultUtil.setResult(false,"API不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getName())){
            return ResultUtil.setResult(false,"配置名称不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getNotificationurl())){
            return ResultUtil.setResult(false,"支付成功地址不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getPageUrl())){
            return ResultUtil.setResult(false,"同步地址不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getPrivatePfxMima())){
            return ResultUtil.setResult(false,"私钥密码不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getPrivatePfxUrl())){
            return ResultUtil.setResult(false,"私钥地址不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getPublicCerUrl())){
            return ResultUtil.setResult(false,"公钥地址不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getRsaName())){
            return ResultUtil.setResult(false,"RSA格式不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getSettlementFlag())){
            return ResultUtil.setResult(false,"结算方式不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getWGetwayUrlDf())){
            return ResultUtil.setResult(false,"代付请求地址不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getWGetwayUrlZf())){
            return ResultUtil.setResult(false,"支付请求地址不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getPaymentAccountName())){
            return ResultUtil.setResult(false,"支付机构名称不能为空！");
        }else if(StringUtils.isBlank(zjCashConfig.getPaymentAccountNumber())){
            return ResultUtil.setResult(false,"支付机构号码不能为空！");
        }

        if (zjCashConfig.getId() == null) {
            zjCashConfig.setCreatedTime(new Date());
            zjCashConfig.setUpdateTime(new Date());
          //  logger.info("--------------执行添加中金代付方法-----------------");
            Cat.logEvent("中金代付配置Controller", "执行添加中金代付方法");
            Map<String, Object>   map = zjCashconfigService.addZjCashConfig(zjCashConfig);
            Cat.logEvent("中金代付配置Controller", "中金代付配置添加出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        } else {
          //  logger.info("--------------执行修改中金代付方法-----------------");
            Cat.logEvent("中金代付配置Controller", "执行修改中金代付方法");
            Map<String, Object> map= zjCashconfigService.updateZjCashConfig(zjCashConfig);
            Cat.logEvent("中金代付配置Controller", "中金代付配置修改出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        }

    }






}
