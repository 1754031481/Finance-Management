package com.zc.service.impl.initialize;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.zc.entity.finance.CommonPayConfig;
import com.zc.entity.interfaceentity.InterfaceEntity;
import com.zc.entity.systemfrom.SystemFrom;
import com.zc.entity.thirdpaytype.ThirdPayType;
import com.zc.service.finance.CommonPayService;
import com.zc.service.initialize.InitializeService;
import com.zc.service.interfaceentity.InterfaceEntityService;
import com.zc.service.systemfrom.SystemFromService;
import com.zc.service.thiredpaytype.ThiredPayTypeService;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: ZhaoJunBiao
 * @date: 2018-04-13 16:34
 * @version: 1.0.0
 */
@Service
@Transactional
public class InitializeServiceImpl  implements InitializeService{

    private Logger logger  = Logger.getLogger(InitializeServiceImpl.class);

    @Reference
    private SystemFromService systemFromService;
    @Reference
    private  InterfaceEntityService interfaceEntityService;
    @Reference
    private ThiredPayTypeService thiredPayTypeService;
    @Reference
    private CommonPayService  commonPayService;

    @Override
    public Map<String, Object> sendInitialize(String orderNum, String fromSystem, String cashName) {
        Map<String, Object> map = new HashMap<String, Object>();
        SystemFrom s = null;
        String cash = null;
        // 第三方接口初始化配置
        CommonPayConfig commonPayConfig = null;
        InterfaceEntity interfaceEntity = null;

        //初始化推送地址
        String aynUrl = "";
        // 从数据库获取配置信息 判断业务逻辑
        s = systemFromService.getSystemFromBySystemFromName(fromSystem);
        if (null == s) {// 判断是否支持项目
            logger.error("订单号:" + orderNum + ",提现操作时查询项目支持表不存在,返回Null");
            map.put("error", "当前项目未授权");
            return map;
        }
        map.put("systemFrom", s);

        if("true".equals(s.getIsShuntingCash())){
            return map;
        }else{
            cashName = s.getThirdPayTypeCash();
        }

        map.put("thirdPayType", cashName);
        interfaceEntity = interfaceEntityService.getInterfaceBythirdPayType(cashName);
        if (null == interfaceEntity) {
            logger.error("订单号:" + orderNum + ",提现操作时查询接口支持表不存在,返回Null");
            map.put("error", "未找到可用的接口");
            return map;
        }
        map.put("interfaceEntity", interfaceEntity);

        ThirdPayType t = thiredPayTypeService.getThirdPayTypeByThirdPayTypeIdAndSystemFromId(interfaceEntity.getId(), s.getId());
        map.put("thirdPayTypeEntity", t);
        if (null == t) {// 判断是否支持接口
            map.put("error", "当前项目未激活接口");
            return map;
        } else {
            // 开始读取参数
            Long payConfigId = t.getPayConfigId();
            Long commonPayConfigId = t.getCommonPayConfigId();
            if (null == payConfigId || null == commonPayConfigId) {// 判断是否有支持配置
                map.put("error", "未找到使用的配置文件");
                return map;
            } else {
                commonPayConfig = commonPayService.getCommonPayOne(commonPayConfigId);// 公共配置
                if (null == commonPayConfig) {// 判断是否支持项目
                    map.put("error", "未找到通用配置文件");
                    return map;
                }
                aynUrl = s.getSystemFromAynUrl()+commonPayConfig.getAynUrl();
                map.put("commonPayConfig", commonPayConfig);
                map.put("aynUrl", aynUrl);
            }
        }
        return map;
    }
}
