package com.zc.service.impl.syxconfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.syxconfig.SyxPayConfigMapper;
import com.zc.entity.syxconfig.SYXPayConfig;
import com.zc.service.syxconfig.SyxPayConfigService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.impl.syxconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月10日9:43
 */
@Service
@Transactional
public class SyxPayConfigServiceImpl implements SyxPayConfigService {

    private Logger logger = Logger.getLogger(SyxPayConfigServiceImpl.class);

    @Autowired
    private SyxPayConfigMapper syxPayConfigMapper;
    /**
     * @description: 商银信支付配置列表
     * @author:  ZhaoJunBiao
     * @date:  2018-04-10 9:45
     * @version: 1.0.0
     * @param pageBean
     * @return
     */
    @Override
    public Map<String, Object> findSyxPayList(PageBean pageBean) {
        logger.info("商银信支付配置列表方法入参");
        Map<String,Object> map=null;
        try {
            PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
            List<Map<String,Object>> syxPayConfigList = syxPayConfigMapper.findSyxPayList();
            PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(syxPayConfigList);
             map = new HashedMap();
            map.put("content",pageInfo.getList());
            map.put("count",pageInfo.getTotal());
            map.put("code",200);
            return map;
        }catch (Exception e){
            logger.info("商银信支付配置列表查询异常");
            e.printStackTrace();
            map.put("code",300);
        }
        return map;
    }

    /**
     * @description: 商银信支付添加配置
     * @author:  ZhaoJunBiao
     * @date:  2018-04-10 12:00
     * @version: 1.0.0
     * @param syxPayConfig
     * @return
     */
    @Override
    public Result addSyxPayConfig(SYXPayConfig syxPayConfig) {
        logger.info("商银信添加调用方法入参，syxPayConfig=["+ JSONObject.toJSONString(syxPayConfig)+"]");
        Result result = new Result();
        try {
            syxPayConfig.setCreatedTime(new Date());
            syxPayConfig.setUpdateTime(new Date());
            syxPayConfigMapper.insert(syxPayConfig);
        }catch (Exception e){
            logger.info("商银信添加方法调用失败");
            e.printStackTrace();
            result.setCode(0);
        }
        return result;
    }

    /**
     * @description: 商银信支付修改配置
     * @author:  ZhaoJunBiao
     * @date:  2018-04-10 12:00
     * @version: 1.0.0
     * @param syxPayConfig
     * @return
     */
    @Override
    public Result updateSyxPayConfig(SYXPayConfig syxPayConfig) {
        logger.info("商银信修改调用方法入参，syxPayConfig=["+JSONObject.toJSONString(syxPayConfig)+"]");
        Result result = new Result();
        try {
            syxPayConfig.setUpdateTime(new Date());
            syxPayConfigMapper.updateSyxPayConfig(syxPayConfig);
        }catch (Exception e){
            logger.info("商银信修改方法调用失败");
            e.printStackTrace();
            result.setCode(0);
        }
        return result;
    }

    /**
     * @description: 商银信支付单一配置查看
     * @author:  ZhaoJunBiao
     * @date:  2018-04-10 12:01
     * @version: 1.0.0
     * @return
     */
    @Override
    public Result findSystemForm() {
        logger.info("商银信支付查看配置方法调用入参");
        Result result = new Result();
        try {
           List<Map<String,Object>>  systemFormList =  syxPayConfigMapper.findSystemForm();
            result.setContent(systemFormList);
        } catch (Exception e) {
            logger.info("商银信支付查看配置异常");
            e.printStackTrace();
            result.setCode(0);
        }
        return result;
    }

}
