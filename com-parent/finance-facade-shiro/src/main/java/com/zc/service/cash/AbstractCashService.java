package com.zc.service.cash;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.zc.common.core.exception.PAYException;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.CalendarUtil;
import com.zc.common.core.utils.CodeConstExt;
import com.zc.common.core.utils.StrShyUtil;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.checklog.CheckLog;
import com.zc.entity.finance.CommonPayConfig;
import com.zc.entity.interfaceentity.InterfaceEntity;
import com.zc.entity.paycontext.PayContext;
import com.zc.entity.systemfrom.SystemFrom;
import com.zc.service.cashreceivestationany.CashReceiveStationAnysService;
import com.zc.service.checklog.CheckLogService;
import com.zc.service.initialize.InitializeService;
import com.zc.service.paymentmessage.PaymentMessageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author:  ZhaoJunBiao
 * @date:  2018-04-18 10:24
 * @version: 1.0.0
 */
public abstract class AbstractCashService {

	private static Log logger = LogFactory.getLog(AbstractCashService.class);

	@Autowired
	private PaymentMessageService paymentMessageService;
	@Autowired
	private CashReceiveStationAnysService cashReceiveStationService;
	@Autowired
	private InitializeService initializeService;
	@Autowired
	private CheckLogService checkLogService;


	/**
	 * @description: 批量处理代付数据
	 * @author:  ZhaoJunBiao
	 * @date:  2018-04-26 23:22
	 * @version: 1.0.0
	 * @param crs
	 * @param initialize
	 * @return
	 */
	protected final Result startPayBatch(CashReceiveStation crs, Map<String, Object> initialize) {
		Result result = new Result();
		String name = initialize.get("useInfo") + "";
		if (!CodeConstExt.ADMIN.equals(name)) {
			PayContext p  = paymentMessageService.getPayContextByOrderNum(crs.getMerSeqId());
			if (null!=p) {
				logger.info("T+1提现时,数据重发,订单号:" + crs.getMerSeqId());
				result.setCode(CodeConstExt.FAIL_CODE);
				result.setMsg("该订单号已存在.");
				return result;
			}
		}
		String thirdPayType=null;
		SystemFrom s = (SystemFrom) initialize.get("systemFrom");
		if ("true".equals(s.getIsShuntingCash())) {// 分流
			result = startBusinessInfo(initialize, crs);
			thirdPayType = crs.getThirdPayType();//访问渠道作业地址

		} else {
			result = startBusiness(initialize, crs);
			InterfaceEntity interfaceEntity = (InterfaceEntity) initialize.get("interfaceEntity");
			thirdPayType = interfaceEntity.getName();//访问渠道作业地址
		}

    		String status = "1";

		if (null != result) {
			logger.info("代付渠道保存作业记录结果:code=" + result.getCode() + ",msg=" + result.getMsg().toLowerCase());

			if (result.getCode() == 0 && "success".equals(result.getMsg().toLowerCase())) {

				crs.setStatus(4);

			} else if (result.getCode() == 1 && "fail".equals(result.getMsg().toLowerCase())) {

				status="3";
				crs.setStatus(3);

			} else {
				crs.setStatus(4);
			}
		} else {
			crs.setStatus(4);
		}


		try {
			CheckLog checkLog = checkLogService.entityByNumThirdPayType(crs.getMerSeqId(), thirdPayType);
			if (checkLog==null){
				Integer type = 1;
				//保存发起轨迹
				saveSendInfo(initialize,thirdPayType,crs,type,status);
			}else {
				logger.info("checkLog不等于null，订单号："+crs.getMerSeqId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("保存发起轨迹出现异常");
		}

		cashReceiveStationService.saveAndModify(crs);
		return result;
	}

	protected abstract Result startBusiness(Map<String, Object> initialize, CashReceiveStation crs);

	protected abstract Result startBusinessInfo(Map<String, Object> initialize, CashReceiveStation crs);

	private void saveSendInfo(Map<String, Object> initialize, String thirdPayType, CashReceiveStation crs,Integer type,String status) {
		checkLogService.saveInfo(initialize,thirdPayType,crs,type,status);


	}

}