package com.zc.service.systemfrom;

import com.zc.common.core.result.Result;
import com.zc.entity.systemfrom.SystemFrom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.systemfrom
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月08日16:12
 */
public interface SystemFromService {

    List<SystemFrom> findSystemNameAndId();

    SystemFrom findSystemName(String name);

    List<Map<String,Object>> getTakeOutSystemFromList(HashMap<String, Object> param);

    SystemFrom getSystemFromBySystemFromName(String systemFrom);

    Result sendFailCash(String systemName);

    Map<String,Object> findSystemList();

    List<Map<String,Object>> getTakeOutSystemFromList2(HashMap<String, Object> param);
}
