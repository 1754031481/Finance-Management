package com.zc.dao.cashreceivestation;

import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.logs.LoginOperatingLogs;
import com.zc.vo.SystemFromCashShuntingVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author:  shiyunshun
 * @date:  2018-06-08 15:31
 * @version: 1.0.0
 */
public interface CashreceivestationMapper extends BaseMapper<CashReceiveStation>{

    List<Map<String,Object>> findBusinessChannelsList(String date);

    List<Map<String,Object>> findCashReceiveStation(Map paramMap);

    List<CashReceiveStation> getCrsInfosByStatus(@Param("systemName") String systemName, @Param("status")Integer status);

    void updateCrsStatus(CashReceiveStation crs);

    Integer  cashReceiveStationUpdate(CashReceiveStation cashReceiveStation);

    List<SystemFromCashShuntingVO> getCashShuntingList(String systemFromName);

    List<String> getCashChannelShunting();

    SystemFromCashShuntingVO getCashShuntingBySytemAndChannel(Map<String,String> paramMap);

    SystemFromCashShuntingVO getCashShuntingBySytemAndChannelLimit(Map<String,String> paramMap);

    void updateCashShuntingBySytemAndChannelLimit(Map<String,String> paramMap);

    List<Map<String,String>> getReShuntingList();

    List<SystemFromCashShuntingVO> getReCashShuntingList(String systemFromName);

    SystemFromCashShuntingVO getReCashShuntingBySytemAndChannel(Map<String,String> paramMap);

    SystemFromCashShuntingVO getReCashShuntingBySytemAndChannelLimit(Map<String,String> paramMap);

    void updateReCashShuntingBySytemAndChannelLimit(Map<String,String> paramMap);

    CashReceiveStation   getReceiveStationById(Long id);

    void insertLog(LoginOperatingLogs loginOperatingLogs);

    Map<String,String> getCrsAddress(String toChannel);

    List<Map<String,Object>> getfromSystemList();
}
