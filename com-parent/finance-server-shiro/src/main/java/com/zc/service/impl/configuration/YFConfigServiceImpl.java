package com.zc.service.impl.configuration;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.configuration.YFcashConfigMapper;
import com.zc.dao.configuration.YFpayConfigMapper;
import com.zc.entity.configuration.YFCashConfig;
import com.zc.entity.configuration.YFPayConfig;
import com.zc.service.configuration.YFConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: 王楠
 * @version:
 * @Description: 裕福配置服务层实现
 **/
@Service
public class YFConfigServiceImpl implements YFConfigService {

    Logger logger = LoggerFactory.getLogger(YFConfigServiceImpl.class);

    @Autowired
    private YFpayConfigMapper yfpayConfigMapper;

    @Autowired
    private YFcashConfigMapper yfcashConfigMapper;

    /**
     * @param page 分页信息
     * @return 裕福渠道支付配置列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取裕福渠道支付配置列表
     */
    @Override
    public HashMap<String, Object> getPayList(PageBean page) {
        logger.info("--------------------分页获取裕福渠道支付配置列表服务入参："+JSONObject.toJSONString(page));
        HashMap<String,Object> map=new HashMap<>();
        try {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<YFPayConfig> list = yfpayConfigMapper.getPayList();
            logger.info("--------------------分页获取裕福渠道支付配置列表返回数据列表："+JSONObject.toJSONString(list));
            PageInfo<YFPayConfig> info = new PageInfo<>(list);
            map.put("code",200);
            map.put("msg","操作成功");
            map.put("content",info.getList());
            map.put("count",info.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","服务器内部异常");
            logger.error("--------------------分页获取裕福渠道支付配置列表服务异常--------------------------------");
        }
        return map;
    }

    /**
     * @param yfPayConfig 添加信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加裕福渠道支付配置
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addPay(YFPayConfig yfPayConfig) {
        logger.info("--------------------添加浦发添加扫码渠道服务参数："+JSONObject.toJSONString(yfPayConfig));
        Result result = new Result();
        try {
            yfPayConfig.setCreatedTime(new Date());
            yfPayConfig.setUpdateTime(new Date());
            yfpayConfigMapper.insert(yfPayConfig);
            result = ResultUtils.returnSuccess("成功");
        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------添加浦发扫码渠道服务异常--------------------------------");
        }
        return result;
    }

    /**
     * @param yfPayConfig 修改信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改裕福渠道支付配置
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updatePay(YFPayConfig yfPayConfig) {
        logger.info("--------------------修改浦发扫码渠道服务参数："+JSONObject.toJSONString(yfPayConfig));
        Result result = new Result();
        try {
            yfpayConfigMapper.updatePay(yfPayConfig);
            result = ResultUtils.returnSuccess("成功");
        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------修改浦发扫码渠道服务异常--------------------------------");
        }
        return result;
    }


    /**
     * @param page 分页信息
     * @return 裕福渠道代付配置列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取裕福渠道代付配置列表
     **/
    @Override
    public HashMap<String, Object> getCashList(PageBean page) {
        logger.info("--------------------分页获取裕福渠道代付配置列表服务入参："+JSONObject.toJSONString(page));
        HashMap<String,Object> map=new HashMap<>();
        try {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<YFCashConfig> list = yfcashConfigMapper.getList();
            logger.info("--------------------分页获取裕福渠道代付配置列表返回数据列表："+JSONObject.toJSONString(list));
            PageInfo<YFCashConfig> info = new PageInfo<>(list);
            map.put("code",200);
            map.put("msg","操作成功");
            map.put("content",info.getList());
            map.put("count",info.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","服务器内部异常");
            logger.error("--------------------分页获取裕福渠道代付配置列表服务异常--------------------------------");
        }
        return map;
    }

    /**
     * @param yfCashConfig 添加信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加裕福渠道代付配置
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addCash(YFCashConfig yfCashConfig) {
        logger.info("--------------------添加裕福渠道代付配置服务参数："+JSONObject.toJSONString(yfCashConfig));
        Result result = new Result();
        try {
            yfCashConfig.setCreatedTime(new Date());
            yfCashConfig.setUpdateTime(new Date());
            yfcashConfigMapper.insert(yfCashConfig);
            result = ResultUtils.returnSuccess("成功");
        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------添加裕福渠道代付配置服务异常--------------------------------");
        }
        return result;
    }

    /**
     * @param yfCashConfig 修改信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改裕福渠道代付配置
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateCash(YFCashConfig yfCashConfig) {
        logger.info("--------------------修改裕福渠道代付配置服务参数："+JSONObject.toJSONString(yfCashConfig));
        Result result = new Result();
        try {
            yfcashConfigMapper.update(yfCashConfig);
            result = ResultUtils.returnSuccess("成功");
        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------修改裕福渠道代付配置服务异常--------------------------------");
        }
        return result;
    }
}
