package com.zc.service.impl.ffconfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.ffconfig.FfPayConfigMapper;
import com.zc.entity.ffconfig.FFPayConfig;
import com.zc.service.ffconfig.FfconfigService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.impl.ffconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月08日15:25
 */
@Service
@Transactional
public class FfconfigServiceImpl implements FfconfigService {

    private Logger logger = Logger.getLogger(FfconfigServiceImpl.class);

    @Autowired
    private FfPayConfigMapper ffconfigMapper;

    /**
     * @description:  丰付支付渠道配置展示
     * @author:  ZhaoJunBiao
     * @date:  2018-04-08 15:42
     * @version: 1.0.0
     * @return
     */
    @Override
    public Map<String,Object>  getFfConfig(PageBean pageBean) {
        logger.info("进入丰付支付渠道配置展示，参数：pageBean,["+pageBean.toString()+"]");
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Map<String,Object>> ffPayConfigList = ffconfigMapper.findFfPayConfig();
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(ffPayConfigList);
        Map<String,Object> map = new HashMap();
        map.put("content",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code","200");
        return map;
    }

    @Override
    public Map<String,Object>  findConfig(Long ffPayId) {
        logger.info("查看丰付支付单一配置方法入参,ffPayId=["+ffPayId+"]");
        List<Map<String,Object>> mapsc = ffconfigMapper.findConfig(ffPayId);
        Map<String,Object> map = new HashMap();
        map.put("content",mapsc);
        map.put("code","200");
        return map;
    }

    @Override
    public Map<String,Object> updateFfPayConfig(FFPayConfig ffPayConfig) {
        logger.info("修改丰付支付配置入参:["+ JSONObject.toJSONString(ffPayConfig)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            ffconfigMapper.updateFfPayConfig(ffPayConfig);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("修改丰付支付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后重试");
        }
        return map;

    }

    @Override
    public Map<String,Object> addFfPayConfig(FFPayConfig ffPayConfig) {
        logger.info("添加丰付支付配置入参:["+ JSONObject.toJSONString(ffPayConfig)+"]");
        Map<String,Object> map = new HashedMap();
        try {
            ffconfigMapper.addFfPayConfig(ffPayConfig);
            map.put("code",200);
            map.put("msg","操作成功");
        }catch (Exception e){
            logger.info("添加丰付支付配置异常");
            e.printStackTrace();
            map.put("code",300);
            map.put("msg","操作失败，请稍后再试");
        }
    return map;

    }
}
