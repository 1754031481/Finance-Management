package com.zc.common.core.email.receive;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.zc.common.core.string.MyStringUtils;

/**
 * 
 * 
 * @author 张靠勤
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2013-6-23 下午6:09:01
 * 
 */
public class EmailParse {
	/**
	 * @description ：/ 接收时间
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:54
	 * @version
	 * @param :
	 */
	private String receiveTime;
	/**
	 * @description ：/ 发送人
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:54
	 * @version
	 * @param :
	 */
	private String sendUser;
	/**
	 * @description ：/ 接收人
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:54
	 * @version
	 * @param :
	 */
	private String receiveUser;
	/**
	 * @description ：/ 抄送的人
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:54
	 * @version
	 * @param :
	 */
	private String cc;
	/**
	 * @description ：/ Message-ID
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:54
	 * @version
	 * @param :
	 */
	private String messageId;
	/**
	 * @description ：/ 主题
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:54
	 * @version
	 * @param :
	 */
	private String subject;
	/**
	 * @description ：/ 内容
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:54
	 * @version
	 * @param :
	 */
	private String content;
	/**
	 * @description ：/ 是否有附件
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:54
	 * @version
	 * @param :
	 */
	private boolean hasAttchment = false;
	/**
	 * @description ：/ 附件文件名称
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:54
	 * @version
	 * @param :
	 */
	private String[] fileName;
	/**
	 * @description ：是否已读
	 * @Created by  : Moya
	 * @Creation Date ： 2018/1/25 17:54
	 * @version
	 * @param :
	 */
	private boolean isReader;
	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getSendUser() {
		return MyStringUtils.getMiddleString(sendUser, "<", ">");
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public String getReceiveUser() {
		return MyStringUtils.getMiddleString(receiveUser, "<", ">");
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isHasAttchment() {
		return hasAttchment;
	}

	public void setHasAttchment(boolean hasAttchment) {
		this.hasAttchment = hasAttchment;
	}

	public String[] getFileName() {
		return fileName;
	}

	public void setFileName(String[] fileName) {
		this.fileName = fileName;
	}

	public boolean isReader() {
		return isReader;
	}

	public void setReader(boolean isReader) {
		this.isReader = isReader;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
