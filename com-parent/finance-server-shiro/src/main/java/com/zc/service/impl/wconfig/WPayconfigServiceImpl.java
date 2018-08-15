package com.zc.service.impl.wconfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.wconfig.WPayConfigMapper;
import com.zc.entity.wconfig.WPayConfig;
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
 * @Creation Date ：2018年04月16
 */
@Service
@Transactional
public class WPayconfigServiceImpl implements WPayconfigService {

    private Logger logger = Logger.getLogger(WPayconfigServiceImpl.class);

    @Autowired
    private WPayConfigMapper wPayConfigMapper;

    /**
     * @description:  W支付渠道配置展示
     * @author:  史云顺
     * @date:  2018-04-16
     * @version: 1.0.0
     * @return
     */
    @Override
    public Map<String,Object>  getWConfig(PageBean pageBean) {
        logger.info("进入W支付渠道配置展示，参数：pageBean,["+pageBean.toString()+"]");
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Map<String,Object>> wPayConfigList = wPayConfigMapper.findWPayConfig();
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(wPayConfigList);
        Map<String,Object> map = new HashMap();
        map.put("content",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code","200");
        return map;
    }

    /**
     * @description:  查看配置信息
     * @param WPayId
     * @return map
     */
    @Override
    public Map<String,Object>  findConfig(Long WPayId) {
        logger.info("查看W支付单一配置方法入参,WPayId=["+WPayId+"]");
        List<Map<String,Object>> mapsc = wPayConfigMapper.findConfig(WPayId);
        Map<String,Object> map = new HashMap();
        map.put("content",mapsc);
        map.put("code","200");
        return map;
    }

   @Override
    public Map<String,Object> updateWPayConfig(WPayConfig wPayConfig1) {
        logger.info("修改W支付配置入参:["+ JSONObject.toJSONString(wPayConfig1)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            wPayConfigMapper.updateWPayConfig(wPayConfig1);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("修改W支付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后重试");
        }
        return map;

    }

    @Override
    public Map<String,Object> addWPayConfig(WPayConfig wPayConfig1) {
        logger.info("添加W支付配置入参:["+ JSONObject.toJSONString(wPayConfig1)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            wPayConfigMapper.addWPayConfig(wPayConfig1);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("添加W支付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后再试");
        }
    return map;

    }

}
