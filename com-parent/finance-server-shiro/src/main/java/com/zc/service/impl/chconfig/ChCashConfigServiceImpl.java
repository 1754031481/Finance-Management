package com.zc.service.impl.chconfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.chconfig.ChCashConfigMapper;
import com.zc.entity.chconfig.CHCashConfig;
import com.zc.service.chcofig.ChCashConfigService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 丰付代付配置
 * @author:  史云顺
 * @date:  2018-04-13
 * @version: 1.0.0
 */
@Service
@Transactional
public class ChCashConfigServiceImpl implements ChCashConfigService {

    private Logger logger = Logger.getLogger(ChCashConfigServiceImpl.class);

    @Autowired
    private ChCashConfigMapper chCashConfigMapper;




    /**
     * @description:  传化代付渠道配置展示
     * @author:  史云顺
     * @date:  2018-04-13
     * @version: 1.0.0
     * @return
     */
    @Override
    public Map<String,Object>  getChCashConfig(PageBean pageBean) {
        logger.info("进入传化代付渠道配置展示，参数：pageBean,["+pageBean.toString()+"]");
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Map<String,Object>> ChPayConfigList = chCashConfigMapper.findChCashConfig();
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(ChPayConfigList);
        Map<String,Object> map = new HashMap();
        map.put("content",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code","200");
        return map;
    }

    @Override
    public Map<String,Object>  findCashConfig(Long ffCashId) {
        logger.info("传化代付查看单一配置方法入参：ffCashId=["+ffCashId+"]");
        Map<String,Object> map = new HashMap();
        try {
            List<Map<String,Object>> mapsc = chCashConfigMapper.findCashConfig(ffCashId);
            map.put("content",mapsc);
            map.put("code","200");
        }catch (Exception e){
            logger.info("传化代付查看单一配置方法异常");
            e.printStackTrace();
            map.put("code","300");
        }
        return map;
    }

    @Override
    public Map<String, Object> updateChCashConfig(CHCashConfig chcashConfig) {
        logger.info("修改丰付代付配置入参:["+ JSONObject.toJSONString(chcashConfig)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            chCashConfigMapper.updateChCashConfig(chcashConfig);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("修改丰付代付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后重试");
        }
        return map;
    }

    @Override
    public Map<String, Object> addChCashConfig(CHCashConfig chcashConfig) {
        logger.info("添加传化代付配置入参:["+ JSONObject.toJSONString(chcashConfig)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            chCashConfigMapper.addChCashConfig(chcashConfig);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("添加传化代付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后再试");
        }
        return map;
    }

}
