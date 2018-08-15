package com.zc.service.impl.todaydispose;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.todaydispose.TodayDisposeContextMapper;
import com.zc.entity.paycontext.PayContext;
import com.zc.service.todaydispose.TodayDisposePayContextService;
import com.zc.vo.PayContextVO;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.impl.todaydispose
 * @progect : Finance-Management
 * @Description : 今日复审查询service接口实现层
 * @Author by :史云顺
 * @Creation Date ：2018年06月05日9:37
 */
@Service
@Transactional
public class TodayDisposePayContextServiceImpl implements TodayDisposePayContextService {

    private Logger logger = Logger.getLogger(TodayDisposePayContextServiceImpl.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private TodayDisposeContextMapper todayDisposePayContextMapper;

    @Override
    public Map<String, Object> findOrderList(PageBean pageBean,PayContextVO payContext) {
         logger.info("查询订单方法入参");
      //  Cat.logEvent("查询处理中订单ServiceImp", "查询处理中订单列表入参", Event.SUCCESS, "参数=pageBean"+ JSONObject.toJSONString(pageBean));
        Map<String,Object> map=null;
        try {
            map = new HashedMap();
            PayContext payContext1 = new PayContext();
            if(StringUtils.isNotEmpty(payContext.getCreatedTime())){
                payContext1.setCreatedTime(sdf.parse(payContext.getCreatedTime() + " 00:00:00"));
            }
            if(StringUtils.isNotEmpty(payContext.getUpdateTime())){
                payContext1.setUpdateTime(sdf.parse(payContext.getUpdateTime() + " 23:59:59"));
            }
            if(StringUtils.isNotEmpty(payContext.getThirdPayType())){
                payContext1.setThirdPayType(payContext.getThirdPayType());
            }
            if(StringUtils.isNotEmpty(payContext.getOrderNum())){
                payContext1.setOrderNum(payContext.getOrderNum());
            }
            if(StringUtils.isNotEmpty(payContext.getFromSystem())){
                payContext1.setFromSystem(payContext.getFromSystem());
            }
            if(StringUtils.isNotEmpty(payContext.getType())){
                payContext1.setType(payContext.getType());
            }
            String[] ide  =  todayDisposePayContextMapper.selectId(payContext1);
            PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
            List<Map<String,Object>> syxCashConfigList = todayDisposePayContextMapper.findOrderList(payContext1);
            PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(syxCashConfigList);
            map.put("content",pageInfo.getList());
            map.put("count",pageInfo.getTotal());
            map.put("ide",ide);
            map.put("code",200);
           // Cat.logEvent("查询处理中订单ServiceImp", "查询处理中订单列表出参", Event.SUCCESS, "参数=pageBean"+map.toString());
            return map;
        }catch (Exception e){
            logger.info("订单列表查询异常");
          //  Cat.logError("===订单列表查询异常===",e);
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
        List<String> list = todayDisposePayContextMapper.getChannelSel();
       // Cat.logEvent("查询处理中订单ServiceImp", "获取三方渠道下拉列表", Event.SUCCESS, "参数="+list.toString());
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
       // Cat.logEvent("查询处理中订单ServiceImp", "添加复审订单入参", Event.SUCCESS, "参数="+chk_value);
        Result result = new Result();
        try{
            List<PayContext>  payContextsListk = todayDisposePayContextMapper.getBatchQuery(chk_value);
            Integer a = todayDisposePayContextMapper.putBatch(payContextsListk);
            result.setCode(a);
           logger.info("----------------添加复审订单列表成功----------------条数"+a+"条");
           // Cat.logEvent("查询处理中订单ServiceImp", "添加复审订单成功===条数"+a+"条", Event.SUCCESS, "参数="+JSONObject.toJSONString(payContextsListk));

        }catch(Exception e){
            e.printStackTrace();
            logger.error("----------------添加复审订单列表异常----------------");
            //Cat.logError("===添加复审订单列表异常===",e);
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
    public  List<PayContext> getPayContext (String[] chk_value){
        //Cat.logEvent("查询处理中订单ServiceImp", "获取处理中复审订单", Event.SUCCESS, "参数="+chk_value);
        return todayDisposePayContextMapper.getBatchQuery(chk_value);
    }



}
