package com.zc.service.impl.ldconfig;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.RespCode;
import com.zc.common.util.core.ResultUtil;
import com.zc.common.util.result.Result;
import com.zc.dao.ldconfig.LDPayMapper;
import com.zc.entity.ldconfig.LDPayConfig;
import com.zc.service.ldconfig.LDPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 杨文杰
 * @Title: LDPayServiceImpl
 * @Description: 联动优势支付配置
 * @date 2018/4/13
 */

@Service
public class LDPayServiceImpl implements LDPayService {

    private static Logger logger = LoggerFactory.getLogger(LDPayServiceImpl.class);

    @Autowired
    private LDPayMapper LDPayMapper;
    @Override
    public Result getList(PageBean pageBean) {
        logger.info("--------------获取联动优势支付配置数据Servcie-----------------");
        Result result = new Result();
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<LDPayConfig> ldPayList = LDPayMapper.getLdPayList();
        //Cat.logEvent("LDPayServiceImpl","查看联动优势支付配置信息", Event.SUCCESS,"ldPayList="+JSON.toJSON(ldPayList));
        logger.info("获取订单列表时service得到的数据"+ JSON.toJSON(ldPayList));
        PageInfo<LDPayConfig> pageInfo = new PageInfo<>(ldPayList);
        HashMap<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("msg","操作成功");
        map.put("content",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        return ResultUtil.getResult(RespCode.Code.SUCCESS,map);
    }

    /**
     * 添加修改
     * @author : 杨文杰
     * @Creation Date ： 2018/4/13
     * @version 1.0.0
     * @param
     *
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result updateLdPay(LDPayConfig LDPayConfig) {
        //Cat.logEvent("LDPayServiceImpl","添加修改联动优势支付配置数据", Event.SUCCESS,"LDPayConfig="+JSON.toJSON(LDPayConfig));
        logger.info("--------------添加修改联动优势支付配置数据Servcie，入参-----------------"+JSON.toJSON(LDPayConfig));
        Result result = new Result();
        try{
            if(LDPayConfig.getId()!=null){
                //编辑操作
                LDPayMapper.updateLdPay(LDPayConfig);
            }else{
                //添加操作
                LDPayConfig.setCreatedTime(new Date());
                LDPayConfig.setUpdateTime(new Date());
                LDPayMapper.insert(LDPayConfig);
            }
            result.setCode("200");
            result.setMessage("联动优势支付配置添加编辑成功");
            return result;
        }catch (Exception e){
            logger.error("------------联动优势支付配置添加编辑失败----------------"+e);
            //Cat.logError("联动优势支付配置添加编辑失败",e);
            result.setCode("0");
            result.setMessage("联动优势支付配置添加编辑失败");
            return result;
        }

    }
    /**
     * 回显
     * @author : 杨文杰
     * @Creation Date ： 2018/4/13
     * @version 1.0.0
     * @param
     *
     */
    @Override
    public Result getLdPay(Long id) {
        //Cat.logEvent("LDPayServiceImpl","查看联动优势支付配置信息入参", Event.SUCCESS,"id="+id);
        logger.info("--------------查看联动优势支付配置信息Service,id-----------------"+id);
        LDPayConfig LDPayConfig = LDPayMapper.getLdPayById(id);
        //Cat.logEvent("LDPayServiceImpl","查看联动优势支付配置信息出参", Event.SUCCESS,"LDPayConfig="+JSON.toJSON(LDPayConfig));
        logger.info("--------------查看联动优势支付配置信息Service,id-----------------"+JSON.toJSON(LDPayConfig));
        return ResultUtil.getResult(RespCode.Code.SUCCESS, LDPayConfig);
    }

}
