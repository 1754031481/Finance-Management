package com.zc.dao.configuration;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.configuration.SPDQRcode;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author: 王楠
 * @version:
 * @Description: 浦发DAO层
 **/
@MyBatisRepository
@Component
public interface SPDMapper extends BaseMapper<SPDQRcode> {


    /**
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改扫码渠道
     */
    void updateQRcode(SPDQRcode spdqRcode);


    /**
     * @return List<SPDQRcode> 扫码渠道列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取扫码渠道列表
     */
    List<SPDQRcode> getQRcodeList();
}
