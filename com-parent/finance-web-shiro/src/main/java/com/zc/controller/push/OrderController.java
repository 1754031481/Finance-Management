package com.zc.controller.push;


import com.alibaba.dubbo.config.annotation.Reference;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ParamVerificationUtil;
import com.zc.entity.push.PushTask;
import com.zc.entity.push.PushTaskPay;
import com.zc.service.push.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 王楠
 * @version:
 * @Description: 关于推送订单控制层
 **/
@Controller
@RequestMapping("push/order")
public class OrderController {


    @Reference
    private OrderService orderService;

    /**
     * @return 页面路径
     * @throws
     * @author 王楠
     * @version
     * @Description: 跳转关于推送代付订单
     */
    @RequestMapping("/cashListPage")
    public String gotoCashListPage(){

        return "push/cashOrder";
    }

    /**
     * @return 代付订单下拉列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取关于推送代付订单下拉列表
     */
    @RequestMapping("/cashSelect")
    @ResponseBody
    public Object getCashSelect(){
        Map<String,List> map = orderService.getCashSelect();
        return map;
    }

    /**
     * @param page 页面信息
     * @return 代付订单列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取关于推送代付订单列表
     */
    @RequestMapping("/cashList")
    @ResponseBody
    public Object getCashList(@ModelAttribute PageBean page,PushTask pushTask){
        if (StringUtils.isEmpty(pushTask.getSystemName())){
            pushTask.setSystemName(null);
        }
        if (StringUtils.isEmpty(pushTask.getOrderNum())){
            pushTask.setOrderNum(null);
        }
        HashMap<String,Object> hashMap = orderService.getCashList(page,pushTask);
        return hashMap;
    }

    /**
     * @param pushTask 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加关于推送代付订单
     */
    @RequestMapping(value = "/addCash",method = RequestMethod.POST)
    @ResponseBody
    public Result addCash(PushTask pushTask){
        /*if (!ParamVerificationUtil.validate(pushTask)){
            return ResultUtils.returnError("传入参数错误");
        }*/
        if (StringUtils.isEmpty(pushTask.getThirdPayType())){
            pushTask.setThirdPayType(null);
        }
        Result result = orderService.addCash(pushTask);
        return result;
    }

    /**
     * @param pushTask 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改关于推送代付订单
     */
    @RequestMapping(value = "/updateCash",method = RequestMethod.POST)
    @ResponseBody
    public Result updateCash(PushTask pushTask){
        /*if (!ParamVerificationUtil.validate(pushTask)){
            return ResultUtils.returnError("传入参数错误");
        }*/
        if (StringUtils.isEmpty(pushTask.getThirdPayType())){
            pushTask.setThirdPayType(null);
        }
        Result result = orderService.updateCash(pushTask);
        return result;
    }

    /**
     * @return 页面路径
     * @throws
     * @author 王楠
     * @version
     * @Description: 跳转关于推送支付订单
     */
    @RequestMapping("/payListPage")
    public String gotoPayListPage(){

        return "push/payOrder";
    }


    /**
     * @param page 页面信息
     * @return 支付订单列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取关于推送支付订单列表
     */
    @RequestMapping("/payList")
    @ResponseBody
    public Object getPayList(@ModelAttribute PageBean page,PushTaskPay pushTaskPay){
        if (StringUtils.isEmpty(pushTaskPay.getSystemName())){
            pushTaskPay.setSystemName(null);
        }
        if (StringUtils.isEmpty(pushTaskPay.getOrderNum())){
            pushTaskPay.setOrderNum(null);
        }
        HashMap<String,Object> hashMap = orderService.getPayList(page,pushTaskPay);
        return hashMap;
    }

    /**
     * @param pushTaskPay 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加关于推送支付订单
     */
    @RequestMapping(value = "/addPay",method = RequestMethod.POST)
    @ResponseBody
    public Result addPay(PushTaskPay pushTaskPay){
      /*  if (!ParamVerificationUtil.validate(pushTaskPay)){
            return ResultUtils.returnError("传入参数错误");
        }*/
        if (StringUtils.isEmpty(pushTaskPay.getThirdPayType())){
            pushTaskPay.setThirdPayType(null);
        }
        Result result = orderService.addPay(pushTaskPay);
        return result;
    }

    /**
     * @param pushTaskPay 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改关于推送支付订单
     */
    @RequestMapping(value = "/updatePay",method = RequestMethod.POST)
    @ResponseBody
    public Result updatePay(PushTaskPay pushTaskPay){
       /* if (!ParamVerificationUtil.validate(pushTaskPay)){
            return ResultUtils.returnError("传入参数错误");
        }*/
        if (StringUtils.isEmpty(pushTaskPay.getThirdPayType())){
            pushTaskPay.setThirdPayType(null);
        }
        Result result = orderService.updatePay(pushTaskPay);
        return result;
    }

    /**
     * @param id
     * @return Result
     * @throws
     * @author ywj
     * @version
     * @Description: 删除支付推送
     */
    @RequestMapping(value = "/deletePushPay",method = RequestMethod.POST)
    @ResponseBody
    public Result deletePushPay(Long id){
       if(null==id){
           return ResultUtils.returnError("参数为空");
       }
        Result result = orderService.deletePush(id);
        return result;
    }

    /**
     * @param id
     * @return Result
     * @throws
     * @author ywj
     * @version
     * @Description: 删除代付推送
     */
    @RequestMapping(value = "/deletePushCash",method = RequestMethod.POST)
    @ResponseBody
    public Result deletePushCash(Long id){
        if(null==id){
            return ResultUtils.returnError("参数为空");
        }
        Result result = orderService.deletePushCash(id);
        return result;
    }
}
