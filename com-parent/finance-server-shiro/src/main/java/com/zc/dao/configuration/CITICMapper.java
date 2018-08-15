package com.zc.dao.configuration;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.configuration.CITICQRcode;
import com.zc.vo.configuration.CITICQRcodeVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author: 王楠
 * @version:
 * @Description: 中信DAO层
 **/
@MyBatisRepository
@Component
public interface CITICMapper extends BaseMapper<CITICQRcode> {

    /**
     * @return 中信扫码渠道列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取中信扫码渠道列表
     */
    List<CITICQRcodeVO> getQRcodeList();

    /**
     * @param citicqRcode
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改中信扫码渠道
     */
    void updateQRcode(CITICQRcode citicqRcode);
}
