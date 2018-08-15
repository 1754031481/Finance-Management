package com.zc.service.impl.configuration;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.configuration.CITICMapper;
import com.zc.entity.configuration.CITICQRcode;
import com.zc.service.configuration.CITICService;
import com.zc.vo.configuration.CITICQRcodeVO;
import com.zc.vo.project.ProjectSupportVO;
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
 * @Description: 中信服务层实现
 **/
@Service
public class CITICServiceImpl implements CITICService {

    Logger logger = LoggerFactory.getLogger(CITICServiceImpl.class);

    @Autowired
    private CITICMapper citicMapper;

    /**
     * @param page
     * @return 中信扫码渠道列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取中信扫码渠道列表
     */
    @Override
    public HashMap<String, Object> getQRcodeList(PageBean page) {
        logger.info("--------------------分页获取中信扫码渠道列表服务入参："+JSONObject.toJSONString(page));
        HashMap<String,Object> map=new HashMap<>();
        try {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<CITICQRcodeVO> list = citicMapper.getQRcodeList();
            logger.info("--------------------分页获取中信扫码渠道列表服务返回数据列表："+JSONObject.toJSONString(list));
            PageInfo<CITICQRcodeVO> info = new PageInfo<>(list);
            map.put("code",200);
            map.put("msg","操作成功");
            map.put("content",info.getList());
            map.put("count",info.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","服务器内部异常");
            logger.error("--------------------分页获取中信扫码渠道列表服务异常--------------------------------");
        }
        return map;
    }

    /**
     * @param citicqRcode
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加中信扫码渠道
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addQRcode(CITICQRcode citicqRcode) {
        logger.info("--------------------添加中信扫码渠道服务参数："+JSONObject.toJSONString(citicqRcode));
        Result result = new Result();
        try {
            citicqRcode.setCreatedTime(new Date());
            citicqRcode.setUpdateTime(new Date());
            citicMapper.insert(citicqRcode);

        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------添加中信扫码渠道服务异常--------------------------------");
        }
        return result;
    }

    /**
     * @param citicqRcode
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改中信扫码渠道
     */
    @Override
    @Transactional()
    public Result updateQRcode(CITICQRcode citicqRcode) throws Exception {
        logger.info("--------------------修改中信扫码渠道服务参数："+JSONObject.toJSONString(citicqRcode));
        Result result = new Result();
        try {
            citicMapper.updateQRcode(citicqRcode);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("--------------------修改中信扫码渠道服务异常--------------------------------");
            throw new Exception();
        }
        return result;
    }
}
