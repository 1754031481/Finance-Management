package com.zc.controller.interfacesupport;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.entity.interfaceentity.InterfaceEntity;
import com.zc.entity.thirdpaytype.ThirdPayType;
import com.zc.service.finance.CommonPayService;
import com.zc.service.interfacesupport.InterFaceSupportService;
import com.zc.service.systemfrom.SystemFromService;
import com.zc.service.thiredpaytype.ThiredPayTypeService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.controller.interfacesupport
 * @progect : Finance-Management
 * @Description : 接口支持模块控制层
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月23日13:48
 */
@Controller
@RequestMapping("pc/view/interfacesupport")
public class InterFaceSupportController {


    private Logger logger = Logger.getLogger(InterFaceSupportController.class);

    @Reference
    private InterFaceSupportService interFaceSupportService;
    @Reference
    private ThiredPayTypeService thiredPayTypeService;
    @Reference
    private CommonPayService commonPayService;
    @Reference
    private SystemFromService systemFromService;
    @Reference

    /**
     * @return
     * @description: 跳转添加接口页面
     * @author: ZhaoJunBiao
     * @date: 2018-04-23 13:52
     * @version: 1.0.0
     */
    @RequestMapping("toAddCashInterFaceSupport")
    public String toAddCashInterFaceSupport() {
        return "interfacesupport/addCashInnterFaceSupport";
    }

    /**
     * @return
     * @description: 跳转添加接口页面
     * @author: ywj
     * @date: 2018-06-05
     * @version: 1.0.0
     */
    @RequestMapping("toAddPayInterFaceSupport")
    public String toAddPayInterFaceSupport() {
        return "interfacesupport/addPayInnterFaceSupport";
    }

    /**
     * @return
     * @description: 跳转添加接口页面
     * @author: ywj
     * @date: 2018-06-05
     * @version: 1.0.0
     */
    @RequestMapping("toAddInterFaceSupport")
    public String toAddInterFaceSupport() {
        return "interfacesupport/addInnterFaceSupport";
    }
    /**
     * @param pageBean
     * @return
     * @description: 渠道信息分页展示
     * @author: ZhaoJunBiao
     * @date: 2018-04-23 14:05
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object findInterFace(PageBean pageBean,String type) {
        Map<String, Object> map = interFaceSupportService.findInterFaceList(pageBean,type);
        return map;
    }

    /**
     * @param interfaceEntity
     * @return
     * @description: 添加接口
     * @author: ZhaoJunBiao
     * @date: 2018-04-23 15:18
     * @version: 1.0.0
     */
    @RequestMapping("updateOrSave")
    @ResponseBody
    public Object updateOrSaveInterFaceEntity(InterfaceEntity interfaceEntity,String isFinal) {
        logger.info("添加接口调用");
        Result result = new Result();
        if (interfaceEntity.getId() == null) {
            result = interFaceSupportService.addInterFace(interfaceEntity,isFinal);
        } else {
            result = interFaceSupportService.updateInterFace(interfaceEntity,isFinal);
        }
        return result;
    }


    /**
     * @description: 跳转到激活接口页面
     * @author:  ZhaoJunBiao
     * @date:  2018-04-23 22:19
     * @version: 1.0.0
     * @return
     */
    @RequestMapping("toActivationInterface")
    public String toActivationInterface(){
        return "interfacesupport/activationInterface";
    }

    /**
     * @description: 激活接口信息分页展示
     * @author:  ZhaoJunBiao
     * @date:  2018-04-24 14:59
     * @version: 1.0.0
     * @param pageBean
     * @return
     */
    @RequestMapping("thirdPayTypeList")
    @ResponseBody
    public  Object findThirdPayType(PageBean pageBean,String type){
        Map<String,Object> map = thiredPayTypeService.findThirdPayType(pageBean,type);
        return  map;
    }


    /**
     * @description: 获取激活接口的下拉列表
     * @author:  ZhaoJunBiao
     * @date:  2018-04-25 12:01
     * @version: 1.0.0
     * @return
     */
    @RequestMapping("findSelectList")
    @ResponseBody
    public Object findSelectList(String interFaceName){
        Map<String,Object> map = new HashedMap();
        if(interFaceName!=null){
         List<Map<String,Object>> tableData =    interFaceSupportService.findPayConfigId(interFaceName);
            map.put("content",tableData);
            map.put("code","200");
            return map;
        }
        //通用配置下拉列表
        Map<String,Object> commonPayList =commonPayService.findCommonPayConfigList();
        //渠道配置下拉列表
     //   Map<String, Object> thirdPayTypeList = thiredPayTypeService.findThirdPayTypeList();
        //配置名称下拉列表框
        Map<String, Object> systemFormList = systemFromService.findSystemList();
        //第三方渠道下拉列表
        Map<String, Object> interFaceList = interFaceSupportService.findInterFaceList2();
        //获取配置id下拉列表

        map.put("commonPayList",commonPayList);
      //  map.put("thirdPayTypeList",thirdPayTypeList);
        map.put("systemFormList",systemFormList);
        map.put("interFaceList",interFaceList);
        map.put("code","200");
        return  map;
    }

    /**
     * @description: 激活接口
     * @author:  ZhaoJunBiao
     * @date:  2018-04-25 15:08
     * @version: 1.0.0
     * @param thirdPayType
     * @return
     */
    @RequestMapping("updateOrSaveThirdPayType")
    @ResponseBody
    public Object updateOrSaveThirdPayType(ThirdPayType thirdPayType) {
        logger.info("添加接口调用");
        Result result = new Result();
        if (thirdPayType.getId() == null) {
            result = thiredPayTypeService.addTirdPayType(thirdPayType);
        } else {
            result = thiredPayTypeService.updateTirdPayType(thirdPayType);
        }
        return result;
    }

    /**
     * @description: 查询final渠道
     * @author:  ywj
     * @date:  2018/6/5
     * @version: 1.0.0
     * @param
     * @return
     */
    @RequestMapping("selectFinalChannel")
    @ResponseBody
    public Object selectFinalChannel(String type) {
        if(!("0".equals(type)||"1".equals(type))){
            return ResultUtil.setResult(false,"参数异常");
        }
        Result result = new Result();
        result = interFaceSupportService.selectFinalChannel(type);
        return result;
    }
}
