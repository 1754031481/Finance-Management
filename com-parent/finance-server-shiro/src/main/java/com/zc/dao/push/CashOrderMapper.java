package com.zc.dao.push;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.push.PushTask;
import com.zc.vo.push.CashSystemFromSelectDTO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author: 王楠
 * @version:
 * @Description: 关于推送代付订单DAO
 **/
@MyBatisRepository
@Component
public interface CashOrderMapper extends BaseMapper<PushTask> {

    /**
     * @return 代付订单列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取关于推送代付订单列表
     */
    List<PushTask> getList(PushTask pushTask);

    /**
     * @param pushTask 配置信息
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改关于推送代付订单
     */
    void update(PushTask pushTask);

    /**
     * @return 代付订单下拉列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取关于推送代付订单下拉列表
     */
    List<CashSystemFromSelectDTO> selectSystemFromName();
}
