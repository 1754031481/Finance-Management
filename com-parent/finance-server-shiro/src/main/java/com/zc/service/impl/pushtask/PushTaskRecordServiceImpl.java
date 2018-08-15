package com.zc.service.impl.pushtask;

import com.alibaba.dubbo.config.annotation.Service;
import com.zc.dao.pushtask.PushTaskRecordMapper;
import com.zc.entity.pushtask.PushTaskRecord;
import com.zc.service.pushtask.PushTaskRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @package : com.zc.service.impl.pushtask
 * @progect : Finance-Management
 * @Description : 推送记录表
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月18日10:05
 */
@Service
@Transactional
public class PushTaskRecordServiceImpl implements PushTaskRecordService {


    @Autowired
    private PushTaskRecordMapper pushTaskRecordMapper;

    @Override
    public PushTaskRecord findPayEntity(String merSeqId) {
        return pushTaskRecordMapper.findPayEntity(merSeqId);
    }
}
