package com.zc.service.cashreceivestationany;

import com.zc.common.core.orm.BaseService;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.cashreceivestation.CashReceiveStation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.cashreceivestationany
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日16:11
 */
public interface CashReceiveStationAnysService {

    List<CashReceiveStation> getCashListBySystem(String systemFrom,Integer status);

    void batchCash(List<CashReceiveStation> list, Map<String, Object> initialize);

    void saveAndModify(CashReceiveStation crs);

    List<CashReceiveStation> getCrsInfosByStatus(String systemName, Integer  status);

    List<Map<String,Object>> projectShunteDataShow(String systemFrom);

    /**
     * @description: 项目代付数据分流配置列表
     * @author:  王楠
     * @date:  2018-04-19 13:55
     * @version: 1.0.0
     * @param systemFromName
     * @return
     */
    Map<String,List> getSystemCashShunting(String systemFromName);

    /**
     * @description: 修改项目分流配置
     * @author:  王楠
     * @date:  2018-04-19 13:55
     * @version: 1.0.0
     * @param paramMap  fromSystem 项目英文缩写
     *                  estimatedMoney 更换渠道的金额
     *                  fromChannel 被更改渠道
     *                  toChannel 目标渠道
     * @return
     */
    Result updateShuntSettings(Map<String,String> paramMap);


    /**
     * @description: 去重发渠道配额设置页面
     * @author:  王楠
     * @date:  2018-04-19 13:55
     * @version: 1.0.0
     * @return 页面路径
     */
    HashMap<String,Object> getReShuntingList(PageBean pageBean);

    /**
     * @description: 重发项目代付数据分流配置列表
     * @author:  王楠
     * @date:  2018-04-19 13:55
     * @version: 1.0.0
     * @param systemFromName
     * @return
     */
    Map<String,List> getReSystemCashShunting(String systemFromName);

    /**
     * @description: 修改项目重发分流配置
     * @author:  王楠
     * @date:  2018-04-19 13:55
     * @version: 1.0.0
     * @param paramMap  fromSystem 项目英文缩写
     *                  estimatedMoney 更换渠道的金额
     *                  fromChannel 被更改渠道
     *                  toChannel 目标渠道
     * @return
     */
    Result updateReShuntSettings(Map<String,String> paramMap);
}
