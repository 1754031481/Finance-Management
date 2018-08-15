package com.zc.common.util.core;

/**
 * 返回抽象对象
 *
 * @author 郑朋
 * @create 2017/4/25
 **/
public interface RespCode {
	/**
	 * 返回消息
	 * @return
	 */
    String getMsg();

	/**
	 * 返回状态码
	 * @return
	 */
	String getCode();

    enum Code implements RespCode {
    	/**状态码*/
        SUCCESS("200","操作成功"),
        FAIL("300","操作失败"),

		;


		private String code;
        private String msg;

        Code(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public String getMsg() {
            return msg;
        }

        @Override
        public String getCode() {
            return code;
        } 
    }

}
