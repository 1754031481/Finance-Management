package com.zc.service.impl.thiredpaytype;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.thiredpaytype.ThiredPayTypeMapper;
import com.zc.entity.paycontext.PayContext;
import com.zc.entity.thirdpaytype.ThirdPayType;
import com.zc.service.thiredpaytype.ThiredPayTypeService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.service.thiredpaytype
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日17:30
 */
@Service
@Transactional
public class ThiredPayTypeServiceImpl  implements ThiredPayTypeService{

    private Logger logger = Logger.getLogger(ThiredPayTypeServiceImpl.class);

    @Autowired
    private ThiredPayTypeMapper thiredPayTypeMapper;
    @Override
    public ThirdPayType getThirdPayTypeByThirdPayTypeIdAndSystemFromId(Long interFaceId, Long systemFormId) {
        if(null == interFaceId || null == systemFormId ){
            return null;
        }
        return thiredPayTypeMapper.getThirdPayTypeWhyIidAndSid(interFaceId,systemFormId);
    }


    /**
     * @description: 激活接口信息分页展示
     * @author:  ZhaoJunBiao
     * @date:  2018-04-24 15:01
     * @version: 1.0.0
     * @param pageBean
     * @return
     */
    @Override
    public Map<String, Object> findThirdPayType(PageBean pageBean,String type) {
        logger.info("thirdPayType列表查询方法入参");
        Map<String,Object> map=null;
        try {
            PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
            List<Map<String,Object>> syxCashConfigList = thiredPayTypeMapper.findThirdPayType(type);
            PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(syxCashConfigList);
            map = new HashedMap();
            map.put("content",pageInfo.getList());
            map.put("count",pageInfo.getTotal());
            map.put("code",200);
            return map;
        }catch (Exception e){
            logger.info("thirdPayType列表查询异常");
            e.printStackTrace();
            map.put("code",300);
        }
        return map;
    }

    @Override
    public Map<String, Object> findThirdPayTypeList() {
        List<Map<String, Object>> thirdPayTypeList = thiredPayTypeMapper.findThirdPayTypeList();
        Map<String,Object> map = new HashedMap();
        map.put("code","200");
        map.put("content",thirdPayTypeList);
        return map;
    }

    /**
     * @description:  激活接口
     * @author:  ZhaoJunBiao
     * @date:  2018-04-25 15:10
     * @version: 1.0.0
     * @param thirdPayType
     * @return
     */
    @Override
    public Result addTirdPayType(ThirdPayType thirdPayType) {
        Result result = new Result();
        try {
            thirdPayType.setCreatedTime(new Date());
            thirdPayType.setUpdateTime(new Date());
            thiredPayTypeMapper.insert(thirdPayType);
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(0);
            result.setMsg("添加失败");
        }
        return result;
    }

    /**
     * @description: 修改激活接口
     * @author:  ZhaoJunBiao
     * @date:  2018-04-25 15:12
     * @version: 1.0.0
     * @param thirdPayType
     * @return
     */
    @Override
    public Result updateTirdPayType(ThirdPayType thirdPayType) {
        Result result = new Result();
        try {
            thiredPayTypeMapper.updateThirdPayType(thirdPayType);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(0);
            result.setMsg("添加失败");
        }
        return result;
    }
}
