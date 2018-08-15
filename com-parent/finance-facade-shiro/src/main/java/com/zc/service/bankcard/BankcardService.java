package com.zc.service.bankcard;

import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.result.Result;
import com.zc.entity.bankcard.Bankcard;
import com.zc.entity.finance.AlPayConfig;

/**
 * @author 杨文杰
 * @Title: BankcardService
 * @Description: 测试银行卡
 * @date 2018/6/4
 */
public interface BankcardService {

    /**
     * 分页获取测试银行卡页面
     * @author : 杨文杰
     * @Creation Date ： 2018/6/4
     * @version 1.0.0
     * @param
     *
     */
    Result getList(PageBean pageBean);

    /**
     * 添加银行卡
     * @author : 杨文杰
     * @Creation Date ： 2018/6/4
     * @version 1.0.0
     * @param
     *
     */
    Result addBankcard(Bankcard bankcard,String ip,String name);

    /**
     * 修改银行卡启用状态
     * @author : 杨文杰
     * @Creation Date ： 2018/6/4
     * @version 1.0.0
     * @param
     *
     */
    Result updateStatus(Bankcard bankcard,String ip,String name);

    /**
     * 删除银行卡
     * @author : 杨文杰
     * @Creation Date ： 2018/6/4
     * @version 1.0.0
     * @param
     *
     */
    Result deleteBankcard(Bankcard bankcard,String ip,String name);
}
