package com.zc.service.impl.pushqueryrecord;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.pushqueryrecord.PushQueryRecordMapper;
import com.zc.entity.pushqueryrecord.PushQueryRecord;
import com.zc.service.pushqueryrecord.PushQueryRecordService;
import com.zc.vo.PushQueryRecordVO;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.impl.pushqueryrecord
 * @progect : Finance-Management
 * @Description : 已处理订单查询service接口实现层
 * @Author by :史云顺
 * @Creation Date ：2018年06月05日9:37
 */
@Service
@Transactional
public class PushQueryRecordServiceImpl implements PushQueryRecordService {

    private Logger logger = Logger.getLogger(PushQueryRecordServiceImpl.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private PushQueryRecordMapper pushQueryRecordMapper;

    @Override
    public Map<String, Object> findOrderList(PageBean pageBean,PushQueryRecordVO pushQueryRecord) {
        logger.info("查询已处理订单方法入参");
       // Cat.logEvent("已处理订单查询ServiceImp", "查询已处理订单列表展示入参", Event.SUCCESS, "参数=pageBean"+pageBean.toString()+ JSON.toJSONString(pushQueryRecord));
        Map<String,Object> map=null;
        try {
            map = new HashedMap();
            PushQueryRecord pushQueryRecord1 = new PushQueryRecord();
            if(StringUtils.isNotEmpty(pushQueryRecord.getCreatedTime())) {
                pushQueryRecord1.setCreatedTime(sdf.parse(pushQueryRecord.getCreatedTime() + " 00:00:00"));
            }
        /*    if(StringUtils.isNotEmpty(pushQueryRecord.getUpdateTime())){
                pushQueryRecord1.setUpdateTime(sdf.parse(pushQueryRecord.getUpdateTime() + " 23:59:59"));
            }*/
            if(StringUtils.isNotEmpty(pushQueryRecord.getThirdPayType())){
                pushQueryRecord1.setThirdPayType(pushQueryRecord.getThirdPayType());
            }
            if(StringUtils.isNotEmpty(pushQueryRecord.getOrderNum())){
                pushQueryRecord1.setOrderNum(pushQueryRecord.getOrderNum());
            }
           if(StringUtils.isNotEmpty(pushQueryRecord.getSystemFromName())){
                pushQueryRecord1.setSystemFromName(pushQueryRecord.getSystemFromName());
            }
            String[] ide  =  pushQueryRecordMapper.selectId(pushQueryRecord1);
            PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
            List<Map<String,Object>> syxCashConfigList = pushQueryRecordMapper.findOrderList(pushQueryRecord1);
            PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(syxCashConfigList);
            map.put("content",pageInfo.getList());
            map.put("count",pageInfo.getTotal());
            map.put("ide",ide);
            map.put("code",200);
            //Cat.logEvent("已处理订单查询ServiceImp", "查询已处理订单列表展示出参", Event.SUCCESS, "参数="+map.toString());
            return map;
        }catch (Exception e){
           logger.info("订单列表查询异常");
          // Cat.logError("====订单列表查询异常======",e);
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
        List<String> list = pushQueryRecordMapper.getChannelSel();
      //  Cat.logEvent("已处理订单查询ServiceImp", "获取三方渠道下拉列表", Event.SUCCESS, "参数="+list.toString());
        result.setCode(200);
        result.setContent(list);
        return result;
    }


    /**
     * @return
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 获取订单
     */
    @Override
    public  List<PushQueryRecord> getPushQueryRecord (String[] chk_value){
       // Cat.logEvent("已处理订单查询ServiceImp", "获取需要下载的订单", Event.SUCCESS, "参数="+chk_value);
        return pushQueryRecordMapper.getBatchQuery(chk_value);
    }



}
