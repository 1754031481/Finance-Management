package com.zc.common.core.easyuiweb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;


/**
 * 
 * @Title: EasyuiWebResult.java
 * @Description: jqueryeasyui返回值    用于接口调用
 *  参数：cotent替换之前封装的rows
 * @author 陈振兵
 * @e-mail chenzhenbing@139.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年5月18日 下午2:32:19
 * Copyright © 2013 厦门卓讯信息技术有限公司 All rights reserved.
 *
 */
public class EasyuiWebResult<T> {
	/**
	 * @description ：/ 总数
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:53
	 * @version
	 * @param :
	 */
	private Long total;
	/**
	 * @description ：/ 类型
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:53
	 * @version
	 * @param :
	 */
	@JsonProperty("content")
	private T t;
	/**
	 * @description ：/ 底部统计
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:53
	 * @version
	 * @param :
	 */
	@JsonUnwrapped
	private Object footer;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public Object getFooter() {
		return footer;
	}

	public void setFooter(Object footer) {
		this.footer = footer;
	}

}
