package com.zc.service.impl.pushtask;

import com.alibaba.dubbo.config.annotation.Service;
import com.zc.dao.pushtask.PushTaskMapper;
import com.zc.entity.push.PushTask;
import com.zc.service.pushtask.PushTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @package : com.zc.service.pushtask
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日18:42
 */
@Service
@Transactional
public class PushTaskServiceImpl implements PushTaskService{

    @Autowired
    private PushTaskMapper pushTaskMapper;

    @Override
    public PushTask findPayEntity(String merSeqId) {
        return pushTaskMapper.findPayEntity(merSeqId);
    }
}
