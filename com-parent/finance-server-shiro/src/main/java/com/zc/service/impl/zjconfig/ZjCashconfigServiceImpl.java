package com.zc.service.impl.zjconfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.zjconfig.ZjCashConfigMapper;
import com.zc.entity.zjconfig.ZjCashConfig;
import com.zc.service.zjconfig.ZjCashconfigService;
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
 * @Creation Date ：2018年04月18
 */
@Service
@Transactional
public class ZjCashconfigServiceImpl implements ZjCashconfigService {

    private Logger logger = Logger.getLogger(ZjCashconfigServiceImpl.class);

    @Autowired
    private ZjCashConfigMapper zjCashConfigMapper;

    /**
     * @description:  中金代付渠道配置展示
     * @author:  史云顺
     * @date:  2018-04-18
     * @version: 1.0.0
     * @return
     */
    @Override
    public Map<String,Object>  getZjConfig(PageBean pageBean) {
        logger.info("进入中金代付渠道配置展示，参数：pageBean,["+pageBean.toString()+"]");
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Map<String,Object>> zjCashConfigList = zjCashConfigMapper.findZjCashConfig();
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(zjCashConfigList);
        Map<String,Object> map = new HashMap();
        map.put("content",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code","200");
        return map;
    }

    /**
     * @description:  查看配置信息
     * @param ZjCashId
     * @return map
     */
   @Override
    public Map<String,Object>  findConfig(Long ZjCashId) {
        logger.info("查看中金代付单一配置方法入参,ZjCashId=["+ZjCashId+"]");
        List<Map<String,Object>> mapsc = zjCashConfigMapper.findConfig(ZjCashId);
        Map<String,Object> map = new HashMap();
        map.put("content",mapsc);
        map.put("code","200");
        return map;
    }

   @Override
    public Map<String,Object> updateZjCashConfig(ZjCashConfig zjCashConfig1) {
        logger.info("修改中金代付配置入参:["+ JSONObject.toJSONString(zjCashConfig1)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            zjCashConfigMapper.updateZjCashConfig(zjCashConfig1);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("修改中金代付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后重试");
        }
        return map;

    }

    @Override
    public Map<String,Object> addZjCashConfig(ZjCashConfig zjCashConfig1) {
        logger.info("添加中金代付配置入参:["+ JSONObject.toJSONString(zjCashConfig1)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            zjCashConfigMapper.addZjCashConfig(zjCashConfig1);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("添加中金代付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后再试");
        }
    return map;

    }

}
