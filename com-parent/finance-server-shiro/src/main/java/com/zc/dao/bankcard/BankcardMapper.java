package com.zc.dao.bankcard;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.bankcard.Bankcard;
import com.zc.entity.logs.LoginOperatingLogs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;


import java.util.List;

/**
 * @author 杨文杰
 * @Title: BankcardMapper
 * @Description: 测试银行卡Mapper
 * @date 2018/6/4
 */
@MyBatisRepository
@Component
public interface BankcardMapper extends BaseMapper<Bankcard> {

    /**
     * 获取测试银行卡页面
     * @author : 杨文杰
     * @Creation Date ： 2018/6/4
     * @version 1.0.0
     * @param
     *
     */
    public List<Bankcard> getBankcardList();

    int updateStatus(@Param("id")Long id, @Param("status") Integer status);

    int selectByBankcardNum(String bankcardNum);

    int deleteBankcard(Bankcard bankcard);

    void insertLog(LoginOperatingLogs loginOperatingLogs);

    void insertBankcard(Bankcard bankcard);

    Bankcard selectBankcardById(Long id);
}

