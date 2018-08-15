package com.zc.service.impl.project;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.dao.project.ProjectSupportMapper;
import com.zc.service.project.ProjectSupportService;
import com.zc.vo.project.ProjectSupportVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 王楠
 * @version:
 * @Description: 项目支持服务层实现
 **/
@Service
public class ProjectSupportServiceImpl implements ProjectSupportService {

    Logger logger = LoggerFactory.getLogger(ProjectSupportServiceImpl.class);

    @Autowired
    private ProjectSupportMapper projectSupportMapper;



    /**
     * @param page 分页信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 分页获取项目支持列表
     */
    @Override
    public HashMap<String,Object> getProjectSupportlist(PageBean page) {
        logger.info("--------------------分页获取项目支持列表服务参数："+JSONObject.toJSONString(page));
        HashMap<String,Object> map=new HashMap<>();
        try {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<ProjectSupportVO> list = projectSupportMapper.getProjectSupportlist();
            logger.info("--------------------分页获取项目支持列表服务返回数据列表："+JSONObject.toJSONString(list));
            PageInfo<ProjectSupportVO> info = new PageInfo<>(list);
            map.put("code",200);
            map.put("msg","操作成功");
            map.put("content",info.getList());
            map.put("count",info.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","服务器内部异常");
            logger.error("--------------------分页获取项目支持列表服务异常--------------------------------");
        }
        return map;
    }

    /**
     * @param projectSupportVO 项目支持信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加项目支持
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addProjectSupport(ProjectSupportVO projectSupportVO) {
        logger.info("--------------------添加项目支持服务参数："+JSONObject.toJSONString(projectSupportVO));
        Result result = new Result();
        try {
            projectSupportMapper.addProjectSupport(projectSupportVO);

        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------添加项目支持服务异常--------------------------------");
        }
        return result;
    }

    /**
     * @param projectSupportVO 项目支持信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改项目支持
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateProjectSupport(ProjectSupportVO projectSupportVO) {
        logger.info("--------------------修改项目支持服务参数："+JSONObject.toJSONString(projectSupportVO));
        Result result = new Result();
        try {
            projectSupportMapper.updateProjectSupport(projectSupportVO);
        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------修改项目支持服务异常--------------------------------");
        }
        return result;
    }

    /**
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取支付代付接口列表
     */
    @Override
    public Result getInterface() {
        logger.info("-------------------------获取支付代付接口列表--------------------------------");
        Result result = new Result();
        try {
            //获取支付接口
            List<String> paylist = projectSupportMapper.getPayInterface();
            //获取代付接口
            List<String> cashlist = projectSupportMapper.getCashInterface();

            Map<String,List> map = new HashMap<>();
            map.put("paylist",paylist);
            map.put("cashlist",cashlist);
            result = ResultUtils.returnSuccess("获取信息成功",map);
        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("-----------------------获取支付代付接口列表异常--------------------------------");
        }
        return result;
    }

}
