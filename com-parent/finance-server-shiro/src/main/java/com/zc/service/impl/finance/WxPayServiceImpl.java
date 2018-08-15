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
import com.zc.dao.wxpay.WxPayMapper;
import com.zc.entity.finance.WxPayConfig;
import com.zc.service.finance.WxPayService;
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
 * @Title: WxPayServiceImpl
 * @Description: 微信配置
 * @date 2018/4/10
 */

@Service
public class WxPayServiceImpl implements WxPayService{

    private static Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);

    @Autowired
    private WxPayMapper wxPayMapper;
    @Override
    public Result getList(PageBean pageBean) {
        logger.info("--------------获取微信配置数据Servcie-----------------");
        Result result = new Result();
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<WxPayConfig> wxPayList = wxPayMapper.getWxPayList();
        //Cat.logEvent("WxPayServiceImpl","查看微信配置信息", Event.SUCCESS,"wxPayList="+JSON.toJSON(wxPayList));
        logger.info("获取订单列表时service得到的数据"+ JSON.toJSON(wxPayList));
        PageInfo<WxPayConfig> pageInfo = new PageInfo<>(wxPayList);
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
    public Result updateWxPay(WxPayConfig wxPayConfig) {
        //Cat.logEvent("WxPayServiceImpl","添加修改微信配置数据", Event.SUCCESS,"wxPayConfig="+JSON.toJSON(wxPayConfig));
        logger.info("--------------添加修改微信配置数据Servcie，入参-----------------"+JSON.toJSON(wxPayConfig));
        Result result = new Result();
        try{
            if(wxPayConfig.getId()!=null){
                //编辑操作
                wxPayMapper.updateWxPay(wxPayConfig);
            }else{
                //添加操作
                wxPayConfig.setCreatedTime(new Date());
                wxPayConfig.setUpdateTime(new Date());
                wxPayMapper.insert(wxPayConfig);
            }
            result.setCode("200");
            result.setMessage("微信配置添加编辑成功");
            return result;
        }catch (Exception e){
           // Cat.logError("微信配置添加编辑失败",e);
            logger.error("------------微信配置添加编辑失败----------------"+e);
            result.setCode("0");
            result.setMessage("微信配置添加编辑失败");
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
    public Result getWxPay(Long id) {
       // Cat.logEvent("WxPayServiceImpl","查看微信配置信息入参", Event.SUCCESS,"id="+id);
        logger.info("--------------查看微信配置信息Service,id-----------------"+id);
        WxPayConfig wxPayConfig = wxPayMapper.getWxPayById(id);
        //Cat.logEvent("WxPayServiceImpl","查看微信配置信息出参", Event.SUCCESS,"wxPayConfig="+JSON.toJSON(wxPayConfig));
        logger.info("--------------查看微信配置信息Service,id-----------------"+JSON.toJSON(wxPayConfig));
        return ResultUtil.getResult(RespCode.Code.SUCCESS,wxPayConfig);
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
        logger.info("--------------展示下拉框Service-----------------");
        List<SystemFromVO> list = wxPayMapper.getSelect();
        //Cat.logEvent("WxPayServiceImpl","微信配置展示下拉框出参", Event.SUCCESS,"list="+JSON.toJSON(list));
        logger.info("--------------展示下拉框Service，出参-----------------"+JSON.toJSON(list));
        return ResultUtil.getResult(RespCode.Code.SUCCESS,list);
    }
}
