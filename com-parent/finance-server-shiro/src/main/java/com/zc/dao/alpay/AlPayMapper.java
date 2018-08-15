package com.zc.dao.alpay;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.finance.AlPayConfig;
import com.zc.vo.SystemFromVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author 杨文杰
 * @Title: AlPayMapper
 * @Description: 支付宝配置Mapper
 * @date 2018/4/9
 */
@MyBatisRepository
@Component
public interface AlPayMapper extends BaseMapper<AlPayConfig> {

    /**
     * 获取支付宝配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    public List<AlPayConfig> getAlPayList();

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    public AlPayConfig getAlPayById(Long id);

    /**
     * 修改配置信息
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    void updateAlPay(AlPayConfig alPayConfig);

    /**
     * 获取支持项目下拉框
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    List<SystemFromVO> getSelect();
}
