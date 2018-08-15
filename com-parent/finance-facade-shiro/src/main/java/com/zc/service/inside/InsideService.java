package com.zc.service.inside;

import com.zc.common.core.result.Result;

/**
 * @package : com.zc.service.inside
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日15:15
 */
public interface InsideService {

    Result launchBusiness(String systemFrom, String name);

    Result sendFailPush(String systemName);
}
