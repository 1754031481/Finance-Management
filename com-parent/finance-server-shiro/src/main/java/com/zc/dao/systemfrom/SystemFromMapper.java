package com.zc.dao.systemfrom;

import com.zc.entity.systemfrom.SystemFrom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.dao
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月08日16:16
 */
public interface SystemFromMapper {

    List<SystemFrom> findSystemNameAndId();

    List<Map<String,Object>> getTakeOutSystemFromList(HashMap<String, Object> param);

    SystemFrom getSystemFromBySystemFromName(String systemFrom);

    List<Map<String,Object>> findSystemList();

    List<Map<String,Object>> getTakeOutSystemFromList2(HashMap<String, Object> param);
}
