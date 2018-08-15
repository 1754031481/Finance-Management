package com.zc.service.impl.finance;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.dao.finance.CashmentHistoryMapper;
import com.zc.entity.finance.Finance;
import com.zc.entity.logs.LoginOperatingLogs;
import com.zc.service.finance.CashmentHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author: 王楠
 * @version:
 * @Description: 代付渠道设置服务层实现
 **/
@Service
public class CashmentHistoryServiceImpl implements CashmentHistoryService {

    Logger logger = LoggerFactory.getLogger(CashmentHistoryServiceImpl.class);

    @Autowired
    CashmentHistoryMapper cashmentHistoryMapper;

    /**
     * @param page 分页信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 分页获取代付交易历史记录列表
     */
    @Override
    public HashMap<String, Object> getlist(PageBean page) {
        logger.info("--------------------分页获取代付交易历史记录列表服务参数："+JSONObject.toJSONString(page));
        HashMap<String,Object> map=new HashMap<>();
        try {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<Finance> list = cashmentHistoryMapper.getlist();
            logger.info("--------------------分页获取代付交易历史记录列表服务返回数据列表："+JSONObject.toJSONString(list));
            PageInfo<Finance> info = new PageInfo<>(list);
            map.put("code",200);
            map.put("msg","操作成功");
            map.put("content",info.getList());
            map.put("count",info.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","服务器内部异常");
            logger.error("--------------------分页获取代付交易历史记录列表服务异常--------------------------------");
        }
        return map;
    }

    /**
     * @param project 分页信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取一个项目一周代付交易历史记录列表
     */
    @Override
    public Result getWeekData(String project) {
        logger.info("--------------------获取一个项目一周代付交易历史记录列表服务参数："+JSONObject.toJSONString(project));
        Result result = new Result();
        try {
            List<Finance> list = cashmentHistoryMapper.getWeekData(project);
            logger.info("--------------------获取一个项目一周代付交易历史记录数据列表："+JSONObject.toJSONString(list));
            result = ResultUtils.returnSuccess("获取数据成功",list);
        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------获取一个项目一周代付交易历史记录列表服务异常--------------------------------");
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result updateStatus(String ip,String name,Long id,Integer status) {
        logger.info("更改代付交易记录启用状态:id=" + id);
        Result result = new Result();
        if (null==id||null==status) {
            return  ResultUtils.returnError("参数异常");
        }
        //查询原数据
        Finance finance = cashmentHistoryMapper.selectById(id);
        String oldStatus = "";
        String newStatus = "";
        if(finance.getStatus()==0){
            oldStatus="启用";
            newStatus="禁用";
        }else{
            oldStatus="禁用";
            newStatus="启用";
        }
        try {
            int i = cashmentHistoryMapper.updateStatus(id,status);
            if(i>0){
                //记录操作日志
                LoginOperatingLogs loginOperatingLogs = new LoginOperatingLogs();
                loginOperatingLogs.setOperationLogs("更改代付交易历史记录启用状态:{id=" +finance.getId()+",项目名称="+finance.getProject()+",原启用状态="+oldStatus+",先启用状态="+newStatus+"}");
                loginOperatingLogs.setCreatedIp(ip);
                loginOperatingLogs.setCreateUser(name);
                cashmentHistoryMapper.insertLog(loginOperatingLogs);
                result.setCode(200);
                result.setMsg("操作成功");
                return result;
            }else{
                return  ResultUtils.returnError("操作失败");
            }
        }catch (Exception e){
            logger.info("更改代付交易启用状态异常"+e);
            return  ResultUtils.returnError("操作失败");
        }
    }
}
