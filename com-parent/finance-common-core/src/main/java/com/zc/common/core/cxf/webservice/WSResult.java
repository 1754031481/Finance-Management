package com.zc.common.core.cxf.webservice;

/**
 * 接口返回值定义类
 * 
 * @author 张靠勤
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2013-6-22 上午10:26:48
 * 
 */
public class WSResult<T> {
	/**
	 * @description ：/ 状态，1为成功，0为失败，其他自定义
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:57
	 * @version
	 * @param :
	 */
	private Integer code = 1;
	/**
	 * @description ：/ 状态说明信息
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:57
	 * @version
	 * @param :
	 */
	private String msg;
	/**
	 * @description ：/ 内容
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:57
	 * @version
	 * @param :
	 */
	private T t;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		t = t;
	}

}
