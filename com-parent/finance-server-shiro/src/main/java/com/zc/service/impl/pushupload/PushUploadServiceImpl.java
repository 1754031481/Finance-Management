package com.zc.service.impl.pushupload;

import com.alibaba.dubbo.config.annotation.Service;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.DoubleUtils;
import com.zc.common.util.core.ResultUtil;
import com.zc.common.util.result.Result;
import com.zc.dao.pushupload.PushUploadMapper;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.paycontext.PayContext;
import com.zc.entity.push.PushTask;
import com.zc.service.pushupload.PushUploadServcie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 杨文杰
 * @Title: PushUploadServcie
 * @Description: 订单推送
 * @date 2018/4/17
 */
@Service
public class PushUploadServiceImpl implements PushUploadServcie{

    Logger logger = LoggerFactory.getLogger(PushUploadServiceImpl.class);

    public static final String VALIDATE = "\\s*|\t|\r|\n";

    @Autowired
    private PushUploadMapper pushUploadMapper;

    /**
     * 获取推送地址
     * @author : 杨文杰
     * @Creation Date ： 2018/4/17
     * @version 1.0.0
     * @param
     *
     */
    @Override
    public Result getPushUrl(String businessnumber) {
        //Cat.logEvent("PushUploadServiceImpl","获取推送地址入参", Event.SUCCESS,"businessnumber="+businessnumber);
        logger.info("-----------获取推送地址入参，订单号=------------"+businessnumber);
        Result result = new Result();
        try {
            String pushUrl = pushUploadMapper.getPushUrl(businessnumber);
            //Cat.logEvent("PushUploadServiceImpl","获取推送地址出参", Event.SUCCESS,"pushUrl="+pushUrl);
            logger.info("-----------获取推送地址出参------------"+pushUrl);
            result.setData(pushUrl);
            result.setSuccess(true);
            return result;
        }catch (Exception e){
           // Cat.logError("获取推送地址失败",e);
            logger.error("-------------获取推送地址失败-----------"+e);
            return ResultUtil.setResult(false,"获取推送地址失败");
        }
    }

    /**
     * 推送订单文件
     * @author : 杨文杰
     * @Creation Date ： 2018/4/17
     * @version 1.0.0
     * @param
     *
     */
    @Override
    @Async
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void pushXlsInfo(Map<String, String> map, String pushUrl) {

        String payStatus = null;
        String businessnumber = map.get("orderNum").trim();
        String money = map.get("money").trim();
        String status = map.get("status").trim();


        money = replace(money);
        money = clearStartAndEndQuote(money);
        businessnumber = replace(businessnumber);
        businessnumber = clearStartAndEndQuote(businessnumber);
        status = replace(status);
        status = clearStartAndEndQuote(status);
        logger.info("订单号:" + businessnumber + ",状态为:" + status);
        PayContext payContext = new PayContext();
        CashReceiveStation crs = pushUploadMapper.getCashReceiveStationByMerSeqId(businessnumber);

        if (crs == null) {
            //Cat.logEvent("PushUploadServiceImpl","Pay工程回调方法执行中,crs表為null", Event.SUCCESS,"businessnumber="+businessnumber);
            logger.info("Pay工程回调方法执行中,crs表為null,订单号" + businessnumber);
            return;
        }
        if (pushUploadMapper.findPayEntity(businessnumber)>0) {
            //Cat.logEvent("PushUploadServiceImpl","Pay工程回调方法执行中,推送记录已存在", Event.SUCCESS,"businessnumber="+businessnumber);
            logger.info("Pay工程回调方法执行中,订单号" + businessnumber + ",推送记录已存在");
            return;
        }
        if (pushUploadMapper.getPayEntity(businessnumber)>0) {
           // Cat.logEvent("PushUploadServiceImpl","Pay工程回调方法执行中,已推送记录已存在", Event.SUCCESS,"businessnumber="+businessnumber);
            logger.info("Pay工程回调方法执行中,订单号" + businessnumber + ",已推送记录已存在");
            return;
        }
       // Cat.logEvent("PushUploadServiceImpl","第三方返回结果为:" + status, Event.SUCCESS,"businessnumber="+businessnumber);
       logger.info("订单号:" + businessnumber + ",第三方返回结果为:" + status);

        String fromSystem = crs.getFromSystem();

        String thirdPayType = crs.getThirdPayType();

         pushUrl = pushUrl+"";

        double div = DoubleUtils.multiply(Double.valueOf(money), 100);
        Long longValue = Double.valueOf(div).longValue();


        payContext.setMoney(longValue);
        payContext.setCreatedTime(new Date());
        payContext.setOrderNum(businessnumber);
        payContext.setSynReturnContext(map + "");
        payContext.setUpdateTime(new Date());
        payContext.setFromSystem(fromSystem);
        //payContext.setThirdPayType(thirdPayType);
        payContext.setAynUrl(pushUrl);
        payContext.setThirdPayType("线下打款");
        payContext.setCurrentDescribe(fromSystem+"线下打款，订单状态:"+status);
        if ("成功".equals(status)) {
            payStatus = "2";
            payContext.setPayStatus(payStatus);
            try {
                 pushUploadMapper.savePayContext(payContext);
            } catch (Exception e) {
                //Cat.logError("Pay工程回调方法执行中,订单号" + businessnumber + ",保存报文失败",e);
                logger.info("Pay工程回调方法执行中,订单号" + businessnumber + ",保存报文失败:" + e.toString());
                e.printStackTrace();
                return;
            }
            if (payContext.getId() == null) {
                //Cat.logEvent("PushUploadServiceImpl","Pay工程回调方法执行中,订单号" + businessnumber + ",保存报文失败");
                logger.info("Pay工程回调方法执行中,订单号" + businessnumber + ",保存报文失败");
                return;
            }
            try{
                pushUploadMapper.deleteCashReceiveStation(crs.getId());
            }catch (Exception e ){
                //Cat.logError("交易状态为成功，删除crs作业表出现异常,订单号为:" + businessnumber,e);
                logger.info("交易状态为成功，删除crs作业表出现异常,订单号为:" + businessnumber);
                logger.error("异常信息"+e);
                return;
            }

        } else if ("失败".equals(status)) {
            payStatus = "3";
            payContext.setPayStatus(payStatus);
            //Cat.logEvent("PushUploadServiceImpl","Pay工程回调方法执行中,订单号" + businessnumber + ",交易结果:" + status + ",为延迟体现");
            logger.info("Pay工程回调方法执行中,订单号" + businessnumber + ",交易结果:" + status + ",为延迟体现");
            crs.setStatus(3);
            try {
                pushUploadMapper.updateCashReceiveStationStatus(crs);
            }catch (Exception e){
               // Cat.logError("Pay工程回调方法执行中,订单号" + businessnumber + ",交易结果:" + status + ",为延迟体现,修改crs表失败",e);
                logger.info("Pay工程回调方法执行中,订单号" + businessnumber + ",交易结果:" + status + ",为延迟体现,修改crs表失败");
                logger.error("异常信息"+e);
                return;
            }
            try {
                pushUploadMapper.savePayContext(payContext);
            }catch (Exception e){
                //Cat.logError("Pay工程回调方法执行中,订单号" + businessnumber + ",保存报文失败",e);
                logger.info("Pay工程回调方法执行中,订单号" + businessnumber + ",保存报文失败");
                logger.error("异常信息"+e);
                return;
            }
            if (payContext.getId() == null) {
                //Cat.logEvent("PushUploadServiceImpl","Pay工程回调方法执行中,订单号" + businessnumber + ",保存报文失败");
                logger.info("Pay工程回调方法执行中,订单号" + businessnumber + ",保存报文失败");
                return;
            }
            return;
        } else {
            //Cat.logEvent("PushUploadServiceImpl","订单异常不为成功也不为失败，信息为:" + map);
            logger.info("订单异常不为成功也不为失败，信息为:" + map);
            return;
        }

        PushTask pushTask = new PushTask();
        pushTask.setAynUrl(payContext.getAynUrl());
        pushTask.setBatchNo(payContext.getBatchNo());
        pushTask.setCreatedTime(new Date());
        pushTask.setDescibe("订单号:" + businessnumber + ",pay工程作业请求宝付结果:" + status + ",成功情况,新建推送记录");
        pushTask.setMoney(payContext.getMoney().toString());
        pushTask.setOrderNum(payContext.getOrderNum());
        pushTask.setPayStatus(payStatus);
        pushTask.setMoney(longValue.toString());
        pushTask.setPayType("0");
        pushTask.setSystemFromName(payContext.getFromSystem());
        pushTask.setSystemName(payContext.getFromSystem());
        pushTask.setThirdPayType(payContext.getThirdPayType());
        pushTask.setUpdateTime(new Date());
        // rpcInsideService.pushTaskPayInNewTrans(pushTask);
        try {
             pushUploadMapper.savePushTask(pushTask);
        }catch (Exception e){
            logger.error("报文保存失败"+e);
            return;
        }
        //Cat.logEvent("PushUploadServiceImpl","Pay工程回调方法执行中,订单号" + businessnumber + ",交易结果:" + status + ",报文保存结果:" + pushTask + ",推送保存结果:" + pushTask);
        logger.info("Pay工程回调方法执行中,订单号" + businessnumber + ",交易结果:" + status + ",报文保存结果:" + pushTask + ",推送保存结果:" + pushTask);
        if (pushTask.getId() != null) {
           // Cat.logEvent("PushUploadServiceImpl","Pay工程回调方法保存成功,订单号" + businessnumber + ",交易结果:" + status + ",报文保存结果:" + pushTask + ",推送保存结果:" + pushTask
                //    + "回调结束.");
            logger.info("Pay工程回调方法保存成功,订单号" + businessnumber + ",交易结果:" + status + ",报文保存结果:" + pushTask + ",推送保存结果:" + pushTask
                    + "回调结束.");
            return;
        } else {
           // Cat.logEvent("PushUploadServiceImpl","Pay工程回调方法保存失败,订单号" + businessnumber + ",保存pushTask表出现异常");
            logger.info("Pay工程回调方法保存失败,订单号" + businessnumber + ",保存pushTask表出现异常");
            return;
        }
    }

    public String replace(String param) {
        Pattern p = Pattern.compile(VALIDATE);
        Matcher m = p.matcher(param);
        return m.replaceAll("");
    }

    public static String clearStartAndEndQuote(String str) {
        if (str != null && str.length() >= 2) {
            if (str.indexOf("\"") == 0) {
                str = str.substring(1, str.length());
            }// 去掉第一个 "
            if (str.lastIndexOf("\"") == (str.length() - 1)) {
                str = str.substring(0, str.length() - 1); // 去掉最后一个 "
                return str;
            }
        }
        return str;
    }

    /**
     * 获取otp
     * @author : 杨文杰
     * @Creation Date ： 2018/4/23
     * @version 1.0.0
     * @param
     *
     */
    @Override
    public Result getOtpByTel(String tel) {
        //Cat.logEvent("PushUploadServiceImpl","获取用户otp,当前用户手机号为"+tel);
        logger.info("--------获取用户otp,当前用户手机号为----------"+tel);
        Result result = new Result();
        String otp = pushUploadMapper.getOtpByTel(tel);
        result.setSuccess(true);
        result.setData(otp);
        //Cat.logEvent("PushUploadServiceImpl","获取用户otp,返回结果"+otp);
        logger.info("--------获取用户otp,返回结果----------"+otp);
        return result;
    }
}
