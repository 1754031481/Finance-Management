package com.zc.service.configuration;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.configuration.SPDQRcode;

import java.util.HashMap;

/**
 * @author: 王楠
 * @version:
 * @Description: 浦发服务层
 **/
public interface SPDService {

    /**
     * @author 王楠
     * @version
     * @Description: 获取扫码渠道列表
     * @return HashMap
     * @exception
     **/
    HashMap<String,Object> getQRcodeList(PageBean page);


    /**
     * @author 王楠
     * @version
     * @Description: 添加扫码渠道
     * @return Result
     * @exception
     **/
    Result addQRcode(SPDQRcode spdqRcode);


    /**
     * @author 王楠
     * @version
     * @Description: 修改扫码渠道
     * @return Result
     * @exception
     **/
    Result updateQRcode(SPDQRcode spdqRcode);
}
