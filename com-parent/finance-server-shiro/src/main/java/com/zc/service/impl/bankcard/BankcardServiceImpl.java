package com.zc.service.impl.bankcard;

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
import com.zc.dao.bankcard.BankcardMapper;
import com.zc.entity.bankcard.Bankcard;
import com.zc.entity.logs.LoginOperatingLogs;
import com.zc.service.bankcard.BankcardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author 杨文杰
 * @Title: BankcardServiceImpl
 * @Description: 测试银行卡
 * @date 2018/6/4
 */

@Service
public class BankcardServiceImpl implements BankcardService{

    private static Logger logger = LoggerFactory.getLogger(BankcardServiceImpl.class);


    @Autowired
    private BankcardMapper bankcardMapper;

    @Override
    public Result getList(PageBean pageBean) {
        logger.info("--------------获取测试银行卡数据Servcie-----------------");
        Result result = new Result();
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<Bankcard> bankcardList = bankcardMapper.getBankcardList();
        ////Cat.logEvent("BankcardServiceImpl","获取测试银行卡列表", Event.SUCCESS,"bankcardList="+JSON.toJSON(bankcardList));
        logger.info("获取订单列表时service得到的数据"+ JSON.toJSON(bankcardList));
        PageInfo<Bankcard> pageInfo = new PageInfo<>(bankcardList);
        HashMap<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("msg","操作成功");
        map.put("content",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        return ResultUtil.getResult(RespCode.Code.SUCCESS,map);

    }

    /**
     * 添加银行卡
     * @author : 杨文杰
     * @Creation Date ： 2018/6/4
     * @version 1.0.0
     * @param
     *
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result addBankcard(Bankcard bankcard,String ip,String name) {
        ////Cat.logEvent("BankcardServiceImpl","添加测试银行卡", Event.SUCCESS,"bankcard="+JSON.toJSON(bankcard));
        logger.info("--------------添加测试银行卡数据Servcie，入参-----------------"+JSON.toJSON(bankcard));
        Result result = new Result();
        //查询银行卡号是否已存在
        int count = bankcardMapper.selectByBankcardNum(bankcard.getBankcardNum());
        if(count>0){
            return ResultUtil.setResult(false,"银行卡号已存在，请勿重复添加！");
        }
        try{
            //添加操作
            bankcard.setCreatedTime(new Date());
            bankcard.setUpdateTime(new Date());
            bankcard.setIsDelete(0);
            bankcardMapper.insertBankcard(bankcard);

            //记录操作日志
            String st = null;
            if(bankcard.getStatus()==0){
                st = "启用";
            }else{
                st = "禁用";
            }
            LoginOperatingLogs loginOperatingLogs = new LoginOperatingLogs();
            loginOperatingLogs.setOperationLogs("新增银行卡:{银行卡号="+bankcard.getBankcardNum()+",持卡人="+bankcard.getBankcardUser()+",启用状态="+st+",所属银行="+bankcard.getBankName()+"}");
            loginOperatingLogs.setCreatedIp(ip);
            loginOperatingLogs.setCreateUser(name);
            bankcardMapper.insertLog(loginOperatingLogs);
            result.setCode("200");
            result.setMessage("测试银行卡添加成功");
            return result;
        }catch (Exception e){
            logger.error("------------测试银行卡添加失败----------------"+e);
            //Cat.logError("测试银行卡添加失败",e);
            result.setCode("0");
            result.setMessage("添加失败");
            return result;
        }

    }
    /**
     * 更改银行卡启用状态
     * @author : 杨文杰
     * @Creation Date ： 2018/6/4
     * @version 1.0.0
     * @param
     *
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result updateStatus(Bankcard bankcard,String ip,String name) {
        //Cat.logEvent("BankcardServiceImpl","更改银行卡启用状态入参",Event.SUCCESS,"bankcard="+JSON.toJSON(bankcard));
        logger.info("service中接收的参数:" + JSON.toJSON(bankcard));
        Result result = new Result();
        if (Objects.isNull(bankcard.getId())) {
            return ResultUtil.setResult(false,"请求参数id为空");
        }
        if (Objects.isNull(bankcard.getStatus())) {
            return ResultUtil.setResult(false,"请求参数status为空");
        }
        //查询原有数据
        Bankcard bankcard1 = bankcardMapper.selectBankcardById(bankcard.getId());
        try {
            int i = bankcardMapper.updateStatus(bankcard.getId(), bankcard.getStatus());
            if (i > 0) {
                String oldStatus = null;
                String newStatus = null;
                if(bankcard1.getStatus()==0){
                    oldStatus = "启用";
                    newStatus = "禁用";
                }else{
                    oldStatus = "禁用";
                    newStatus = "启用";
                }
                //记录操作日志
                LoginOperatingLogs loginOperatingLogs = new LoginOperatingLogs();
                loginOperatingLogs.setOperationLogs("修改银行卡启用状态:{银行卡号="+bankcard1.getBankcardNum()+",原启用状态="+oldStatus+",现启用状态="+newStatus+"}");
                loginOperatingLogs.setCreatedIp(ip);
                loginOperatingLogs.setCreateUser(name);
                bankcardMapper.insertLog(loginOperatingLogs);
                return ResultUtil.getResult(RespCode.Code.SUCCESS);
            } else {
                return ResultUtil.getResult(RespCode.Code.FAIL);
            }
        }catch (Exception e){
            //Cat.logError("修改银行卡启用状态失败",e);
            logger.info("修改银行卡启用状态失败"+e);
            return ResultUtil.getResult(RespCode.Code.FAIL);
        }

    }
    /**
     * 删除银行卡
     * @author : 杨文杰
     * @Creation Date ： 2018/6/4
     * @version 1.0.0
     * @param
     *
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result deleteBankcard(Bankcard bankcard,String ip,String name) {
        ////Cat.logEvent("BankcardServiceImpl","删除银行卡service,id=:" +bankcard.getId());
        logger.info("删除银行卡service,id=:" +bankcard.getId());
        //查询原有数据
        Bankcard bankcard1 = bankcardMapper.selectBankcardById(bankcard.getId());
        try {
            int i = bankcardMapper.deleteBankcard(bankcard);
            if (i > 0) {
                //记录操作日志
                LoginOperatingLogs loginOperatingLogs = new LoginOperatingLogs();
                loginOperatingLogs.setOperationLogs("删除银行卡:{银行卡号=" +bankcard1.getBankcardNum()+"}");
                loginOperatingLogs.setCreatedIp(ip);
                loginOperatingLogs.setCreateUser(name);
                bankcardMapper.insertLog(loginOperatingLogs);
                return ResultUtil.getResult(RespCode.Code.SUCCESS);
            } else {
                return ResultUtil.getResult(RespCode.Code.FAIL);
            }
        }catch (Exception e){
            //Cat.logError("删除银行卡失败",e);
            logger.info("删除银行卡失败"+e);
            return ResultUtil.getResult(RespCode.Code.FAIL);
        }
    }
}
