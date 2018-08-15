package com.zc.dao.xsconfig;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.xsconfig.XSPayConfig;
import com.zc.vo.SystemFromVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author 杨文杰
 * @Title: XsPayMapper
 * @Description: 新生支付配置Mapper
 * @date 2018/4/11
 */
@MyBatisRepository
@Component
public interface XSPayMapper extends BaseMapper<XSPayConfig> {

    /**
     * 获取配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/11
     * @version 1.0.0
     * @param
     *
     */
    public List<XSPayConfig> getXsPayList();

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    public XSPayConfig getXsPayById(Long id);

    /**
     * 修改配置信息
     * @author : 杨文杰
     * @Creation Date ： 2018/4/11
     * @version 1.0.0
     * @param
     *
     */
    void updateXsPay(XSPayConfig XSPayConfig);

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
