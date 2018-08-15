package com.zc.service.paycontext;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.paycontext.PayContext;
import com.zc.vo.PayContextVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.paycontext
 * @progect : Finance-Management
 * @Description : 订单查询service接口层
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月24日9:37
 */
public interface PayContextService {
    Map<String,Object> findOrderList(PageBean pageBean,PayContextVO payContext);

    Result getChannelSel();

    List<PayContext> getPayContentByIds(@Param("ids") String[] ids);
}
