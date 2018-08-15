package com.zc.dao.configuration;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.configuration.YFCashConfig;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author: 王楠
 * @version:
 * @Description: 裕福代付配置DAO
 **/
@MyBatisRepository
@Component
public interface YFcashConfigMapper extends BaseMapper<YFCashConfig> {

    /**
     * @return 裕福渠道代付配置列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取裕福渠道代付配置列表
     **/
    List<YFCashConfig> getList();

    /**
     * @param yfCashConfig 修改信息
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改裕福渠道代付配置
     **/
    void update(YFCashConfig yfCashConfig);
}
