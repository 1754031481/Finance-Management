package com.zc.service.transactionsetting;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;

import java.util.Map;

/**
 * @package : com.zc.service.transactionsetting
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月12日10:46
 */
public interface PaymentInitiatedService {
    Map<String, Object> findBusinessChannelsList(PageBean pageBean);

    Result launchBusiness(String systemName, String admin);
}
