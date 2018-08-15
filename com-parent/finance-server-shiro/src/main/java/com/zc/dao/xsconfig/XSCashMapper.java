package com.zc.dao.xsconfig;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.xsconfig.XSCashConfig;
import com.zc.vo.SystemFromVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author 杨文杰
 * @Title: XsCashMapper
 * @Description: 新生代付配置Mapper
 * @date 2018/4/11
 */
@MyBatisRepository
@Component
public interface XSCashMapper extends BaseMapper<XSCashConfig> {

    /**
     * 获取配置页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/11
     * @version 1.0.0
     * @param
     *
     */
    public List<XSCashConfig> getXsCashList();

    /**
     * 查看
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    public XSCashConfig getXsCashById(Long id);

    /**
     * 修改配置信息
     * @author : 杨文杰
     * @Creation Date ： 2018/4/11
     * @version 1.0.0
     * @param
     *
     */
    void updateXsCash(XSCashConfig XSCashConfig);

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
