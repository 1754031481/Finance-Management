package com.zc.service.impl.finance;

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
import com.zc.dao.alpay.AlPayMapper;
import com.zc.entity.finance.AlPayConfig;
import com.zc.service.finance.AlPayService;
import com.zc.vo.SystemFromVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 杨文杰
 * @Title: AlPayServiceImpl
 * @Description: 支付宝配置
 * @date 2018/4/9
 */

@Service
public class AlPayServiceImpl implements AlPayService{

    private static Logger logger = LoggerFactory.getLogger(AlPayServiceImpl.class);

    @Autowired
    private AlPayMapper alPayMapper;
    @Override
    public Result getList(PageBean pageBean) {
        Result result = new Result();
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<AlPayConfig> alPayList = alPayMapper.getAlPayList();
        //Cat.logEvent("AlPayServiceImpl","查看支付宝配置信息", Event.SUCCESS,"alPayList="+JSON.toJSON(alPayList));
        logger.info("获取订单列表时service得到的数据"+ JSON.toJSON(alPayList));
        PageInfo<AlPayConfig> pageInfo = new PageInfo<>(alPayList);
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
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result updateAlPay(AlPayConfig alPayConfig) {
        //Cat.logEvent("AlPayServiceImpl","添加修改支付宝配置数据", Event.SUCCESS,"alPayConfig="+JSON.toJSON(alPayConfig));
        logger.info("--------------添加修改支付宝配置数据Servcie，入参-----------------"+JSON.toJSON(alPayConfig));
        Result result = new Result();
        try{
            if(alPayConfig.getId()!=null){
                //编辑操作
                alPayMapper.updateAlPay(alPayConfig);
            }else{
                //添加操作
                alPayConfig.setCreatedTime(new Date());
                alPayConfig.setUpdateTime(new Date());
                alPayMapper.insert(alPayConfig);
            }
            result.setCode("200");
            result.setMessage("支付宝配置添加编辑成功");
            return result;
        }catch (Exception e){
            logger.error("------------支付宝配置添加编辑失败----------------"+e);
           // Cat.logError("支付宝配置添加编辑失败",e);
            result.setCode("0");
            result.setMessage("支付宝配置添加编辑失败");
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
    public Result getAlPay(Long id) {
        //Cat.logEvent("AlPayServiceImpl","查看支付宝配置信息入参", Event.SUCCESS,"id="+id);
        logger.info("--------------查看支付宝配置信息Service,id-----------------"+id);
        AlPayConfig alPayConfig = alPayMapper.getAlPayById(id);
        //Cat.logEvent("AlPayServiceImpl","查看支付宝配置信息出参", Event.SUCCESS,"alPayConfig="+JSON.toJSON(alPayConfig));
        logger.info("--------------查看支付宝配置信息Service,id-----------------"+JSON.toJSON(alPayConfig));
        return ResultUtil.getResult(RespCode.Code.SUCCESS,alPayConfig);
    }

    /**
     * 展示下拉框
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    @Override
    public Result getSelect() {
        logger.info("--------------支付宝配置展示下拉框Service-----------------");
        List<SystemFromVO> list = alPayMapper.getSelect();
        //Cat.logEvent("AlPayServiceImpl","支付宝配置展示下拉框出参", Event.SUCCESS,"list="+JSON.toJSON(list));
        logger.info("--------------支付宝配置展示下拉框Service，出参-----------------"+JSON.toJSON(list));
        return ResultUtil.getResult(RespCode.Code.SUCCESS,list);
    }
}
