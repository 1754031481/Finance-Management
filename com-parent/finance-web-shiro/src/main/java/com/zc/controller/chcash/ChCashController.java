package com.zc.controller.chcash;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.entity.chconfig.CHCashConfig;
import com.zc.service.chcofig.ChCashConfigService;
import com.zc.service.systemfrom.SystemFromService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * @package : com.zc.controller.chpay
 * @progect : Finance-Management
 * @Description :传化代付渠道
 * @Description : logger切Cat日志监控  更新时间2018年07月09日14:20
 * @Author by :史云顺
 * @Creation Date ：2018年04月08日14:20
 */
@Controller
@RequestMapping("configuration/CHDFConfig")
public class ChCashController {

    private Logger logger = Logger.getLogger(ChCashController.class);

    @Reference(version = "1.0.0")
    private ChCashConfigService chCashConfigService;
    @Reference(version = "1.0.0")
    private SystemFromService systemFromService;




    /**
     * @return
     * @description: 跳转传化代付
     * @author: 史云顺
     * @date:
     * @version: 1.0.0
     */
    @RequestMapping("cashListPage")
    public String showToFind() {
        return "chconfig/chcash";
    }


    /**
     * @param pageBean
     * @return
     * @description: 丰付代付配置信息分页展示
     * @author: 史云顺
     * @date: 2018-04-13
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object showFfPayConfig(PageBean pageBean) {
       // logger.info("--------------获取传化代付配置数据Conntroller-----------------");
        Cat.logEvent("传化代付配置Controller", "传化代付配置信息分页展示");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Map<String, Object> map = chCashConfigService.getChCashConfig(pageBean);
        Cat.logEvent("传化代付配置Controller", "传化代付配置信息分页展示出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }

    /**
     * @description: 查看
     * @author: 史云顺
     * @date:  2018-04-13
     * @version: 1.0.0
     * @param chid
     * @return
     */
    @RequestMapping("findChCash")
    @ResponseBody
    public Object findChCash(Long chid) {
       // logger.info("--------------查看传化代付配置信息Conntroller,id-----------------"+chid);
        Cat.logEvent("传化代付配置Controller", "查看传化代付配置信息入参", Event.SUCCESS, "参数="+chid);
        if(null==chid){
            return ResultUtil.setResult(false,"id不能为空！");
        }
        Map<String, Object> map = chCashConfigService.findCashConfig(chid);
        Cat.logEvent("传化代付配置Controller", "查看传化代付配置信息出参", Event.SUCCESS, "参数="+map.toString()
        );
        return map;
    }


    /**
     * @param chCashConfig
     * @return
     * @description: 传化代付配置修改（添加）
     * @author: 史云顺
     * @date:  2018-04-13
     * @version: 1.0.0
     */
    @RequestMapping(value = "updateOrSaveChCash", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrSavevChPayConfig(CHCashConfig chCashConfig) {
      //  logger.info("--------------获取传化代付配置数据Conntroller-----------------"+ JSON.toJSON(chCashConfig));
        Cat.logEvent("传化代付配置Controller", "传化代付配置修改（添加）入参", Event.SUCCESS, "参数="+JSON.toJSON(chCashConfig));
        if(StringUtils.isBlank(chCashConfig.getAppid())){
            return ResultUtil.setResult(false,"Appid不能为空！");
        }else  if(StringUtils.isBlank(chCashConfig.getBackurl())){
            return ResultUtil.setResult(false,"回调地址不能为空！");
        }else if(StringUtils.isBlank(chCashConfig.getBankaccounttype())){
            return ResultUtil.setResult(false,"业务种类不能为空！");
        }else if(StringUtils.isBlank(chCashConfig.getBankcardtype())){
            return ResultUtil.setResult(false,"卡类不能为空！");
        }else if(StringUtils.isBlank(chCashConfig.getDogSk())){
            return ResultUtil.setResult(false,"网关码不能为空！");
        }else if(StringUtils.isBlank(chCashConfig.getFromaccountnumber())){
            return ResultUtil.setResult(false,"账户号不能为空！");
        }else if(StringUtils.isBlank(chCashConfig.getName())){
            return ResultUtil.setResult(false,"配置名称不能为空！");
        }else if(StringUtils.isBlank(chCashConfig.getRootUrl())){
            return ResultUtil.setResult(false,"请求地址不能为空！");
        }else if(StringUtils.isBlank(chCashConfig.getServiceId())){
            return ResultUtil.setResult(false,"服务id不能为空！");
        }else if(StringUtils.isBlank(chCashConfig.getSubject())){
            return ResultUtil.setResult(false,"描述不能为空！");
        }else if(StringUtils.isBlank(chCashConfig.getTerminal())){
            return ResultUtil.setResult(false,"终端不能为空！");
        }
            if (chCashConfig.getId() == null) {
                chCashConfig.setCreatedTime(new Date());
                chCashConfig.setUpdateTime(new Date());
               // logger.info("-------------------执行传化代付添加方法------------------");
                Cat.logEvent("传化代付配置Controller", "执行传化代付添加方法");
                Map<String, Object>  map = chCashConfigService.addChCashConfig(chCashConfig);
                Cat.logEvent("传化代付配置Controller", "传化代付配置添加出参", Event.SUCCESS, "参数="+map.toString());
                return map;
            } else {
               // logger.info("-------------------执行传化代付修改方法------------------");
                Cat.logEvent("传化代付配置Controller", "执行传化代付修改方法");
                Map<String, Object>  map = chCashConfigService.updateChCashConfig(chCashConfig);
                Cat.logEvent("传化代付配置Controller", "传化代付配置修改出参", Event.SUCCESS, "参数="+map.toString());
                return map;
            }

        }





}
