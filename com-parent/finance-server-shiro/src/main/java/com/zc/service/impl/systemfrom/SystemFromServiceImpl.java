package com.zc.service.impl.systemfrom;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.zc.common.core.result.Result;
import com.zc.dao.systemfrom.SystemFromMapper;
import com.zc.entity.systemfrom.SystemFrom;
import com.zc.service.inside.InsideService;
import com.zc.service.systemfrom.SystemFromService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @package : com.zc.service.impl.systemfrom
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月08日16:12
 */
@Service
@Transactional
public class SystemFromServiceImpl implements SystemFromService {

    @Autowired
    private SystemFromMapper systemFromMapper;
    @Reference
    private InsideService insideService;
    @Override
    public List<SystemFrom> findSystemNameAndId() {
        return systemFromMapper.findSystemNameAndId();
    }

    @Override
    public SystemFrom findSystemName(String name) {
        return null;
    }



    @Override
    public List<Map<String, Object>> getTakeOutSystemFromList(HashMap<String, Object> param) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(calendar.getTime());
        date += " 23:59:59";
        param.put("dates",date);
        return systemFromMapper.getTakeOutSystemFromList(param);
    }

    @Override
    public SystemFrom getSystemFromBySystemFromName(String systemFrom) {
        SystemFrom s = null;
        try {
            s = systemFromMapper.getSystemFromBySystemFromName(systemFrom);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return s;
    }


    /**
     * @description: 审核失败回调
     * @author:  ZhaoJunBiao
     * @date:  2018-04-25 9:30
     * @version: 1.0.0
     * @param systemName
     * @return
     */
    @Override
    public Result sendFailCash(String systemName) {
        return insideService.sendFailPush(systemName);
    }

    @Override
    public Map<String, Object> findSystemList() {
        List<Map<String,Object>> systemFromList = systemFromMapper.findSystemList();
        Map<String,Object> map = new HashedMap();
        map.put("code","200");
        map.put("content",systemFromList);
        return map;
    }

    @Override
    public List<Map<String, Object>> getTakeOutSystemFromList2(HashMap<String, Object> param) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(calendar.getTime());
        date += " 23:59:59";
        param.put("dates",date);
        return systemFromMapper.getTakeOutSystemFromList2(param);
    }
}
