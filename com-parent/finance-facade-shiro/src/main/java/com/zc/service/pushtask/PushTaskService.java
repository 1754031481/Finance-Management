package com.zc.service.pushtask;


import com.zc.entity.push.PushTask;

/**
 * @package : com.zc.service.pushtask
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日18:42
 */
public interface PushTaskService {

    PushTask findPayEntity(String merSeqId);
}
