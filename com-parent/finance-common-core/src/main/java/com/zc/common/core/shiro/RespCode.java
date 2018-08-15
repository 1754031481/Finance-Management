package com.zc.common.core.shiro;

/**
 * 返回抽象对象
 *
 * @author 郑朋
 * @create 2017/4/25
 **/
public interface RespCode {
    String getMsg();
    String getCode();

    enum Code implements RespCode {
        //操作成功
        SUCCESS("200","操作成功"),
        //操作失败
        FAIL("300","操作失败"),
        //服务器内部错误
        INTERNAL_SERVER_ERROR("0405", "服务器内部错误"),
        //没有权限访问！
        PERMISSION_DENIED("10101","没有权限访问！"),
        //当前操作未处理完，请稍后再试！
        REPETITION("10102","当前操作未处理完，请稍后再试！"),
        //请求参数不全
        REQUEST_DATA_ERROR("10100","请求参数不全");
    	
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
