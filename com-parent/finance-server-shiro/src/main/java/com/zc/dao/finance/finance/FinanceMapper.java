package com.zc.dao.finance.finance;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.finance.Finance;
import com.zc.entity.finance.WxPayConfig;
import com.zc.vo.SystemFromVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @description:  financeMapper
 * @author:  ZhaoJunBiao
 * @date:  2018-04-13 11:37
 * @version: 1.0.0
 */
public interface FinanceMapper extends BaseMapper<Finance> {

    Integer findToDayTakeCount(Map<String, Object> param);
}
