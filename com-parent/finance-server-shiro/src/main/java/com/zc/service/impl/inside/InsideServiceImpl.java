package com.zc.service.impl.inside;

import com.alibaba.dubbo.common.status.Status;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.codingapi.tx.listener.service.InitService;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.CodeConstExt;
import com.zc.dao.cashreceivestation.CashreceivestationMapper;
import com.zc.dao.pushtask.PushTaskMapper;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.paycontext.PayContext;
import com.zc.entity.push.PushTask;
import com.zc.service.cashreceivestationany.CashReceiveStationAnysService;
import com.zc.service.initialize.InitializeService;
import com.zc.service.inside.InsideService;
import com.zc.service.paymentmessage.PaymentMessageService;
import com.zc.service.pushtask.PushTaskService;
import com.zc.service.systemfrom.SystemFromService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.impl.inside
 * @progect : Finance-Management
 * @Description :  重发今日代付  审核失败回调 service实现层
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日15:15
 */
@Service
@Transactional
public class InsideServiceImpl implements InsideService  {

    private Logger logger = Logger.getLogger(InsideServiceImpl.class);

    @Reference
    private SystemFromService systemFromService;
    @Reference
    private CashReceiveStationAnysService cashReceiveStationAnysService;
    @Reference
    private InitializeService initializeService;
    @Reference
    private PaymentMessageService paymentMessageService;
    @Reference
    private PushTaskService pushTaskService;
    @Autowired
    private PushTaskMapper pushTaskMapper;
    @Autowired
    private CashreceivestationMapper cashreceivestationMapper;
    @Override

    /**
     * @description: 代付交易发起
     * @author:  ZhaoJunBiao
     * @date:  2018-04-13 11:20
     * @version: 1.0.0
     */
    public Result launchBusiness(String systemFrom, String name) {
        Result result =  new Result();
        List<CashReceiveStation> list = null;
        List<CashReceiveStation> listAdmin = new ArrayList();
        String useInfo = null;
        if (null == systemFrom) {
            result.setCode(CodeConstExt.FAIL_CODE);
            result.setMsg("未找到该代付交易的系统");
            return result;
        }else if (null == systemFromService.getSystemFromBySystemFromName(systemFrom)) {
            result.setCode(CodeConstExt.FAIL_CODE);
            result.setMsg("未找到该代付交易的系统");
            return result;
        }
        if(StringUtils.isBlank(name)){
            result.setCode(CodeConstExt.FAIL_CODE);
            result.setMsg("未输入该代付交易的身份");
            return result;
        }
        if(CodeConstExt.FINACE.equals(name)){
            list = cashReceiveStationAnysService.getCrsInfosByStatus(systemFrom,1);
            if (null == list || list.size() < 1) {
                result.setCode(CodeConstExt.FAIL_CODE);
                result.setMsg("未找到代付数据");
                return result;
            }
        }else if(CodeConstExt.ADMIN.equals(name)){
            list = cashReceiveStationAnysService.getCrsInfosByStatus(systemFrom,3);
            if (null == list || list.size() < 1) {
                result.setCode(CodeConstExt.FAIL_CODE);
                result.setMsg("未找到代付数据");
                return result;
            }
        }
        CashReceiveStation crs = list.get(0);
        String merSeqId = crs.getMerSeqId();
        String fromSystem = crs.getFromSystem();

        Map<String, Object> initialize = initializeService.sendInitialize(merSeqId,fromSystem,crs.getThirdPayType());


        initialize.put("useInfo", name);
        try {
            cashReceiveStationAnysService.batchCash(list, initialize);
            logger.info("异步方法调用时间1（launchBusiness）:"+System.currentTimeMillis());
        } catch (Exception e) {
            logger.info("财务代付交易发起任务,发生异常,systemFrom项目名为:"+systemFrom+",描述:"+e.toString());
        }

        result.setCode(CodeConstExt.SUCCESS_CODE);
        result.setMsg("开始代付交易,请稍后查看结果.");
        return result;
    }


    /**
     * @description: 审核失败回调
     * @author:  ZhaoJunBiao
     * @date:  2018-04-25 9:33
     * @version: 1.0.0
     * @param systemName
     * @return
     */
    @Override
    public Result sendFailPush(String systemName) {
        logger.info("开始发起失败代付,参数为:"+systemName+"时间为:"+new Date());
        int i=0;
        List<CashReceiveStation> listBySystemBystate = cashReceiveStationAnysService.getCrsInfosByStatus(systemName,3);
        if(listBySystemBystate == null){
            return ResultUtils.returnError("未找到待推送的回调信息");
        }
        for (CashReceiveStation cashReceiveStation : listBySystemBystate) {
            PushTask pushTask = new PushTask();

            PayContext payContext = paymentMessageService.getPayContextByOrderNum(cashReceiveStation.getMerSeqId());
            if(payContext == null){
                logger.info("发起失败代付失败,根据crs.merseqId查询payContext表为null,订单号:"+cashReceiveStation.getMerSeqId());
                continue;
            }
            int delete2 = cashreceivestationMapper.deleteByPrimaryKey(cashReceiveStation.getId());
            logger.info("delete删除后返回的值"+delete2);
           /* if(delete2!=0){
                logger.info("严重Error<===============发起失败代付回调，删除crs表失败,订单号:"+cashReceiveStation.getMerSeqId());
                ++i;
                continue;
            }*/
            pushTask.setAynUrl(payContext.getAynUrl());
            pushTask.setCreatedTime(new Date());
            pushTask.setPayStatus("3");
            pushTask.setOrderNum(cashReceiveStation.getMerSeqId());
            pushTask.setMoney(cashReceiveStation.getMoney()+"");
            pushTask.setThirdPayType(cashReceiveStation.getThirdPayType());
            pushTask.setPayType("1");
            pushTask.setSystemFromName(systemName);
            pushTask.setSystemName(systemName);
            pushTaskMapper.insert(pushTask);
        }
        return ResultUtils.returnSuccess("发起失败代付成功,共计失败："+i+"笔");
    }
}
