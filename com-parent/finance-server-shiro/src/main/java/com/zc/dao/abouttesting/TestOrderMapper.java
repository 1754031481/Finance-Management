package com.zc.dao.abouttesting;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.logs.TestOrderLogs;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @package : com.zc.service.abouttesting
 * @progect : Finance-Management
 * @Description :
 * @Author by :王楠
 * @Creation Date ：2018年06月04日10:42
 */
@MyBatisRepository
@Component
public interface TestOrderMapper extends BaseMapper<CashReceiveStation> {


    /**
     * @param testBankCards 订单号
     * @return List<CashReceiveStation> 测试订单集合
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取测试订单列表
     */
    List<CashReceiveStation> getTestOrderList(List<String> testBankCards);

    /**
     * @return List<String> 测试银行卡号集合
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取测试银行卡列表
     */
    List<String> selectTestBankCard();

    /**
     * @return List<String> 代付渠道集合
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取所有代付渠道
     */
    List<String> selectCashChannelList();

    /**
     * @param cashReceiveStation
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改测试订单
     */
    void updateTestOrder(CashReceiveStation cashReceiveStation);

    /**
     * @return CashReceiveStation 原测试订单
     * @param cashReceiveStation
     * @throws
     * @author 王楠
     * @version
     * @Description: 查询原测试订单
     */
    CashReceiveStation getTestOrderById(CashReceiveStation cashReceiveStation);

}
