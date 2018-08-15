package com.zc.common.core.utils;

import java.util.HashMap;
import java.util.Map;

public class CodeConstExt {
	/**
	 * 返回结果描述
	 */
	public static final String SUCCESS="0000";
	public static final Integer FAIL_CODE=1;//失败
	public static final Integer SUCCESS_CODE=0;//成功

	public static final String FINACE="finace";//财务标识
	public static final String ADMIN="admin";//超管标识
	

	public static   Map<String,String> tableMap = new HashMap<String, String>();
	static{
		tableMap.put("贵州银联代付","");
		tableMap.put("易联代付","yl_cash_config");
		tableMap.put("易联支付","yl_pay_config");
		tableMap.put("银生宝支付","ysb_pay_config");
		tableMap.put("银生宝代付","ysb_cash_config");
		tableMap.put("支付宝支付","al_pay_config");
		tableMap.put("商银信支付","syx_pay_config");
		tableMap.put("易军通商银信","syx_pay_config");
		tableMap.put("商银信代付","syx_cash_config");
		tableMap.put("宝付支付","");
		tableMap.put("宝付代付","");
		tableMap.put("微信支付","wx_pay_config");
		tableMap.put("衫德支付","");
		tableMap.put("衫德代付","");
		tableMap.put("快捷支付","");
		tableMap.put("浦发扫码支付","pf_bank_nativa");
		tableMap.put("双乾支付","sq_pay_config");
		tableMap.put("双乾代付","sq_cash_config");
		tableMap.put("传化支付","ch_pay_config");
		tableMap.put("传化代付","ch_cash_config");
		tableMap.put("联动优势代付","ldys_cash_config");
		tableMap.put("联动优势支付","ldys_pay_config");
		tableMap.put("银联快捷支付","ylkj_pay_config");
		tableMap.put("银联快捷代付","ylkj_cash_config");
		tableMap.put("新生支付","xs_pay_config");
		tableMap.put("丰付代付","ff_cash_config");
		tableMap.put("银盛支付","ys_pay_config");
		tableMap.put("银盛代付","ys_cash_config");
		tableMap.put("新生代付","xs_cash_config");
		tableMap.put("兴业支付","");
		tableMap.put("随行付支付","sxf_pay_config");
		tableMap.put("随行付代付","sxf_cash_config");
		tableMap.put("丰付支付","ff_pay_config");
		tableMap.put("酷宝支付","ff_pay_config");
		tableMap.put("酷宝代付","ff_cash_config");
		tableMap.put("裕福支付","yf_pay_config");
		tableMap.put("裕福代付","yf_cash_config");
		tableMap.put("W支付","w_pay_config");
		tableMap.put("W代付","w_cash_config");
		tableMap.put("中金支付","zj_pay_config");
		tableMap.put("先锋支付","ff_pay_config");
		tableMap.put("先锋代付","ff_cash_config");
		tableMap.put("中金代付","zj_cash_config");
		tableMap.put("汇付宝支付","ff_pay_config");
		tableMap.put("维基支付","wj_pay_config");
		tableMap.put("维基代付","wj_cash_config");
		tableMap.put("汇付宝代付","ff_cash_config");
	}
	

}
