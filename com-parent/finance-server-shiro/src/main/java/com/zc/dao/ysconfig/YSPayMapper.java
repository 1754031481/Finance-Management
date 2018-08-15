package com.zc.dao.ysconfig;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.ysconfig.YSPayConfig;
import com.zc.vo.SystemFromVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author 杨文杰
 * @Title: YsPayMapper
 * @Description: 银盛支付配置Mapper
 * @date 2018/4/11
 */
@MyBatisRepository
@Component
public interface YSPayMapper extends BaseMapper<YSPayConfig> {

    /**
     * 获取配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/11
     * @version 1.0.0
     * @param
     *
     */
    public List<YSPayConfig> getYsPayList();

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    public YSPayConfig getYsPayById(Long id);

    /**
     * 修改配置信息
     * @author : 杨文杰
     * @Creation Date ： 2018/4/11
     * @version 1.0.0
     * @param
     *
     */
    void updateYsPay(YSPayConfig YSPayConfig);

    /**
     * 获取支持项目下拉框
     * @author : 杨文杰
     * @Creation Date ： 2018/4/11
     * @version 1.0.0
     * @param
     *
     */
    List<SystemFromVO> getSelect();
}
