package com.zc.service.impl.xsConfig;

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
import com.zc.dao.xsconfig.XSCashMapper;
import com.zc.entity.xsconfig.XSCashConfig;
import com.zc.service.xsconfig.XSCashService;
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
 * @Title: XSCashServiceImpl
 * @Description: 新生代付配置
 * @date 2018/4/11
 */

@Service
public class XSCashServiceImpl implements XSCashService {

    private static Logger logger = LoggerFactory.getLogger(XSCashServiceImpl.class);

    @Autowired
    private XSCashMapper XSCashMapper;
    @Override
    public Result getList(PageBean pageBean) {
        logger.info("--------------获取新生代付配置数据Servcie-----------------");
        Result result = new Result();
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<XSCashConfig> xsCashList = XSCashMapper.getXsCashList();
        logger.info("获取订单列表时service得到的数据"+ JSON.toJSON(xsCashList));
        //Cat.logEvent("XSCashServiceImpl","查看新生代付配置信息", Event.SUCCESS,"xsCashList="+JSON.toJSON(xsCashList));
        PageInfo<XSCashConfig> pageInfo = new PageInfo<>(xsCashList);
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
    public Result updateXsCash(XSCashConfig XSCashConfig) {
        logger.info("--------------添加修改新生代付配置数据Servcie，入参-----------------"+JSON.toJSON(XSCashConfig));
        //Cat.logEvent("XSCashServiceImpl","添加修改新生代付配置数据", Event.SUCCESS,"XSCashConfig="+JSON.toJSON(XSCashConfig));
        Result result = new Result();
        try{
            if(XSCashConfig.getId()!=null){
                //编辑操作
                XSCashMapper.updateXsCash(XSCashConfig);
            }else{
                //添加操作
                XSCashConfig.setCreatedTime(new Date());
                XSCashConfig.setUpdateTime(new Date());
                XSCashMapper.insert(XSCashConfig);
            }
            result.setCode("200");
            result.setMessage("新生代付配置添加编辑成功");
            return result;
        }catch (Exception e){
            logger.error("------------新生代付配置添加编辑失败----------------"+e);
           // Cat.logError("新生代付配置添加编辑失败",e);
            result.setCode("0");
            result.setMessage("新生代付配置添加编辑失败");
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
    public Result getXsCash(Long id) {
        logger.info("--------------查看新生代付配置信息Service,id-----------------"+id);
        //Cat.logEvent("XSCashServiceImpl","查看新生代付配置信息入参", Event.SUCCESS,"id="+id);
        XSCashConfig XSCashConfig = XSCashMapper.getXsCashById(id);
        //Cat.logEvent("XSCashServiceImpl","查看新生代付配置信息出参", Event.SUCCESS,"XSCashConfig="+JSON.toJSON(XSCashConfig));
        logger.info("--------------查看新生代付配置信息Service,id-----------------"+JSON.toJSON(XSCashConfig));
        return ResultUtil.getResult(RespCode.Code.SUCCESS, XSCashConfig);
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
        List<SystemFromVO> list = XSCashMapper.getSelect();
        logger.info("--------------展示下拉框Service，出参-----------------"+JSON.toJSON(list));
        return ResultUtil.getResult(RespCode.Code.SUCCESS,list);
    }
}
