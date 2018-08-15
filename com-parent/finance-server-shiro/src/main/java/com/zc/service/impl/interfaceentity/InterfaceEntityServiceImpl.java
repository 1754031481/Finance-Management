package com.zc.service.impl.interfaceentity;

import com.alibaba.dubbo.config.annotation.Service;
import com.zc.dao.interfaceentity.InterFaceEntityMapper;
import com.zc.entity.interfaceentity.InterfaceEntity;
import com.zc.service.interfaceentity.InterfaceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @package : com.zc.service.impl.interfaceentity
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日17:16
 */
@Service
@Transactional
public class InterfaceEntityServiceImpl implements InterfaceEntityService {
    @Autowired
    private InterFaceEntityMapper interFaceEntityMapper;

    @Override
    public InterfaceEntity getInterfaceBythirdPayType(String cashName) {
        InterfaceEntity findOne = null;
        try {
            findOne = interFaceEntityMapper.getInterfaceBythirdPayType(cashName);
        } catch (Exception e) {
            return null;
        }
        return findOne;
    }
}
