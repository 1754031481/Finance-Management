package com.zc.service.initialize;

import java.util.Map;

/**
 * @description:
 * @author: ZhaoJunBiao
 * @date: 2018-04-13 16:34
 * @version: 1.0.0
 */
public interface InitializeService {

    Map<String, Object> sendInitialize(String merSeqId, String fromSystem, String cashName);
}
