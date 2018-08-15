package com.zc.service.thiredpaytype;

import com.zc.common.core.result.Result;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.thirdpaytype.ThirdPayType;

import java.util.Map;

/**
 * @package : com.zc.service.thiredpaytype
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日17:30
 */
public interface ThiredPayTypeService {

    ThirdPayType getThirdPayTypeByThirdPayTypeIdAndSystemFromId(Long interFaceId, Long systemFormId);

    Map<String,Object> findThirdPayType(PageBean pageBean,String type);

    Map<String,Object> findThirdPayTypeList();

    Result addTirdPayType(ThirdPayType thirdPayType);

    Result updateTirdPayType(ThirdPayType thirdPayType);
}
