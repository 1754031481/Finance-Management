package com.zc.service.impl.transactionsetting;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.CodeConstExt;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.cashreceivestation.CashreceivestationMapper;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.service.cashreceivestationany.CashReceiveStationAnysService;
import com.zc.service.initialize.InitializeService;
import com.zc.service.systemfrom.SystemFromService;
import com.zc.service.transactionsetting.PaymentInitiatedService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @package : com.zc.service.impl.transactionsetting
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月12日10:46
 */
@Service
@Transactional
public class PaymentInitiatedServiceImpl implements PaymentInitiatedService {

    private Logger logger = Logger.getLogger(PaymentInitiatedServiceImpl.class);

    @Autowired
    private CashreceivestationMapper cashreceivestationMapper;
    @Reference
    private SystemFromService systemFromService;
    @Reference
    private CashReceiveStationAnysService cashReceiveStationAnysService;
    @Reference
    private InitializeService initializeService;

    /**
     * @param pageBean
     * @return
     * @description: 业务渠道分页展示
     * @author: ZhaoJunBiao
     * @date: 2018-04-12 14:49
     * @version: 1.0.0
     */
    @Override
    public Map<String, Object> findBusinessChannelsList(PageBean pageBean) {
        Map<String, Object> map = new HashedMap();
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(calendar.getTime());
        date += " 23:59:59";


        List<Map<String, Object>> syxPayConfigList = cashreceivestationMapper.findBusinessChannelsList(date);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(syxPayConfigList);
        map.put("content", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        map.put("code", 200);
        return map;
    }

    @Override
    public Result launchBusiness(String systemName, String name) {
        Result result =  new Result();
        List<CashReceiveStation> list = null;
        List<CashReceiveStation> listAdmin = new ArrayList();
        String useInfo = null;
        if (null == systemName) {
            result.setCode(CodeConstExt.FAIL_CODE);
            result.setMsg("未找到需代付交易的系统");
            return result;
        }else if (null == systemFromService.getSystemFromBySystemFromName(systemName)) {
            result.setCode(CodeConstExt.FAIL_CODE);
            result.setMsg("未找到需代付交易的系统");
            return result;
        }
        if(StringUtils.isBlank(name)){
            result.setCode(CodeConstExt.FAIL_CODE);
            result.setMsg("未输入需代付交易的身份");
            return result;
        }
        if(CodeConstExt.FINACE.equals(name)){
            Integer status = 1;
            list = cashReceiveStationAnysService.getCrsInfosByStatus(systemName,status);
            if (null == list || list.size() < 1) {
                result.setCode(CodeConstExt.FAIL_CODE);
                result.setMsg("没有代付数据");
                return result;
            }
        }else if(CodeConstExt.ADMIN.equals(name)){
            Integer status = 3;
            list = cashReceiveStationAnysService.getCrsInfosByStatus(systemName,status);
            if (null == list || list.size() < 1) {
                result.setCode(CodeConstExt.FAIL_CODE);
                result.setMsg("没有代付数据");
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

        } catch (Exception e) {
            logger.info("财务批量代付任务,发生异常,systemFrom:"+systemName+",描述:"+e.toString());
        }

        result.setCode(CodeConstExt.SUCCESS_CODE);
        result.setMsg("开始代付交易,稍后查看结果.");
        return result;
    }
}
