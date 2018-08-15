package com.zc.service.impl.wjconfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.wjconfig.WjPayConfigMapper;
import com.zc.entity.wjconfig.WjPayConfig;
import com.zc.service.wjconfig.WjPayconfigService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.impl.wjconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月18
 */
@Service
@Transactional
public class WjPayconfigServiceImpl implements WjPayconfigService {

    private Logger logger = Logger.getLogger(WjPayconfigServiceImpl.class);

    @Autowired
    private WjPayConfigMapper wjPayConfigMapper;

    /**
     * @description:  维基支付渠道配置展示
     * @author:  史云顺
     * @date:  2018-04-18
     * @version: 1.0.0
     * @return
     */
    @Override
    public Map<String,Object>  getWjConfig(PageBean pageBean) {
        logger.info("进入维基支付渠道配置展示，参数：pageBean,["+pageBean.toString()+"]");
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Map<String,Object>> WjPayConfigList = wjPayConfigMapper.findWjPayConfig();
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(WjPayConfigList);
        Map<String,Object> map = new HashMap();
        map.put("content",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code","200");
        return map;
    }

    /**
     * @description:  查看配置信息
     * @param WjPayId
     * @return map
     */
    @Override
    public Map<String,Object>  findConfig(Long WjPayId) {
        logger.info("查看维基支付单一配置方法入参,WjPayId=["+WjPayId+"]");
        List<Map<String,Object>> mapsc = wjPayConfigMapper.findConfig(WjPayId);
        Map<String,Object> map = new HashMap();
        map.put("content",mapsc);
        map.put("code","200");
        return map;
    }
    /**
     * @description: 修改维基支付配置
     * @param wjPayConfig1
     * @return map
     */
  @Override
    public Map<String,Object> updateWjPayConfig(WjPayConfig wjPayConfig1) {
        logger.info("修改维基支付配置入参:["+ JSONObject.toJSONString(wjPayConfig1)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            wjPayConfigMapper.updateWjPayConfig(wjPayConfig1);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("修改维基支付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后重试");
        }
        return map;

    }
    /**
     * @description:添加维基支付配置
     * @param wjPayConfig1
     * @return map
     */
    @Override
    public Map<String,Object> addWjPayConfig(WjPayConfig wjPayConfig1) {
        logger.info("添加维基支付配置入参:["+ JSONObject.toJSONString(wjPayConfig1)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            wjPayConfigMapper.addWjPayConfig(wjPayConfig1);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("添加维基支付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后再试");
        }
    return map;

    }

}
