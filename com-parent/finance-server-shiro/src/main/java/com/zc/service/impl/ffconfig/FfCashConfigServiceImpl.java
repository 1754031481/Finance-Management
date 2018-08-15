package com.zc.service.impl.ffconfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.ffconfig.FfCashConfigMapper;
import com.zc.entity.ffconfig.FFCashConfig;
import com.zc.entity.ffconfig.FFPayConfig;
import com.zc.service.ffconfig.FfCashConfigService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 丰付代付配置
 * @author:  ZhaoJunBiao
 * @date:  2018-04-09 15:30
 * @version: 1.0.0
 */
@Service
@Transactional
public class FfCashConfigServiceImpl implements FfCashConfigService {

    private Logger logger = Logger.getLogger(FfCashConfigServiceImpl.class);

    @Autowired
    private FfCashConfigMapper ffCashConfigMapper;

    /**
     * @description:  丰付代付渠道配置展示
     * @author:  ZhaoJunBiao
     * @date:  2018-04-08 15:42
     * @version: 1.0.0
     * @return
     */
    @Override
    public Map<String,Object>  getFfCashConfig(PageBean pageBean) {
        logger.info("进入丰付代付渠道配置展示，参数：pageBean,["+pageBean.toString()+"]");
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Map<String,Object>> ffPayConfigList = ffCashConfigMapper.findFfCashConfig();
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(ffPayConfigList);
        Map<String,Object> map = new HashMap();
        map.put("content",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code","200");
        return map;
    }

    @Override
    public Map<String,Object>  findCashConfig(Long ffCashId) {
        logger.info("丰付代付查看单一配置方法入参：ffCashId=["+ffCashId+"]");
        Map<String,Object> map = new HashMap();
        try {
            List<Map<String,Object>> mapsc = ffCashConfigMapper.findCashConfig(ffCashId);
            map.put("content",mapsc);
            map.put("code","200");
        }catch (Exception e){
            logger.info("丰付代付查看单一配置方法异常");
            e.printStackTrace();
            map.put("code","300");
        }
        return map;
    }

    @Override
    public Map<String,Object> updateFfCashConfig(FFCashConfig ffCashConfig) {
        logger.info("修改丰付代付配置入参:["+ JSONObject.toJSONString(ffCashConfig)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            ffCashConfigMapper.updateFfCashConfig(ffCashConfig);
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
    public Map<String,Object> addFfCashConfig(FFCashConfig ffCashConfig) {
        logger.info("添加丰付代付配置入参:["+ JSONObject.toJSONString(ffCashConfig)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            ffCashConfigMapper.addFfCashConfig(ffCashConfig);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("添加丰付代付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后再试");
        }
    return map;

    }
}
