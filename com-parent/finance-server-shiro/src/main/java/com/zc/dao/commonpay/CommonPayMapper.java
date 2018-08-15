package com.zc.dao.commonpay;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.finance.CommonPayConfig;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author 杨文杰
 * @Title: CommonPayMapper
 * @Description: 通用配置Mapper
 * @date 2018/4/8
 */
@MyBatisRepository
@Component
public interface CommonPayMapper extends BaseMapper<CommonPayConfig> {

    /**
     * 获取通用配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    public List<CommonPayConfig> getCommonPayList();

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    public CommonPayConfig getCommonPayById(Long id);

    /**
     * 修改配置信息
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    void updateCommonPay(CommonPayConfig commonPayConfig);


    void insertCommonPay(CommonPayConfig commonPayConfig);
}
