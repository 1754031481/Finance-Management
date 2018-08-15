package com.zc.service.cashreceivestationupdate;

import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.ffconfig.FFPayConfig;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.cashreceivestationupdate
 * @progect : Finance-Management
 * @Description :
 * @Author by :shiyunshun
 * @Creation Date ：2018年04月08日15:24
 */
public interface CashReceiveStationUpdateService {

    Map<String,Object>  getCashReceiveStation(PageBean pageBean ,Map paramMap);

    Map<String,Object> CashReceiveStationUpdate(CashReceiveStation cashReceiveStation,String ip,String name);

    List<Map<String,Object>> getfromSystemList();
}
