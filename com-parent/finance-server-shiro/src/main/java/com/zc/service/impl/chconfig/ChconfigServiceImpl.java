package com.zc.service.impl.chconfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.chconfig.ChPayConfigMapper;
import com.zc.entity.chconfig.ChPayConfig;
import com.zc.service.chcofig.ChconfigService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.impl.chconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月12
 */
@Service
@Transactional
public class ChconfigServiceImpl implements ChconfigService {

    private Logger logger = Logger.getLogger(ChconfigServiceImpl.class);

    @Autowired
    private ChPayConfigMapper chPayConfigMapper;

    /**
     * @description:  传化支付渠道配置展示
     * @author:  史云顺
     * @date:  2018-04-12
     * @version: 1.0.0
     * @return
     */
    @Override
    public Map<String,Object>  getChConfig(PageBean pageBean) {
        logger.info("进入传化支付渠道配置展示，参数：pageBean,["+pageBean.toString()+"]");
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Map<String,Object>> chPayConfigList = chPayConfigMapper.findChPayConfig();
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(chPayConfigList);
        Map<String,Object> map = new HashMap();
        map.put("content",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code","200");
        return map;
    }

    @Override
    public Map<String,Object>  findConfig(Long ChPayId) {
        logger.info("查看传化支付单一配置方法入参,ffPayId=["+ChPayId+"]");
        List<Map<String,Object>> mapsc = chPayConfigMapper.findConfig(ChPayId);
        Map<String,Object> map = new HashMap();
        map.put("content",mapsc);
        map.put("code","200");
        return map;
    }

    @Override
    public Map<String,Object> updateChPayConfig(ChPayConfig chPayConfig) {
        logger.info("修改传化支付配置入参:["+ JSONObject.toJSONString(chPayConfig)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            chPayConfigMapper.updateChPayConfig(chPayConfig);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("修改传化支付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后重试");
        }
        return map;

    }

    @Override
    public Map<String,Object> addChPayConfig(ChPayConfig ChpayConfig) {
        logger.info("添加丰付支付配置入参:["+ JSONObject.toJSONString(ChpayConfig)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            chPayConfigMapper.addChPayConfig(ChpayConfig);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("添传化支付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后再试");
        }
    return map;

    }

}
