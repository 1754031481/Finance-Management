package com.zc.dao.paycontext;

import com.zc.entity.paycontext.PayContext;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ZhaoJunBiao
 * @date: 2018-04-13 18:47
 * @version: 1.0.0
 */
public interface PayContextMapper extends BaseMapper<PayContext> {


    PayContext getPayContextWhyOrderNum(String merSeqId);

    List<Map<String, Object>> findOrderList(PayContext payContext);

    List<Map<String,Object>> projectShunteDataShow(@Param("systemFromName") String systemFromName,@Param("date")String date);

    List<String> getChannelSel();

    String[] findSelectId(PayContext payContext);

    List<PayContext> getPayContextByIds(@Param("ids") String[] ids);
}
