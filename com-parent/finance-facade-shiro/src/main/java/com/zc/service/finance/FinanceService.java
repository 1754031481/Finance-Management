package com.zc.service.finance;

import com.zc.common.core.result.Result;
import com.zc.entity.finance.Finance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @package : com.zc.service.finance
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日10:54
 */
public interface  FinanceService {

    Result findToDayTakeCount(String systemFromName);

    void insertFinance(Finance finance);


    Result rechargeToDayPayment(String systemName);
}
