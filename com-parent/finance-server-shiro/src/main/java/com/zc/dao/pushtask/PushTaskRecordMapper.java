package com.zc.dao.pushtask;

import com.zc.entity.pushtask.PushTaskRecord;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @package : com.zc.dao.pushtask
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月18日10:15
 */
public interface PushTaskRecordMapper extends BaseMapper<PushTaskRecord>{

    PushTaskRecord findPayEntity(String merSeqId);
}
