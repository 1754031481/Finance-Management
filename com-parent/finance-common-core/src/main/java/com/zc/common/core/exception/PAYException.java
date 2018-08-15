package com.zc.common.core.exception;

/**
 * @description:  自定义异常，该异常信息包含错误码，错误基本信息、错误详细信息
 * @author:  ZhaoJunBiao
 * @date:  2018-04-18 10:26
 * @version: 1.0.0
 */
public class PAYException extends Exception {

    private static final long serialVersionUID = 1L;

    public PAYException(Integer resultCode, String resultMessage, String errorDetail, Integer responseCode) {
        super("{"+"\""+"resultCode"+"\""+":"+"\""+resultCode+"\""+","+"\""+"resultMsg"+"\""+":"+
        		"\""+resultMessage+"\""+","+"\""+"errDetail"+"\""+":"+"\""+errorDetail+"\""+","+
        		"\""+"responseCode"+"\""+":"+"\""+responseCode+"\""+"}");
    }

    public PAYException(Integer resultCode, String resultMessage, String errorDetail) {
    	super("{"+"\""+"resultCode"+"\""+":"+"\""+resultCode+"\""+","+"\""+"resultMsg"+"\""+":"+
        		"\""+resultMessage+"\""+","+"\""+"errDetail"+"\""+":"+"\""+errorDetail+"\""+"}");
    }

    public PAYException(String msg) {
        super(msg);
    }
}
