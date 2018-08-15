package com.zc.dao.thiredpaytype;

import com.zc.entity.syxconfig.SYXPayConfig;
import com.zc.entity.thirdpaytype.ThirdPayType;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.dao.syxconfig
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月10日9:55
 */
public interface ThiredPayTypeMapper extends BaseMapper<ThirdPayType> {


    ThirdPayType getThirdPayTypeWhyIidAndSid(@Param("interFaceId") Long interFaceId, @Param("systemFormId")Long systemFormId);

    List<Map<String,Object>> findThirdPayType(@Param("type")String type);

    List<Map<String,Object>> findThirdPayTypeList();

    void updateThirdPayType(ThirdPayType thirdPayType);
}
