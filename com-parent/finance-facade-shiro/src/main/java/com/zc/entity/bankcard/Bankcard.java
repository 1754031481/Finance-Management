package com.zc.entity.bankcard;

import com.zc.common.core.orm.hibernate.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 杨文杰
 * @Title: Bankcard
 * @Description: 测试银行卡实体
 * @date 2018/6？4
 */
@Table(name = "test_bank_card")
public class Bankcard extends BaseIdEntity  {

    /**
     * 银行卡号
     */
    @Column(name = "bankcard_num")
    private String bankcardNum;

    /**
     * 所属银行
     */
    @Column(name = "bank_name")
    private String bankName;

    /**
     * 持卡人姓名
     */
    @Column(name = "bankcard_user")
    private String bankcardUser;

    /**
     * 是否启用
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    public String getBankcardNum() {
        return bankcardNum;
    }

    public void setBankcardNum(String bankcardNum) {
        this.bankcardNum = bankcardNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankcardUser() {
        return bankcardUser;
    }

    public void setBankcardUser(String bankcardUser) {
        this.bankcardUser = bankcardUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Bankcard{");
        sb.append("id=").append(id);
        sb.append(",bankcardNum='").append(bankcardNum).append('\'');
        sb.append(", bankName='").append(bankName).append('\'');
        sb.append(", bankcardUser='").append(bankcardUser).append('\'');
        sb.append(", status=").append(status);
        sb.append(", isDelete=").append(isDelete);
        sb.append('}');
        return sb.toString();
    }
}
