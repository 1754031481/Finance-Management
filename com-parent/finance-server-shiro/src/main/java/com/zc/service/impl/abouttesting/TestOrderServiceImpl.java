package com.zc.service.impl.abouttesting;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.abouttesting.TestOrderLogsMapper;
import com.zc.dao.abouttesting.TestOrderMapper;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.logs.TestOrderLogs;
import com.zc.service.abouttesting.TestOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @package : com.zc.service.abouttesting
 * @progect : Finance-Management
 * @Description :
 * @Author by :王楠
 * @Creation Date ：2018年06月04日10:42
 */
@Service
public class TestOrderServiceImpl implements TestOrderService {

    Logger logger = LoggerFactory.getLogger(TestOrderServiceImpl.class);

    @Autowired
    private TestOrderMapper testOrderMapper;

    @Autowired
    private TestOrderLogsMapper testOrderLogsMapper;

    /**
     * @param page
     * @return HashMap
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取测试订单列表
     */
    @Override
    public HashMap<String, Object> getTestOrderList(PageBean page) {
        logger.info("====================================分页获取测试订单列表服务入参："+JSONObject.toJSONString(page));
        HashMap<String,Object> map=new HashMap<>();
        try {

            //查询所有测试银行卡号
            List<String> testBankCards = testOrderMapper.selectTestBankCard();
            logger.info("-------------------------数据库查询所有测试银行卡返回数据："+JSONObject.toJSONString(testBankCards));
            if (testBankCards == null || testBankCards.size() <= 0){
                map.put("msg","没有要查询的银行卡号");
                map.put("code",200);
                map.put("content",new ArrayList<CashReceiveStation>());
                map.put("count",0);
                return map;
            }


            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<CashReceiveStation> list = testOrderMapper.getTestOrderList(testBankCards);
            logger.info("---------------------------数据库查询测试订单列表返回数据："+JSONObject.toJSONString(list));
            PageInfo<CashReceiveStation> info = new PageInfo<>(list);
            map.put("code",200);
            map.put("msg","操作成功");
            map.put("content",info.getList());
            map.put("count",info.getTotal());
            logger.info("===============================分页获取测试订单列表服务返回："+JSONObject.toJSONString(map));
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","服务器内部异常");
            logger.error("===============================分页获取测试订单列表服务异常====================================");
        }
        return map;
    }

    /**
     * @return bankNums 测试银行卡列表
     *         channels 第三方渠道列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取测试银行卡和第三方渠道列表
     **/
    @Override
    public HashMap<String, Object> getBankNumAndChannelList() {
        logger.info("====================================获取测试银行卡和第三方渠道列表服务开始====================================");
        HashMap<String,Object> map=new HashMap<>();
        try {

            //查询所有测试银行卡号
            List<String> testBankCards = testOrderMapper.selectTestBankCard();
            logger.info("-------------------------数据库查询所有测试银行卡返回数据："+JSONObject.toJSONString(testBankCards));


            //查询所有代付渠道
            List<String> cashChannels =testOrderMapper.selectCashChannelList();
            logger.info("---------------------------数据库查询所有代付渠道返回数据："+JSONObject.toJSONString(testBankCards));

            map.put("bankNums",testBankCards);
            map.put("channels",cashChannels);
            logger.info("===============================获取测试银行卡和第三方渠道列表服务返回："+JSONObject.toJSONString(map));
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","服务器内部异常");
            logger.error("===============================获取测试银行卡和第三方渠道列表服务异常====================================");
        }
        return map;
    }

    /**
     * @param newCashReceiveStation
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改测试订单
     */
    @Override
    @Transactional
    public Result updateTestOrder(CashReceiveStation newCashReceiveStation,String userName){
        logger.info("==========================修改测试订单服务参数 newCashReceiveStation:"+JSONObject.toJSONString(newCashReceiveStation)+"userName:"+JSONObject.toJSONString(userName));

        //查询修改前测试订单
        CashReceiveStation oldCashReceiveStation = testOrderMapper.getTestOrderById(newCashReceiveStation);

        if (oldCashReceiveStation == null){
            return ResultUtils.returnError("该订单已不存在");
        }

        logger.info("---------------------------------数据库查询原测试订单数据："+JSONObject.toJSONString(oldCashReceiveStation));
        TestOrderLogs testOrderLogs = new TestOrderLogs();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        testOrderLogs.setCreateUser(userName);
        testOrderLogs.setCreatedTime(new Date());
        testOrderLogs.setUpdateTime(new Date());
        testOrderLogs.setMerSeqId(oldCashReceiveStation.getMerSeqId());
        testOrderLogs.setCardNo("原:"+oldCashReceiveStation.getCardNo()+"\r\n现:"+newCashReceiveStation.getCardNo());
        testOrderLogs.setBankSub("原:"+oldCashReceiveStation.getBankSub()+"\r\n现:"+newCashReceiveStation.getBankSub());
        testOrderLogs.setComments("原:"+oldCashReceiveStation.getComments()+"\r\n现:"+newCashReceiveStation.getComments());
        testOrderLogs.setCreateTimeLog("原:"+formatter.format(oldCashReceiveStation.getCreatedTime())+"\r\n现:"+formatter.format(newCashReceiveStation.getCreatedTime()));
        testOrderLogs.setPersonName("原:"+oldCashReceiveStation.getPersonName()+"\r\n现:"+newCashReceiveStation.getPersonName());
        testOrderLogs.setThirdPayType("原:"+oldCashReceiveStation.getThirdPayType()+"\r\n现:"+newCashReceiveStation.getThirdPayType());

        //添加修改日志
        testOrderLogsMapper.insert(testOrderLogs);

        //修改测试订单
        testOrderMapper.updateTestOrder(newCashReceiveStation);

        return ResultUtils.returnSuccess("返回数据成功");
    }

    /**
     * @param testOrderLogs 条件查询过滤信息
     * @param page 分页信息
     * @return result
     * @throws
     * @author 王楠
     * @version
     * @Description: 条件查询测试订单日志数据(分页)
     **/
    @Override
    public HashMap<String, Object> getTestOrderLogs(TestOrderLogs testOrderLogs, PageBean page) {
        logger.info("===================================条件查询测试订单日志数据列表服务开始 testOrderLogs："+JSONObject.toJSONString(testOrderLogs)+"page:"+JSONObject.toJSONString(page));
        HashMap<String,Object> map=new HashMap<>();
        try {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<TestOrderLogs> list = testOrderLogsMapper.getTestOrderLogs(testOrderLogs);
            logger.info("---------------------------数据库查询测试订单日志列表返回数据："+JSONObject.toJSONString(list));
            PageInfo<TestOrderLogs> info = new PageInfo<>(list);
            map.put("code",200);
            map.put("msg","操作成功");
            map.put("content",info.getList());
            map.put("count",info.getTotal());
            logger.info("===============================分页获取测试订单日志列表服务返回："+JSONObject.toJSONString(map));
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","服务器内部异常");
            logger.error("===============================条件查询测试订单日志数据列表服务异常====================================");
        }
        return map;
    }


}
