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
import com.zc.dao.ldconfig.LDCashMapper;
import com.zc.entity.ldconfig.LDCashConfig;
import com.zc.service.ldconfig.LDCashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 杨文杰
 * @Title: LDCashServiceImpl
 * @Description: 联动优势代付配置
 * @date 2018/4/11
 */

@Service
public class LDCashServiceImpl implements LDCashService {

    private static Logger logger = LoggerFactory.getLogger(LDCashServiceImpl.class);

    @Autowired
    private LDCashMapper LDCashMapper;
    @Override
    public Result getList(PageBean pageBean) {
        logger.info("--------------获取联动优势代付配置数据Servcie-----------------");
        Result result = new Result();
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<LDCashConfig> ldCashList = LDCashMapper.getLdCashList();
        //Cat.logEvent("LDCashServiceImpl","查看联动优势代付配置信息", Event.SUCCESS,"ldCashList="+JSON.toJSON(ldCashList));
        logger.info("获取订单列表时service得到的数据"+ JSON.toJSON(ldCashList));
        PageInfo<LDCashConfig> pageInfo = new PageInfo<>(ldCashList);
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
    public Result updateLdCash(LDCashConfig LDCashConfig) {
        //Cat.logEvent("LDCashServiceImpl","添加修改联动优势代付配置数据", Event.SUCCESS,"LDCashConfig="+JSON.toJSON(LDCashConfig));
        logger.info("--------------添加修改联动优势代付配置数据Servcie，入参-----------------"+JSON.toJSON(LDCashConfig));
        Result result = new Result();
        try{
            if(LDCashConfig.getId()!=null){
                //编辑操作
                LDCashMapper.updateLdCash(LDCashConfig);
            }else{
                //添加操作
                LDCashConfig.setCreatedTime(new Date());
                LDCashConfig.setUpdateTime(new Date());
                LDCashMapper.insert(LDCashConfig);
            }
            result.setCode("200");
            result.setMessage("联动优势代付配置添加编辑成功");
            return result;
        }catch (Exception e){
            //Cat.logError("联动优势代付配置添加编辑失败",e);
            logger.error("------------联动优势代付配置添加编辑失败----------------"+e);
            result.setCode("0");
            result.setMessage("联动优势代付配置添加编辑失败");
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
    public Result getLdCash(Long id) {
        logger.info("--------------查看联动优势代付配置信息Service,id-----------------"+id);
        //Cat.logEvent("LDCashServiceImpl","查看联动优势代付配置信息入参", Event.SUCCESS,"id="+id);
        LDCashConfig LDCashConfig = LDCashMapper.getLdCashById(id);
        logger.info("--------------查看联动优势代付配置信息Service,id-----------------"+JSON.toJSON(LDCashConfig));
        //Cat.logEvent("LDCashServiceImpl","查看联动优势代付配置信息出参", Event.SUCCESS,"LDCashConfig="+JSON.toJSON(LDCashConfig));
        return ResultUtil.getResult(RespCode.Code.SUCCESS, LDCashConfig);
    }


}
