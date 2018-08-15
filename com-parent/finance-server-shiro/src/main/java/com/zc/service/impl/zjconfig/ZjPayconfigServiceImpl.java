package com.zc.service.impl.zjconfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.wconfig.WPayConfigMapper;
import com.zc.dao.zjconfig.ZjPayConfigMapper;
import com.zc.entity.wconfig.WPayConfig;
import com.zc.entity.zjconfig.ZjPayConfig;
import com.zc.service.wconfig.WPayconfigService;
import com.zc.service.zjconfig.ZjPayconfigService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.impl.zjconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :史云顺
 * @Creation Date ：2018年04月17
 */
@Service
@Transactional
public class ZjPayconfigServiceImpl implements ZjPayconfigService {

    private Logger logger = Logger.getLogger(ZjPayconfigServiceImpl.class);

    @Autowired
    private ZjPayConfigMapper zjPayConfigMapper;

    /**
     * @description:  中金支付渠道配置展示
     * @author:  史云顺
     * @date:  2018-04-17
     * @version: 1.0.0
     * @return
     */
    @Override
    public Map<String,Object>  getZjConfig(PageBean pageBean) {
        logger.info("进入中金支付渠道配置展示，参数：pageBean,["+pageBean.toString()+"]");
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Map<String,Object>> ZjPayConfigList = zjPayConfigMapper.findZjPayConfig();
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(ZjPayConfigList);
        Map<String,Object> map = new HashMap();
        map.put("content",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code","200");
        return map;
    }

    /**
     * @description:  查看配置信息
     * @param ZjPayId
     * @return map
     */
    @Override
    public Map<String,Object>  findConfig(Long ZjPayId) {
        logger.info("查看中金支付单一配置方法入参,ZjPayId=["+ZjPayId+"]");
        List<Map<String,Object>> mapsc = zjPayConfigMapper.findConfig(ZjPayId);
        Map<String,Object> map = new HashMap();
        map.put("content",mapsc);
        map.put("code","200");
        return map;
    }

   @Override
    public Map<String,Object> updateZjPayConfig(ZjPayConfig zjPayConfig1) {
        logger.info("修改中金支付配置入参:["+ JSONObject.toJSONString(zjPayConfig1)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            zjPayConfigMapper.updateZjPayConfig(zjPayConfig1);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("修改中金支付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后重试");
        }
        return map;

    }

    @Override
    public Map<String,Object> addZjPayConfig(ZjPayConfig zjPayConfig1) {
        logger.info("添加中金支付配置入参:["+ JSONObject.toJSONString(zjPayConfig1)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            zjPayConfigMapper.addZjPayConfig(zjPayConfig1);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("添加中金支付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后再试");
        }
    return map;

    }

}
