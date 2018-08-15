package com.zc.service.impl.push;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.project.ProjectSupportMapper;
import com.zc.dao.push.CashOrderMapper;
import com.zc.dao.push.PayOrderMapper;
import com.zc.entity.push.PushTask;
import com.zc.entity.push.PushTaskPay;
import com.zc.service.push.OrderService;
import com.zc.vo.push.CashSystemFromSelectDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 王楠
 * @version:
 * @Description: 关于推送订单服务层实现
 **/
@Service
public class OrderServiceImpl implements OrderService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private CashOrderMapper cashOrderMapper;

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Autowired
    private ProjectSupportMapper projectSupportMapper;




    /**
     * @param page 页面信息
     * @return 代付订单列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取关于推送代付订单列表
     */
    @Override
    public HashMap<String, Object> getCashList(PageBean page,PushTask pushTask) {
        logger.info("--------------------分页获取关于推送代付订单列表服务入参："+JSONObject.toJSONString(page));
        logger.info("--------------------条件查询信息："+JSONObject.toJSONString(pushTask));
        HashMap<String,Object> map=new HashMap<>();
        try {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<PushTask> list = cashOrderMapper.getList(pushTask);
            logger.info("--------------------分页获取关于推送代付订单列表返回数据列表："+JSONObject.toJSONString(list));
            PageInfo<PushTask> info = new PageInfo<>(list);
            map.put("code",200);
            map.put("msg","操作成功");
            map.put("content",info.getList());
            map.put("count",info.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","服务器内部异常");
            logger.error("--------------------分页获获取关于推送代付订单列表服务异常--------------------------------");
        }
        return map;
    }

    /**
     * @param pushTask 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加关于推送代付订单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addCash(PushTask pushTask) {
        logger.info("--------------------添加关于推送代付订单服务参数："+JSONObject.toJSONString(pushTask));
        Result result = new Result();
        try {
            pushTask.setCreatedTime(new Date());
            pushTask.setUpdateTime(new Date());
            BigDecimal bigDecimal = new BigDecimal(pushTask.getMoney());
            BigDecimal money = bigDecimal.multiply(new BigDecimal(100));
            pushTask.setMoney(money.toString());
            cashOrderMapper.insert(pushTask);

        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------添加关于推送代付订单服务异常--------------------------------");
        }
        return result;
    }

    /**
     * @param pushTask 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改关于推送代付订单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateCash(PushTask pushTask) {
        logger.info("--------------------修改关于推送代付订单服务参数："+JSONObject.toJSONString(pushTask));
        Result result = new Result();
        try {
            BigDecimal bigDecimal = new BigDecimal(pushTask.getMoney());
            BigDecimal money = bigDecimal.multiply(new BigDecimal(100));
            BigDecimal scale = money.setScale(0, BigDecimal.ROUND_UNNECESSARY);
            pushTask.setMoney(scale.toString());
            cashOrderMapper.update(pushTask);
        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------修改关于推送代付订单服务异常--------------------------------");
        }
        return result;
    }

    /**
     * @return 代付订单下拉列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取关于推送代付订单下拉列表
     */
    @Override
    public Map<String, List> getCashSelect() {
        logger.info("-----------------------修改关于推送代付订单服务---------------------------");
        Map<String, List> map = new HashMap<>();
        try {
            List<CashSystemFromSelectDTO> systemFrom = cashOrderMapper.selectSystemFromName();
            List<String> cashInterface = projectSupportMapper.getCashInterface();
            List<String> payInterface = projectSupportMapper.getPayInterface();
            map.put("systemFrom",systemFrom);
            map.put("cashInterface",cashInterface);
            map.put("payInterface",payInterface);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("--------------------修改关于推送代付订单服务异常--------------------------------");
        }
        return map;
    }

    /**
     * @param page 页面信息
     * @return 支付订单列表
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取关于推送支付订单列表
     */
    @Override
    public HashMap<String, Object> getPayList(PageBean page,PushTaskPay pushTaskPay) {
        logger.info("--------------------分页获取关于推送支付订单列表服务入参："+JSONObject.toJSONString(page));
        logger.info("--------------------关于推送支付订单列表服务条件查询入参："+JSONObject.toJSONString(pushTaskPay));
        HashMap<String,Object> map=new HashMap<>();
        try {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<PushTaskPay> list = payOrderMapper.getList(pushTaskPay);
            logger.info("--------------------分页获取关于推送支付订单列表返回数据列表："+JSONObject.toJSONString(list));
            PageInfo<PushTaskPay> info = new PageInfo<>(list);
            map.put("code",200);
            map.put("msg","操作成功");
            map.put("content",info.getList());
            map.put("count",info.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","服务器内部异常");
            logger.error("--------------------分页获取关于推送支付订单列表服务异常--------------------------------");
        }
        return map;
    }

    /**
     * @param pushTaskPay 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 添加关于推送支付订单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addPay(PushTaskPay pushTaskPay) {
        logger.info("--------------------添加关于推送支付订单服务参数："+JSONObject.toJSONString(pushTaskPay));
        Result result = new Result();
        try {
            pushTaskPay.setCreatedTime(new Date());
            pushTaskPay.setUpdateTime(new Date());
            BigDecimal bigDecimal = new BigDecimal(pushTaskPay.getMoney());
            BigDecimal money = bigDecimal.multiply(new BigDecimal(100));
            pushTaskPay.setMoney(money.toString());
            payOrderMapper.insert(pushTaskPay);

        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------添加关于推送支付订单服务异常--------------------------------");
        }
        return result;
    }

    /**
     * @param pushTaskPay 配置信息
     * @return Result
     * @throws
     * @author 王楠
     * @version
     * @Description: 修改关于推送支付订单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updatePay(PushTaskPay pushTaskPay) {
        logger.info("--------------------修改关于推送支付订单服务参数："+JSONObject.toJSONString(pushTaskPay));
        Result result = new Result();
        try {
            BigDecimal bigDecimal = new BigDecimal(pushTaskPay.getMoney());
            BigDecimal money = bigDecimal.multiply(new BigDecimal(100));
            BigDecimal scale = money.setScale(0, BigDecimal.ROUND_UNNECESSARY);
            pushTaskPay.setMoney(scale.toString());
            payOrderMapper.update(pushTaskPay);
        }catch (Exception e){
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
            logger.error("--------------------修改关于推送支付订单服务异常--------------------------------");
        }
        return result;
    }

    /**
     * @param id
     * @return Result
     * @throws
     * @author ywj
     * @version
     * @Description: 删除推送
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deletePush(Long id) {
        logger.info("-------------删除支付推送订单，id号为"+id);
        Result result = new Result();
        try {
            PushTaskPay pushTaskPay = new PushTaskPay();
            pushTaskPay.setId(id);
            int i = payOrderMapper.delete(pushTaskPay);
            if(i<1){
                return ResultUtils.returnError("删除支付推送订单失败");
            }
        }catch (Exception e){
            logger.info("删除支付推送订单失败"+e);
            return ResultUtils.returnError("删除支付推送订单失败");
        }
        result.setCode(200);
        result.setMsg("删除成功");
        return result;
    }

    /**
     * @param id
     * @return Result
     * @throws
     * @author ywj
     * @version
     * @Description: 删除代付推送
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deletePushCash(Long id) {
        logger.info("-------------删除支付推送订单，id号为"+id);
        Result result = new Result();
        try {
            PushTask pushTask = new PushTask();
            pushTask.setId(id);
            int i = cashOrderMapper.delete(pushTask);
            if(i<1){
                return ResultUtils.returnError("删除支付推送订单失败");
            }
        }catch (Exception e){
            logger.info("删除支付推送订单失败"+e);
            return ResultUtils.returnError("删除支付推送订单失败");
        }
        result.setCode(200);
        result.setMsg("删除成功");
        return result;
    }
}
