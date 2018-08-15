package com.zc.service.impl.cash;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.CodeConstExt;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.paycontext.PayContext;
import com.zc.entity.push.PushTask;
import com.zc.entity.pushtask.PushTaskRecord;
import com.zc.service.business.BusinessService;
import com.zc.service.cash.AbstractCashService;
import com.zc.service.cash.CashService;
import com.zc.service.paymentmessage.PaymentMessageService;
import com.zc.service.pushtask.PushTaskRecordService;
import com.zc.service.pushtask.PushTaskService;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @package : com.zc.service.impl.cash
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日17:54
 */
@Service
@Transactional
public class CashServiceImpl extends AbstractCashService implements CashService {

    private Logger logger = Logger.getLogger(CashServiceImpl.class);

    @Reference
    private PaymentMessageService paymentMessageService;
    @Reference
    private PushTaskService pushTaskService;
    @Reference
    private PushTaskRecordService pushTaskRecordService;
    @Reference
    private BusinessService businessService;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void payBatch(CashReceiveStation crs, Map<String, Object> initialize) {
       Result startPayBatch = null;
        try {
            String userInfo = initialize.get("useInfo") + "";
            if (CodeConstExt.ADMIN.equals(userInfo)) {
               PayContext payContext = paymentMessageService.getPayContextByOrderNum(crs.getMerSeqId());
                PushTask findPayEntity = pushTaskService.findPayEntity(crs.getMerSeqId());
                PushTaskRecord pushTaskRecord = pushTaskRecordService.findPayEntity(crs.getMerSeqId());
                if (payContext == null) {
                    logger.info("根据订单号查询paycontext实体为null，订单号:" + crs.getMerSeqId());
                    return;
                }
                if ("2".equals(payContext.getPayStatus())
                        || ((findPayEntity != null && "2".equals(findPayEntity.getPayStatus() + ""))
                        || (pushTaskRecord != null))) {
                    logger.info("根据订单号查询订单paycontext或者push表状态为成功状态，订单号:" + crs.getMerSeqId());
                    return;
                }
            }
            startPayBatch = startPayBatch(crs, initialize);
        } catch (Exception e) {
            logger.info("<========error==========>发起代付发生异常，异常信息为" + e.getMessage() + ",result返回结果为:" + startPayBatch);
        }
        if (null != startPayBatch) {
            logger.info("财务批量处理代付交易中,订单号:" + crs.getMerSeqId() + ",Result结果:" + startPayBatch.getCode() + ","
                    + startPayBatch.getMsg());
        } else {
            logger.info("财务批量处理代付交易中,订单号:" + crs.getMerSeqId() + ",Result结果为空:" + startPayBatch);
        }
    }


    /**
     * @description: 提现业务
     * @author:  ZhaoJunBiao
     * @date:  2018-04-18 10:47
     * @version: 1.0.0
     * @param initialize
     * @param crs
     * @return
     */
    @Override
    protected Result startBusiness(Map<String, Object> initialize, CashReceiveStation crs) {
        return businessService.startBusiness(initialize, crs);
    }

    @Override
    protected Result startBusinessInfo(Map<String, Object> initialize, CashReceiveStation crs) {
        return businessService.startBusinessInfo(initialize, crs);
    }
}
