package com.zc.service.impl.finance;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.utils.DoubleUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.RespCode;
import com.zc.common.util.core.ResultUtil;
import com.zc.common.util.result.Result;
import com.zc.dao.commonpay.CommonPayMapper;
import com.zc.entity.finance.CommonPayConfig;
import com.zc.service.finance.CommonPayService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨文杰
 * @Title: CommonPayController
 * @Description: 通用配置
 * @date 2018/4/8
 */

@Service
public class CommonPayServiceImpl implements CommonPayService{

    private static Logger logger = LoggerFactory.getLogger(CommonPayServiceImpl.class);

    @Autowired
    private CommonPayMapper commonPayMapper;

    /**
     * 分页获取通用配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    @Override
    public Result getList(PageBean pageBean) {
        logger.info("--------------获取通用配置数据Servcie-----------------");
        Result result = new Result();
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<CommonPayConfig> commonPayList = commonPayMapper.getCommonPayList();
        //Cat.logEvent("CommonPayServiceImpl","查看通用配置信息", Event.SUCCESS,"commonPayList="+JSON.toJSON(commonPayList));
        logger.info("获取订单列表时service得到的数据"+ JSON.toJSON(commonPayList));
        PageInfo<CommonPayConfig> pageInfo = new PageInfo<>(commonPayList);
        HashMap<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("msg","操作成功");
        map.put("content",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
       // return ResultUtil.getResult(RespCode.Code.SUCCESS,pageInfo.getList(),pageInfo.getTotal());
        return ResultUtil.getResult(RespCode.Code.SUCCESS,map);
    }

    /**
     * 添加修改
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result updateCommonPay(CommonPayConfig commonPayConfig) {
        logger.info("--------------添加修改通用配置数据Servcie，入参-----------------"+JSON.toJSON(commonPayConfig));
        //Cat.logEvent("CommonPayServiceImpl","添加修改通用配置数据", Event.SUCCESS,"commonPayConfig="+JSON.toJSON(commonPayConfig));
        Result result = new Result();
        try{
            if(commonPayConfig.getId()!=null){
                //编辑操作
                commonPayMapper.updateCommonPay(commonPayConfig);
            }else{
                //添加操作
                commonPayMapper.insertCommonPay(commonPayConfig);
            }
            result.setCode("200");
            result.setMessage("通用配置添加编辑成功");
            return result;
        }catch (Exception e){
            logger.error("------------通用配置添加编辑失败----------------"+e);
           // Cat.logError("通用配置添加编辑失败",e);
            result.setCode("0");
            result.setMessage("通用配置添加编辑失败");
            return result;
        }

    }
    /**
     * 回显
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    @Override
    public Result getCommonPay(Long id) {
        logger.info("--------------查看通用配置信息Service,id-----------------"+id);
        //Cat.logEvent("CommonPayServiceImpl","查看通用配置信息入参", Event.SUCCESS,"id="+id);
        CommonPayConfig commonPayConfig = commonPayMapper.getCommonPayById(id);
        //Cat.logEvent("CommonPayServiceImpl","查看通用配置信息出参", Event.SUCCESS,"commonPayConfig="+JSON.toJSON(commonPayConfig));
        logger.info("--------------查看通用配置信息Service,id-----------------"+JSON.toJSON(commonPayConfig));
        return ResultUtil.getResult(RespCode.Code.SUCCESS,commonPayConfig);
    }

    /**
     * @description: 根据id查询
     * @author:  ZhaoJunBiao
     * @date:  2018-04-13 17:49
     * @version: 1.0.0
     * @param commonPayConfigId
     * @return
     */
    @Override
    public CommonPayConfig getCommonPayOne(Long commonPayConfigId) {
        CommonPayConfig commonPayConfig = new CommonPayConfig();
        commonPayConfig.setId(commonPayConfigId);
        return commonPayMapper.selectOne(commonPayConfig);
    }

    /**
     * @description: 获取配置
     * @author:  ZhaoJunBiao
     * @date:  2018-04-25 13:40
     * @version: 1.0.0
     * @return
     */
    @Override
    public Map<String, Object> findCommonPayConfigList() {
        List<CommonPayConfig> commonPayConfigList = commonPayMapper.getCommonPayList();
        Map<String,Object> map = new HashedMap();
        map.put("code","200");
        map.put("content",commonPayConfigList);
        return map;
    }


}
