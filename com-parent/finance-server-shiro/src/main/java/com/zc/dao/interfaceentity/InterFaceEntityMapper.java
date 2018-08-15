package com.zc.dao.interfaceentity;

import com.zc.entity.interfaceentity.InterfaceEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.dao
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月08日16:16
 */
public interface InterFaceEntityMapper extends BaseMapper<InterfaceEntity> {

    InterfaceEntity getInterfaceBythirdPayType(String cashName);

    List<Map<String, Object>> findInterFaceList(@Param("type")String type);

    void updateInterFace(InterfaceEntity interfaceEntity);

    List<Map<String,Object>> findPayConfigId(@Param("interFaceName") String interFaceName);

    String selectFinalChannel(@Param("type")String type);


    void reducePayFinalChannel();

    void reduceCashFinalChannel();
}
