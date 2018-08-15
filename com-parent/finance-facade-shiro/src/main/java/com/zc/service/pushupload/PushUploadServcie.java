package com.zc.service.pushupload;

import com.zc.common.util.result.Result;

import java.util.Map;

/**
 * @author 杨文杰
 * @Title: PushUploadServcie
 * @Description: 订单推送
 * @date 2018/4/17
 */
public interface PushUploadServcie {


    Result getPushUrl(String businessnumber);

    void pushXlsInfo(Map<String, String> map, String pushUrl);

    Result getOtpByTel(String tel);
}
