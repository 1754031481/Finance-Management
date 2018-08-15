package com.zc.service.interfacesupport;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.interfaceentity.InterfaceEntity;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.interfacesupport
 * @progect : Finance-Management
 * @Description : 接口支持模块service
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月23日13:50
 */
public interface InterFaceSupportService {
    Map<String, Object> findInterFaceList(PageBean pageBean,String type);

    Result addInterFace(InterfaceEntity interfaceEntity,String isFinal);

    Result updateInterFace(InterfaceEntity interfaceEntity,String isFinal);

    Map<String,Object> findInterFaceList2();

    List<Map<String,Object>> findPayConfigId(String interFaceName);

    Result selectFinalChannel(String type);
}
