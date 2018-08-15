package com.zc.service.pushtask;

import com.zc.entity.pushtask.PushTaskRecord;

/**
 * @package : com.zc.service.pushtask
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月18日10:05
 */
public interface PushTaskRecordService {
    PushTaskRecord findPayEntity(String merSeqId);
}
