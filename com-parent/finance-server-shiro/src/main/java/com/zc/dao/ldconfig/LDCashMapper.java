package com.zc.dao.ldconfig;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.ldconfig.LDCashConfig;
import com.zc.vo.SystemFromVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author 杨文杰
 * @Title: LdPayMapper
 * @Description: 联动优势支付配置Mapper
 * @date 2018/4/11
 */
@MyBatisRepository
@Component
public interface LDCashMapper extends BaseMapper<LDCashConfig> {

    /**
     * 获取配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/11
     * @version 1.0.0
     * @param
     *
     */
    public List<LDCashConfig> getLdCashList();

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    public LDCashConfig getLdCashById(Long id);

    /**
     * 修改配置信息
     * @author : 杨文杰
     * @Creation Date ： 2018/4/11
     * @version 1.0.0
     * @param
     *
     */
    void updateLdCash(LDCashConfig ysPCashConfig);


}
