package com.zc.service.impl.interfacesupport;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.CodeConstExt;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.interfaceentity.InterFaceEntityMapper;
import com.zc.entity.interfaceentity.InterfaceEntity;
import com.zc.service.interfacesupport.InterFaceSupportService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.impl.interfacesupport
 * @progect : Finance-Management
 * @Description : 接口支持模块service实现层
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月23日13:50
 */
@Service
@Transactional
public class InterFaceSupportServiceImpl implements InterFaceSupportService {

    private Logger logger = Logger.getLogger(InterFaceSupportServiceImpl.class);
    @Autowired
    private InterFaceEntityMapper interFaceEntityMapper;


    /**
     * @param pageBean
     * @return
     * @description: 渠道信息分页展示
     * @author: ZhaoJunBiao
     * @date: 2018-04-23 14:07
     * @version: 1.0.0
     */
    @Override
    public Map<String, Object> findInterFaceList(PageBean pageBean,String type) {
        logger.info("渠道信息列表方法入参");
        Map<String, Object> map = null;
        try {
            map = new HashedMap();
            PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
            List<Map<String, Object>> syxCashConfigList = interFaceEntityMapper.findInterFaceList(type);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(syxCashConfigList);
            map.put("content", pageInfo.getList());
            map.put("count", pageInfo.getTotal());
            map.put("code", 200);
            return map;
        } catch (Exception e) {
            logger.info("渠道信息列表列表查询异常");
            e.printStackTrace();
            map.put("code", 300);
        }
        return map;
    }


    /**
     * @param interfaceEntity
     * @return
     * @description: 添加接口
     * @author: ZhaoJunBiao
     * @date: 2018-04-23 15:21
     * @version: 1.0.0
     */
    @Override
    public Result addInterFace(InterfaceEntity interfaceEntity,String isFinal) {
        logger.info("添加接口调用方法入参，interfaceEntity=[" + JSONObject.toJSONString(interfaceEntity) + "]");
        Result result = new Result();
        if("0".equals(isFinal)){
            if(interfaceEntity.getType()==0){
                //还原原有支付final渠道
                interFaceEntityMapper.reducePayFinalChannel();
            }else{
                //还原原有代付final渠道
                interFaceEntityMapper.reduceCashFinalChannel();
            }
            interfaceEntity.setIsShunting("final");
        }
        try {
            if (interfaceEntity.getType() == 1) {
                interfaceEntity.setIsShuntingCash(interfaceEntity.getIsShunting());
                interfaceEntity.setIsShunting(null);
            }
            if (interfaceEntity.getType() == 1) {
                interfaceEntity.setWeightCash(interfaceEntity.getWeight());
                interfaceEntity.setWeight(null);
            }
            interfaceEntity.setCreatedTime(new Date());
            interfaceEntity.setUpdateTime(new Date());
            interFaceEntityMapper.insert(interfaceEntity);
        } catch (Exception e) {
            logger.info("添加接口方法调用失败");
            e.printStackTrace();
            result.setCode(0);
        }
        return result;
    }

    /**
     * @param interfaceEntity
     * @return
     * @description: 修改接口
     * @author: ZhaoJunBiao
     * @date: 2018-04-23 15:21
     * @version: 1.0.0
     */
    @Override
    public Result updateInterFace(InterfaceEntity interfaceEntity,String isFinal) {
        logger.info("修改接口调用方法入参，interfaceEntity=[" + JSONObject.toJSONString(interfaceEntity) + "]");
        Result result = new Result();
        if("0".equals(isFinal)){
            if(interfaceEntity.getType()==0){
                //还原原有支付final渠道
                interFaceEntityMapper.reducePayFinalChannel();
            }else{

                //还原原有代付final渠道
                interFaceEntityMapper.reduceCashFinalChannel();
            }
            interfaceEntity.setIsShunting("final");
        }
        try {
            interfaceEntity.setUpdateTime(new Date());
            if (interfaceEntity.getType() == 1) {
                interfaceEntity.setIsShuntingCash(interfaceEntity.getIsShunting());
                interfaceEntity.setIsShunting(null);
                interfaceEntity.setWeightCash(interfaceEntity.getWeight());
                interfaceEntity.setWeight(null);
            }
            interFaceEntityMapper.updateInterFace(interfaceEntity);
        } catch (Exception e) {
            logger.info("修改接口方法调用失败");
            e.printStackTrace();
            result.setCode(0);
        }
        return result;
    }

    @Override
    public Map<String, Object> findInterFaceList2() {
        List<Map<String, Object>> interFaceList = interFaceEntityMapper.findInterFaceList(null);
        Map<String, Object> map = new HashedMap();
        map.put("code", "200");
        map.put("content", interFaceList);
        return map;
    }


    /**
     * @param interFaceName
     * @return
     * @description: 根据interface查询PayConfigId
     * @author: ZhaoJunBiao
     * @date: 2018-04-26 14:47
     * @version: 1.0.0
     */
    @Override
    public List<Map<String,Object>> findPayConfigId(String interFaceName) {
        String tableName = CodeConstExt.tableMap.get(interFaceName);
        return interFaceEntityMapper.findPayConfigId(tableName);
    }

    @Override
    public Result selectFinalChannel(String type) {
        //Cat.logEvent("InterFaceSupportServiceImpl","查询final渠道");
        logger.info("查询final渠道");
        Result result = new Result();
        String channelName = null;
        try {
            channelName = interFaceEntityMapper.selectFinalChannel(type);
        }catch (Exception e){
            //Cat.logError("查询final渠道失败",e);
            logger.error("查询final渠道失败"+e.toString());
            return ResultUtils.returnError("查询失败");
        }
        if(channelName==null){
            channelName="null";
        }
        result.setCode(200);
        result.setMsg("查询成功");
        result.setContent(channelName);
        return result;
    }
}
