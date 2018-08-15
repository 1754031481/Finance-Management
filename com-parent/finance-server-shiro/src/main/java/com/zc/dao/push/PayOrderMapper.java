package com.zc.dao.push;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.push.PushTaskPay;
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
public interface PayOrderMapper extends BaseMapper<PushTaskPay> {

    /**
     * @return 代付订单列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取关于推送代付订单列表
     */
    List<PushTaskPay> getList(PushTaskPay pushTaskPay);

    /**
     * @param pushTaskPay 配置信息
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改关于推送代付订单
     */
    void update(PushTaskPay pushTaskPay);

}
