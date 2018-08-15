package com.zc.service.impl.wconfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.wconfig.WCashConfigMapper;
import com.zc.dao.wconfig.WPayConfigMapper;
import com.zc.entity.wconfig.WCashConfig;
import com.zc.entity.wconfig.WPayConfig;
import com.zc.service.wconfig.WCashconfigService;
import com.zc.service.wconfig.WPayconfigService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.impl.wconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月17
 */
@Service
@Transactional
public class WCashconfigServiceImpl implements WCashconfigService {

    private Logger logger = Logger.getLogger(WCashconfigServiceImpl.class);

    @Autowired
    private WCashConfigMapper wCashConfigMapper;

    /**
     * @description:  W代付渠道配置展示
     * @author:  史云顺
     * @date:  2018-04-17
     * @version: 1.0.0
     * @return
     */
    @Override
    public Map<String,Object>  getWConfig(PageBean pageBean) {
        logger.info("进入W代付渠道配置展示，参数：pageBean,["+pageBean.toString()+"]");
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Map<String,Object>> wCashConfigList = wCashConfigMapper.findWCashConfig();
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(wCashConfigList);
        Map<String,Object> map = new HashMap();
        map.put("content",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code","200");
        return map;
    }

    /**
     * @description:  查看配置信息
     * @param WCashId
     * @return map
     */
   @Override
    public Map<String,Object>  findConfig(Long WCashId) {
        logger.info("查看W代付单一配置方法入参,WCashId=["+WCashId+"]");
        List<Map<String,Object>> mapsc = wCashConfigMapper.findConfig(WCashId);
        Map<String,Object> map = new HashMap();
        map.put("content",mapsc);
        map.put("code","200");
        return map;
    }

   @Override
    public Map<String,Object> updateWCashConfig(WCashConfig wCashConfig1) {
        logger.info("修改W代付配置入参:["+ JSONObject.toJSONString(wCashConfig1)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            wCashConfigMapper.updateWCashConfig(wCashConfig1);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("修改W代付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后重试");
        }
        return map;

    }

    @Override
    public Map<String,Object> addWCashConfig(WCashConfig wCashConfig1) {
        logger.info("添加W代付配置入参:["+ JSONObject.toJSONString(wCashConfig1)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            wCashConfigMapper.addWCashConfig(wCashConfig1);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("添加W代付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后再试");
        }
    return map;

    }

}
