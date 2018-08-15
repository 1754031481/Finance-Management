package com.zc.service.impl.checklog;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.checklog.CheckLogMapper;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.checklog.CheckLog;
import com.zc.entity.thirdpaytype.ThirdPayType;
import com.zc.service.checklog.CheckLogService;
import com.zc.vo.CheckLogVO;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.dubbo.config.annotation.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @package : com.zc.service.impl.checklog
 * @progect : Finance-Management
 * @Description : 复审查询service接口实现层
 * @Author by :史云顺
 * @Creation Date ：2018年06月05日9:37
 */
@Service
@Transactional
public class CheckLogServiceImpl implements CheckLogService {

    private Logger logger = Logger.getLogger(CheckLogServiceImpl.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private CheckLogMapper checkLogMapper;

    @Override
    public Map<String, Object> findOrderList(PageBean pageBean,CheckLogVO checkLog) {
       logger.info("-----------------------查询复审订单方法入参------------------------");
       // Cat.logEvent("查询代付复审订单ServiceImp", "查询代付复审订单列表");
        Map<String,Object> map=null;
        try {
            CheckLog CheckLog1 = new CheckLog();


            if(StringUtils.isNotEmpty(checkLog.getCreatedTime())){
                CheckLog1.setCreatedTime(sdf.parse(checkLog.getCreatedTime() + " 00:00:00"));
            }

            /*if(StringUtils.isNotEmpty(checkLog.getUpdateTime())){
                CheckLog1.setUpdateTime(sdf.parse(checkLog.getUpdateTime() + " 23:59:59"));
            }*/
            if(StringUtils.isNotEmpty(checkLog.getThirdPayType())){
                CheckLog1.setThirdPayType(checkLog.getThirdPayType());
            }
            if(StringUtils.isNotEmpty(checkLog.getOrderNum())){
                CheckLog1.setOrderNum(checkLog.getOrderNum());
            }
            if(StringUtils.isNotEmpty(checkLog.getSystemFromName())){
                CheckLog1.setSystemFromName(checkLog.getSystemFromName());
            }
            String[] ida = checkLogMapper.selectId(CheckLog1);
            PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
            List<Map<String,Object>> syxCashConfigList = checkLogMapper.findOrderList(CheckLog1);
            PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(syxCashConfigList);
            map = new HashedMap();
            map.put("content",pageInfo.getList());
            map.put("count",pageInfo.getTotal());
            map.put("ida",ida);
            map.put("code",200);
            logger.info("-----------------------------复审订单列表查询成功----------------------------------");
          //  Cat.logEvent("查询代付复审订单ServiceImp", "复审订单列表查询成功出参", Event.SUCCESS, "参数="+ map.toString());
            return map;
        }catch (Exception e){
            logger.info("-----------------复审订单列表查询异常----------------");
            //Cat.logError("====复审订单列表查询异常====",e);
            e.printStackTrace();
            map.put("code",300);
        }
        return map;
    }



    /**
     * @return
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 获取复审订单下拉列表
     */
    @Override
    public Result getChannelSel() {
        logger.info("获取三方渠道下拉列表");
        Result result = new Result();
        List<String> list = checkLogMapper.getChannelSel();
      //  Cat.logEvent("查询代付复审订单ServiceImp", "获取三方渠道下拉列表");
        result.setCode(200);
        result.setContent(list);
        return result;
    }
    /**
     * @return
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 添加复审订单列表
     */
    @Override
    public Result getBatchInsert (String[] chk_value){
       // Cat.logEvent("查询代付复审订单ServiceImp", "代付订单复审记录入参", Event.SUCCESS, "参数="+chk_value);
        Result result = new Result();
        try{
            List<CheckLogVO>  checkLogListk = checkLogMapper.getBatchQuery(chk_value);
            Integer a = checkLogMapper.putBatch(checkLogListk);
            result.setCode(a);
           logger.info("----------------添加复审订单列表成功----------------条数"+a+"条");
           // Cat.logEvent("查询代付复审订单ServiceImp", "添加复审订单列表成功出参"+a+"条", Event.SUCCESS, "参数="+ JSONObject.toJSONString(checkLogListk));
        }catch(Exception e){
            e.printStackTrace();
            logger.error("----------------添加复审订单列表异常----------------");
            //Cat.logError("=====添加复审订单列表异常=====",e);
        }
        return result;
    }


    /**
     * @return
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 获取复审订单
     */
    @Override
    public  List<CheckLog> getCheckLog (String[] chk_value){
        return checkLogMapper.getBatchQuery1(chk_value);
    }

    @Override
    public void saveInfo(Map<String, Object> initialize, String thirdPayType, CashReceiveStation crs,Integer type,String status) {
        ThirdPayType thirdPayTypeEntity = (ThirdPayType)initialize.get("thirdPayTypeEntity");
        CheckLog checkLog =new CheckLog();
        checkLog.setCreatedTime(new Date());
        checkLog.setOrderMoney(Long.parseLong(crs.getMoney()+""));
        checkLog.setPayStatus(status);
        checkLog.setSystemFromName(crs.getFromSystem());
        checkLog.setOrderNum(crs.getMerSeqId());
        checkLog.setThirdPayType(thirdPayType);
        checkLog.setType(type);
        checkLog.setConfigId(thirdPayTypeEntity.getPayConfigId());
        checkLogMapper.insert(checkLog);

    }

    @Override
    public CheckLog entityByNumThirdPayType(String orderNum, String thirdPayType) {
        return checkLogMapper.entityByNumThirdPayType(orderNum,thirdPayType);
    }


}
