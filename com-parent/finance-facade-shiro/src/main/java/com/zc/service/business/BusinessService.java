package com.zc.service.business;

import com.zc.common.core.result.Result;
import com.zc.entity.cashreceivestation.CashReceiveStation;

import java.util.Map;

/**
 * @package : com.zc.service.business
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月18日10:42
 */
public interface BusinessService {
    Result startBusinessInfo(Map<String, Object> initialize, CashReceiveStation crs);

    Result startBusiness(Map<String, Object> initialize, CashReceiveStation crs);
}
