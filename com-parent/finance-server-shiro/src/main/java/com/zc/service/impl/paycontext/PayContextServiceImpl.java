package com.zc.service.impl.paycontext;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.paycontext.PayContextMapper;
import com.zc.entity.paycontext.PayContext;
import com.zc.service.paycontext.PayContextService;
import com.zc.vo.PayContextVO;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.impl.paycontext
 * @progect : Finance-Management
 * @Description : 订单查询service接口实现层
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月24日9:37
 */
@Service
@Transactional
public class PayContextServiceImpl  implements PayContextService{

    private Logger logger = Logger.getLogger(PayContextServiceImpl.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private PayContextMapper payContextMapper;

    @Override
    public Map<String, Object> findOrderList(PageBean pageBean,PayContextVO payContext) {
        logger.info("查询订单方法入参"+JSONObject.fromObject(payContext));
        Cat.logEvent("PayContextServiceImpl","",Event.SUCCESS,"异常" + ",查询订单方法入参:" + JSONObject.fromObject(payContext));
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
            if(StringUtils.isNotEmpty(payContext.getPayStatus())){
                payContext1.setPayStatus(payContext.getPayStatus());
            }
            String[] ids = payContextMapper.findSelectId(payContext1);
            PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
            List<Map<String,Object>> syxCashConfigList = payContextMapper.findOrderList(payContext1);
            PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(syxCashConfigList);
            map.put("content",pageInfo.getList());
            map.put("count",pageInfo.getTotal());
            map.put("ids",ids);
            map.put("code",200);
            return map;
        }catch (Exception e){
//            logger.info("订单列表查询异常");
            Cat.logEvent("PayContextServiceImpl","订单列表查询异常");
            e.printStackTrace();
            map.put("code",300);
        }
        return map;
    }

    @Override
    public Result getChannelSel() {
        Result result = new Result();
        List<String> list = payContextMapper.getChannelSel();
        result.setCode(200);
        result.setContent(list);
        return result;
    }

    @Override
    public List<PayContext> getPayContentByIds(String[] ids) {
        //Cat.logEvent("PayContextServiceImpl","获取要下载的订单数据，订单id="+ids);
        logger.info("获取要下载的订单数据，订单id="+ids);
        List<PayContext> list = payContextMapper.getPayContextByIds(ids);
        return list;
    }
}
