package com.zc.dao.pushupload;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.paycontext.PayContext;
import com.zc.entity.push.PushTask;
import org.springframework.stereotype.Component;

/**
 * Created by jx on 2018/4/17.
 */
@MyBatisRepository
@Component
public interface PushUploadMapper {

    /**
     * 获取推送地址
     * @author : 杨文杰
     * @Creation Date ： 2018/4/17
     * @version 1.0.0
     * @param
     *
     */
    String getPushUrl(String businessnumber);

    CashReceiveStation getCashReceiveStationByMerSeqId(String businessnumber);


    int findPayEntity(String businessnumber);

    int getPayEntity(String businessnumber);

    /**
     * 保存支付记录
     * @author : 杨文杰
     * @Creation Date ： 2018/4/17
     * @version 1.0.0
     * @param
     *
     */
    void savePayContext(PayContext payContext);

    /**
     * 删除提现记录
     * @author : 杨文杰
     * @Creation Date ： 2018/4/17
     * @version 1.0.0
     * @param
     *
     */
    void deleteCashReceiveStation(Long id);

    /**
     * 修改提现记录状态
     * @author : 杨文杰
     * @Creation Date ： 2018/4/17
     * @version 1.0.0
     * @param
     *
     */
    void updateCashReceiveStationStatus(CashReceiveStation crs);

    /**
     * 保存推送
     * @author : 杨文杰
     * @Creation Date ： 2018/4/17
     * @version 1.0.0
     * @param
     *
     */
    void savePushTask(PushTask pushTask);

    /**
     * 获取用户otp
     * @author : 杨文杰
     * @Creation Date ： 2018/4/17
     * @version 1.0.0
     * @param
     *
     */
    String getOtpByTel(String tel);
}
