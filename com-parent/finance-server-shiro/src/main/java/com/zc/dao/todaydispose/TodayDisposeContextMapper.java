package com.zc.dao.todaydispose;

import com.zc.entity.paycontext.PayContext;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: shiyunshun
 * @date: 2018-06-05 18:47
 * @version: 1.0.0
 */
public interface TodayDisposeContextMapper extends BaseMapper<PayContext> {

    /**
     * @return
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 列表查询
     */
    List<Map<String, Object>> findOrderList(PayContext payContext);

    /**
     * 获取业务来源
     * @param
     * @return
     */
    List<String> getChannelSel();

    /**
     * 根据id列表批量查询
     * @param chk_value
     * @return
     */
    List<PayContext>  getBatchQuery(@Param("dramaIds") String[] chk_value);

    /**
     * 批量添加
     * @param payContextListk
     * @return
     */
    Integer  putBatch(@Param("payContextListk") List<PayContext> payContextListk);
    /**
     * @return
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 获取当前页面
     */
    String[] selectId(PayContext payContext);




}
