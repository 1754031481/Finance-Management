package com.zc.service.impl.ysconfig;

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
import com.zc.dao.ysconfig.YSPayMapper;
import com.zc.entity.ysconfig.YSPayConfig;
import com.zc.service.ysconfig.YSPayService;
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
 * @Title: YSPayServiceImpl
 * @Description: 银盛支付配置
 * @date 2018/4/11
 */

@Service
public class YSPayServiceImpl implements YSPayService {

    private static Logger logger = LoggerFactory.getLogger(YSPayServiceImpl.class);

    @Autowired
    private YSPayMapper YSPayMapper;
    @Override
    public Result getList(PageBean pageBean) {
        logger.info("--------------获取银盛支付配置数据Servcie-----------------");
        Result result = new Result();
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<YSPayConfig> ysPayList = YSPayMapper.getYsPayList();
        //Cat.logEvent("YSPayServiceImpl","查看银盛支付配置信息", Event.SUCCESS,"ysPayList="+JSON.toJSON(ysPayList));
        logger.info("获取订单列表时service得到的数据"+ JSON.toJSON(ysPayList));
        PageInfo<YSPayConfig> pageInfo = new PageInfo<>(ysPayList);
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
    public Result updateYsPay(YSPayConfig YSPayConfig) {
        logger.info("--------------添加修改银盛支付配置数据Servcie，入参-----------------"+JSON.toJSON(YSPayConfig));
        //Cat.logEvent("YSPayServiceImpl","添加修改银盛支付配置数据", Event.SUCCESS,"YSPayConfig="+JSON.toJSON(YSPayConfig));
        Result result = new Result();
        try{
            if(YSPayConfig.getId()!=null){
                //编辑操作
                YSPayMapper.updateYsPay(YSPayConfig);
            }else{
                //添加操作
                YSPayConfig.setCreatedTime(new Date());
                YSPayConfig.setUpdateTime(new Date());
                YSPayMapper.insert(YSPayConfig);
            }
            result.setCode("200");
            result.setMessage("银盛支付配置添加编辑成功");
            return result;
        }catch (Exception e){
            //Cat.logError("银盛支付配置添加编辑失败",e);
            logger.error("------------银盛支付配置添加编辑失败----------------"+e);
            result.setCode("0");
            result.setMessage("银盛支付配置添加编辑失败");
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
    public Result getYsPay(Long id) {
        //Cat.logEvent("YSPayServiceImpl","查看银盛支付配置信息入参", Event.SUCCESS,"id="+id);
        logger.info("--------------查看银盛支付配置信息Service,id-----------------"+id);
        YSPayConfig YSPayConfig = YSPayMapper.getYsPayById(id);
        //Cat.logEvent("YSPayServiceImpl","查看银盛支付配置信息出参", Event.SUCCESS,"YSPayConfig="+JSON.toJSON(YSPayConfig));
        logger.info("--------------查看银盛支付配置信息Service,id-----------------"+JSON.toJSON(YSPayConfig));
        return ResultUtil.getResult(RespCode.Code.SUCCESS, YSPayConfig);
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
        List<SystemFromVO> list = YSPayMapper.getSelect();
        logger.info("--------------展示下拉框Service，出参-----------------"+JSON.toJSON(list));
        return ResultUtil.getResult(RespCode.Code.SUCCESS,list);
    }
}
