package com.zc.service.impl.configuration;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.configuration.SPDMapper;
import com.zc.entity.configuration.SPDQRcode;
import com.zc.service.configuration.SPDService;
import com.zc.vo.configuration.CITICQRcodeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: 王楠
 * @version:
 * @Description: 浦发服务层实现
 **/
@Service
public class SPDServiceImpl implements SPDService {

    Logger logger = LoggerFactory.getLogger(SPDServiceImpl.class);

    @Autowired
    private SPDMapper spdMapper;


    /**
     * @param page
     * @return HashMap
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取扫码渠道列表
     */
    @Override
    public HashMap<String, Object> getQRcodeList(PageBean page) {
        logger.info("--------------------分页获取浦发获取扫码渠道列表服务入参："+JSONObject.toJSONString(page));
        HashMap<String,Object> map=new HashMap<>();
        try {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<SPDQRcode> list = spdMapper.getQRcodeList();
            logger.info("--------------------分页获获取浦发获取扫码渠道列表服务返回数据列表："+JSONObject.toJSONString(list));
            PageInfo<SPDQRcode> info = new PageInfo<>(list);
            map.put("code",200);
            map.put("msg","操作成功");
            map.put("content",info.getList());
            map.put("count",info.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","服务器内部异常");
            logger.error("--------------------分页获取浦发获取扫码渠道列表服务异常--------------------------------");
        }
        return map;
    }

    /**
     * @param spdqRcode
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加扫码渠道
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addQRcode(SPDQRcode spdqRcode) {
        logger.info("--------------------添加浦发添加扫码渠道服务参数："+JSONObject.toJSONString(spdqRcode));
        Result result = new Result();
        try {
            spdqRcode.setCreatedTime(new Date());
            spdqRcode.setUpdateTime(new Date());
            spdMapper.insert(spdqRcode);

        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------添加浦发扫码渠道服务异常--------------------------------");
        }
        return result;
    }

    /**
     * @param spdqRcode
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改扫码渠道
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateQRcode(SPDQRcode spdqRcode) {
        logger.info("--------------------修改浦发扫码渠道服务参数："+JSONObject.toJSONString(spdqRcode));
        Result result = new Result();
        try {
            spdMapper.updateQRcode(spdqRcode);
        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------修改浦发扫码渠道服务异常--------------------------------");
        }
        return result;
    }
}
