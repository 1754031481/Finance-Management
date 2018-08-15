package com.zc.service.configuration;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.configuration.CITICQRcode;
import com.zc.vo.configuration.CITICQRcodeVO;

import java.util.HashMap;

/**
 * @author: 王楠
 * @version:
 * @Description: 中信服务层
 **/
public interface CITICService {


    /**
     * @author 王楠
     * @version
     * @Description: 获取中信扫码渠道列表
     * @return 中信扫码渠道列表
     * @exception
     **/
    HashMap<String,Object> getQRcodeList(PageBean page);


    /**
     * @author 王楠
     * @version
     * @Description: 添加中信扫码渠道
     * @return Result
     * @exception
     **/
    Result addQRcode(CITICQRcode citicqRcode);

    /**
     * @author 王楠
     * @version
     * @Description: 修改中信扫码渠道
     * @return Result
     * @exception
     **/
    Result updateQRcode(CITICQRcode citicqRcode) throws Exception;

}
