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
import com.zc.dao.ysconfig.YSCashMapper;
import com.zc.entity.ysconfig.YSCashConfig;
import com.zc.service.ysconfig.YSCashService;
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
 * @Title: YSCashServiceImpl
 * @Description: 银盛代付配置
 * @date 2018/4/11
 */

@Service
public class YSCashServiceImpl implements YSCashService {

    private static Logger logger = LoggerFactory.getLogger(YSCashServiceImpl.class);

    @Autowired
    private YSCashMapper YSCashMapper;
    @Override
    public Result getList(PageBean pageBean) {
        logger.info("--------------获取银盛代付配置数据Servcie-----------------");
        Result result = new Result();
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<YSCashConfig> ysCashList = YSCashMapper.getYsCashList();
        //Cat.logEvent("YSCashServiceImpl","查看银盛代付配置信息", Event.SUCCESS,"ysCashList="+JSON.toJSON(ysCashList));
        logger.info("获取订单列表时service得到的数据"+ JSON.toJSON(ysCashList));
        PageInfo<YSCashConfig> pageInfo = new PageInfo<>(ysCashList);
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
    public Result updateYsCash(YSCashConfig YSCashConfig) {
        //Cat.logEvent("YSCashServiceImpl","添加修改银盛代付配置数据", Event.SUCCESS,"YSCashConfig="+JSON.toJSON(YSCashConfig));
        logger.info("--------------添加修改银盛代付配置数据Servcie，入参-----------------"+JSON.toJSON(YSCashConfig));
        Result result = new Result();
        try{
            if(YSCashConfig.getId()!=null){
                //编辑操作
                YSCashMapper.updateYsCash(YSCashConfig);
            }else{
                //添加操作
                YSCashConfig.setCreatedTime(new Date());
                YSCashConfig.setUpdateTime(new Date());
                YSCashMapper.insert(YSCashConfig);
            }
            result.setCode("200");
            result.setMessage("银盛代付配置添加编辑成功");
            return result;
        }catch (Exception e){
            //Cat.logError("银盛代付配置添加编辑失败",e);
            logger.error("------------银盛代付配置添加编辑失败----------------"+e);
            result.setCode("0");
            result.setMessage("银盛代付配置添加编辑失败");
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
    public Result getYsCash(Long id) {
        //Cat.logEvent("YSCashServiceImpl","查看银盛代付配置信息入参", Event.SUCCESS,"id="+id);
        logger.info("--------------查看银盛代付配置信息Service,id-----------------"+id);
        YSCashConfig YSCashConfig = YSCashMapper.getYsCashById(id);
        //Cat.logEvent("YSCashServiceImpl","查看银盛代付配置信息出参", Event.SUCCESS,"YSCashConfig="+JSON.toJSON(YSCashConfig));
        logger.info("--------------查看银盛代付配置信息Service,id-----------------"+JSON.toJSON(YSCashConfig));
        return ResultUtil.getResult(RespCode.Code.SUCCESS, YSCashConfig);
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
        List<SystemFromVO> list = YSCashMapper.getSelect();
        logger.info("--------------展示下拉框Service，出参-----------------"+JSON.toJSON(list));
        return ResultUtil.getResult(RespCode.Code.SUCCESS,list);
    }
}
