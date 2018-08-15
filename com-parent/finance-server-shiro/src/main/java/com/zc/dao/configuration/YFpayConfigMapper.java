package com.zc.dao.configuration;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.configuration.YFPayConfig;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author: 王楠
 * @version:
 * @Description: 裕福支付配置DAO
 **/
@MyBatisRepository
@Component
public interface YFpayConfigMapper extends BaseMapper<YFPayConfig> {


    /**
     * @return 裕福渠道支付配置列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取裕福渠道支付配置列表
     */
    List<YFPayConfig> getPayList();

    /**
     * @param yfPayConfig
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改裕福渠道支付配置
     */
    void updatePay(YFPayConfig yfPayConfig);
}
