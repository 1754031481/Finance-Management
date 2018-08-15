package com.zc.dao.wxpay;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.finance.WxPayConfig;
import com.zc.vo.SystemFromVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author 杨文杰
 * @Title: WxPayMapper
 * @Description: 微信配置Mapper
 * @date 2018/4/10
 */
@MyBatisRepository
@Component
public interface WxPayMapper extends BaseMapper<WxPayConfig> {

    /**
     * 获取配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/10
     * @version 1.0.0
     * @param
     *
     */
    public List<WxPayConfig> getWxPayList();

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    public WxPayConfig getWxPayById(Long id);

    /**
     * 修改配置信息
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    void updateWxPay(WxPayConfig wxPayConfig);

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
