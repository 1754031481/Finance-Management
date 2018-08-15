package com.zc.service.impl.paymentmessage;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.zc.dao.paycontext.PayContextMapper;
import com.zc.entity.paycontext.PayContext;
import com.zc.service.paymentmessage.PaymentMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author:  ZhaoJunBiao
 * @date:  2018-04-13 18:40
 * @version: 1.0.0
 */
@Service
@Transactional
public class PaymentMessageServiceImpl implements PaymentMessageService{

    @Autowired
    private PayContextMapper payContextMapper;


    @Override
    public PayContext getPayContextByOrderNum(String merSeqId) {
        return payContextMapper.getPayContextWhyOrderNum(merSeqId);
    }
}
