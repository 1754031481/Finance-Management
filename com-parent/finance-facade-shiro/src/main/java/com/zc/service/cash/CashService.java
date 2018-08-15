package com.zc.service.cash;

import com.zc.entity.cashreceivestation.CashReceiveStation;

import java.util.Map;

/**
 * @package : com.zc.service.cash
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日17:54
 */
public interface CashService {

    void payBatch(CashReceiveStation crs, Map<String, Object> initialize);
}
