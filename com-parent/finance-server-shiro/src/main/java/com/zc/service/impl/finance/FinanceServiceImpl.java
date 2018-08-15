package com.zc.service.impl.finance;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.CodeConstExt;
import com.zc.common.core.utils.DoubleUtils;
import com.zc.dao.finance.finance.FinanceMapper;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.finance.Finance;
import com.zc.service.cashreceivestationany.CashReceiveStationAnysService;
import com.zc.service.finance.FinanceService;
import com.zc.service.transactionsetting.PaymentInitiatedService;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @package : com.zc.service.impl.finance
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日11:35
 */
@Service
@Transactional
public class FinanceServiceImpl implements FinanceService {

    private Logger logger = Logger.getLogger(FinanceServiceImpl.class);

    @Autowired
    private CashReceiveStationAnysService cashReceiveStationAnysService;
    @Autowired
    private FinanceMapper financeMapper;
    @Autowired
    private PaymentInitiatedService paymentInitiatedService;


    @Override
    public Result findToDayTakeCount(String systemFromName) {
        Result result = new Result();
        result.setCode(0);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("systemName", systemFromName);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        date += " 00:00:00";
        param.put("dates", date);
        Integer count = financeMapper.findToDayTakeCount(param);
        if (count == null) {

        } else if (count != 0) {
            result.setCode(1);
            result.setMsg("该项目今天已经向第三方发起代付请求，不能重新发起代付！");
            return result;
        }
        return result;
    }

    @Override
    public void insertFinance(Finance finance) {
        finance.setUpdateTime(new Date());
        finance.setCreatedTime(new Date());
        financeMapper.insert(finance);
    }



    @Override
    public Result rechargeToDayPayment(String systemName) {
        Result launchBusiness = paymentInitiatedService.launchBusiness(systemName, CodeConstExt.ADMIN);
        return launchBusiness;
    }

}
