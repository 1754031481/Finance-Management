package com.zc.service.impl.business;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.xml.XMLUtils;
import com.zc.common.util.result.HttpRequest;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.interfaceentity.InterfaceEntity;
import com.zc.service.business.BusinessService;
import com.zc.service.impl.cashreceivestation.CashReceiveStationAnysServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @package : com.zc.service.business
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月18日10:42
 */
@Service
@Transactional
public class BusinessServiceImpl implements BusinessService{

    private Logger logger = Logger.getLogger(BusinessServiceImpl.class);
    @Override
    public Result startBusinessInfo(Map<String, Object> initialize, CashReceiveStation crs) {
        String crsTaskAddress = crs.getCrsTaskAddress();//访问渠道作业地址
        if (null == crsTaskAddress || "".equals(crsTaskAddress)) {
            return ResultUtils.returnError("渠道路径异常");
        } else {
            int connect = HttpRequest.isConnect(crsTaskAddress);
            logger.info("请求渠道返回值"+connect+",,,访问渠道作业地址"+crsTaskAddress);
            if (connect != 200) {
                logger.info("发去代付请求渠道失败");
                return ResultUtils.returnError("渠道路径阻塞");
            }
        }
        String convertToXml = XMLUtils.convertToXml(crs);
        String sendPost = HttpRequest.sendPost(crsTaskAddress, convertToXml,null);
        JSONObject parseObject = JSONObject.parseObject(sendPost);
        return JSONObject.toJavaObject(parseObject, Result.class);
    }

    @Override
    public Result   startBusiness(Map<String, Object> initialize, CashReceiveStation crs) {
        InterfaceEntity interfaceEntity = (InterfaceEntity) initialize.get("interfaceEntity");

        String crsTaskAddress = interfaceEntity.getCrsTaskAddress();//访问渠道作业地址

        crs.setNotifyUrl(interfaceEntity.getAddress());//不分流获取渠道发起地址
        crs.setThirdPayType(interfaceEntity.getName());//不分流获取渠道名称

        if (null == crsTaskAddress || "".equals(crsTaskAddress)) {
            return ResultUtils.returnError("渠道路径异常");
        } else {
            int connect = HttpRequest.isConnect(crsTaskAddress);
            logger.info("请求渠道返回值"+connect+",,,访问渠道作业地址"+crsTaskAddress);
            if (connect != 200) {
                logger.info("发去代付请求渠道失败");
                return ResultUtils.returnError("渠道路径阻塞");
            }
        }
        String convertToXml = XMLUtils.convertToXml(crs);
        String sendPost = HttpRequest.sendPost(crsTaskAddress, convertToXml,null);
        JSONObject parseObject = JSONObject.parseObject(sendPost);
        return JSONObject.toJavaObject(parseObject, Result.class);
    }
}
