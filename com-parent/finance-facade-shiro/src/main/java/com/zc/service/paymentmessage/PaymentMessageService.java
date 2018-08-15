package com.zc.service.paymentmessage;

import com.zc.entity.paycontext.PayContext;

/**
 * @package : com.zc.service.paymentmessage
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日18:39
 */
public interface PaymentMessageService {

    PayContext getPayContextByOrderNum(String merSeqId);
}
