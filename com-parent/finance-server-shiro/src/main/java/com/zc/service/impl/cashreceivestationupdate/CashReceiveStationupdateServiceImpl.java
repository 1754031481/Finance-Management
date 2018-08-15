package com.zc.service.impl.cashreceivestationupdate;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.cashreceivestation.CashreceivestationMapper;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.logs.LoginOperatingLogs;
import com.zc.service.cashreceivestationupdate.CashReceiveStationUpdateService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;

/**
 * @package : com.zc.service.impl.cashreceivestationupdate
 * @progect : Finance-Management
 * @Description :
 * @Author by :shiyunshun
 * @Creation Date ：2018年06月08日16:11
 */
@Service
@Transactional
public class CashReceiveStationupdateServiceImpl implements CashReceiveStationUpdateService {

    private Logger logger = Logger.getLogger(CashReceiveStationupdateServiceImpl.class);


    @Autowired
    private CashreceivestationMapper cashreceivestationMapper;


    /**
     * @description:  发起代付展示
     * @author:  shiiyunshun
     * @date:  2018-06-08 15:42
     * @version: 1.0.0
     * @return
     */
    @Override
    public Map<String,Object> getCashReceiveStation(PageBean pageBean,Map paramMap) {
        logger.info("进入代付发起订单展示，参数：pageBean,["+pageBean.toString()+"]");
       // Cat.logEvent("代付发起订单修改ServiceImp", "进入代付发起订单展示", Event.SUCCESS, "参数=pageBean"+pageBean.toString());
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Map<String,Object>> cashReceiveStation = cashreceivestationMapper.findCashReceiveStation(paramMap);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(cashReceiveStation);
        Map<String,Object> map = new HashMap();
        map.put("content",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code","200");
        return map;
    }

    @Override
    public Map<String,Object> CashReceiveStationUpdate(CashReceiveStation cashReceiveStation,String ip,String name) {
       logger.info("修改代付发起订单入参:["+ JSONObject.toJSONString(cashReceiveStation)+"]");
       // Cat.logEvent("代付发起订单修改ServiceImp", "修改代付发起订单入参", Event.SUCCESS, "参数="+JSONObject.toJSONString(cashReceiveStation));
        Map<String,Object> map = new HashedMap();
        //查询原有的数据
        CashReceiveStation   cashReceiveStation1 = cashreceivestationMapper.getReceiveStationById(cashReceiveStation.getId());
        try {

             String log1 = "【修改待发起订单】单号："+cashReceiveStation1.getMerSeqId();
             String log2 = "更改为:";
             String log3 = log1;
             LoginOperatingLogs loginOperatingLogs = new LoginOperatingLogs();
               if(!cashReceiveStation1.getMoney().equals(cashReceiveStation.getMoney())){
                    log1 =  log1+"/当前金额:"+cashReceiveStation1.getMoney()+log2+cashReceiveStation.getMoney();
               }
               if(!cashReceiveStation1.getCreatedTime().equals(cashReceiveStation.getCreatedTime())){
                    log1 = log1+ "/当前时间:"+cashReceiveStation1.getCreatedTime()+log2+cashReceiveStation.getCreatedTime();
               }
               if(!cashReceiveStation1.getCardNo().equals(cashReceiveStation.getCardNo())){
                    log1 =  log1+ "/当前银行卡号:"+cashReceiveStation1.getCardNo()+log2+cashReceiveStation.getCardNo();
               }
                if(!cashReceiveStation1.getPersonName().equals(cashReceiveStation.getPersonName())){
                    log1 =  log1+ "/当前收款人名称:"+cashReceiveStation1.getPersonName()+log2+cashReceiveStation.getPersonName();
                }
                if(!cashReceiveStation1.getBankName().equals(cashReceiveStation.getBankName())){
                    log1 =  log1+ "/当前银行名称:"+cashReceiveStation1.getBankName()+log2+cashReceiveStation.getBankName();
                }
               loginOperatingLogs.setOperationLogs(log1);
               loginOperatingLogs.setCreatedIp(ip);
               loginOperatingLogs.setCreateUser(name);
               int i =  cashreceivestationMapper.cashReceiveStationUpdate(cashReceiveStation);
               try{
                    if(!log3.equals(log1)){
                        cashreceivestationMapper.insertLog(loginOperatingLogs);
                    }
                      logger.info("--------添加日志成功-----");
                   //   Cat.logEvent("代付发起订单修改ServiceImp", "添加日志成功", Event.SUCCESS, "参数=");
               }catch (Exception e){
                      logger.info("---------添加日志记录异常------");
                    //  Cat.logError("添加日志记录异常",e);
                      e.printStackTrace();
               }

            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("修改代付发起订单异常");
           // Cat.logError("修改代付发起订单异常",e);
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后重试");
        }
        return map;

    }

    @Override
    public List<Map<String, Object>> getfromSystemList() {
        List<Map<String, Object>> list = cashreceivestationMapper.getfromSystemList();
        return list;
    }

}
