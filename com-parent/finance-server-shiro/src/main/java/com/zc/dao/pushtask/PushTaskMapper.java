package com.zc.dao.pushtask;

import com.zc.entity.push.PushTask;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @package : com.zc.dao.pushtask
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月18日10:07
 */
public interface PushTaskMapper extends BaseMapper<PushTask>{

    PushTask findPayEntity(String merSeqId);
}
