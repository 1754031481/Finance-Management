package com.zc.service.impl.cashreceivestation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.dao.cashreceivestation.CashreceivestationMapper;
import com.zc.dao.paycontext.PayContextMapper;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.service.cash.CashService;
import com.zc.service.cashreceivestationany.CashReceiveStationAnysService;
import com.zc.vo.SystemFromCashShuntingVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @package : com.zc.service.impl.cashreceivestation
 * @progect : Finance-Management
 * @Description :
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月13日16:11
 */
@Service
@Transactional
public class CashReceiveStationAnysServiceImpl implements CashReceiveStationAnysService {

    private Logger logger = Logger.getLogger(CashReceiveStationAnysServiceImpl.class);


    @Autowired
    private CashreceivestationMapper cashreceivestationMapper;
    @Reference
    private CashService cashService;
    @Autowired
    private PayContextMapper payContextMapper;

    @Override
    public List<CashReceiveStation> getCashListBySystem(String systemFrom,Integer status) {
        if (systemFrom == null) {
            return null;
        }
        return cashreceivestationMapper.getCrsInfosByStatus(systemFrom,status);
    }

    @Override
    @Async
    public void batchCash(List<CashReceiveStation> list, Map<String, Object> initialize) {
        logger.info("异步方法调用时间2（batchCash）:"+System.currentTimeMillis());
        for (CashReceiveStation crs : list) {
//            Result payBatch = null;
            try {
                cashService.payBatch(crs, initialize);
            } catch (Exception e) {
                logger.info("<========error==========>发起代付发生异常，异常信息为"+e.getMessage());
            }
		/*	if (null != payBatch) {
				logger.info("财务批量处理代付交易中,订单号:"+crs.getMerSeqId()+",Result结果:"+payBatch.getCode()+","+payBatch.getMsg());
			} else {
				logger.info("财务批量处理代付交易中,订单号:"+crs.getMerSeqId()+",Result结果为空:"+payBatch);
			}*/
        }
    }

    @Override
    public void saveAndModify(CashReceiveStation crs) {
       cashreceivestationMapper.updateCrsStatus(crs);
    }

    /**
     * @description:  根据项目和订单状态查询订单记录(今日以前)
     * @author:  ZhaoJunBiao
     * @date:  2018-04-19 13:59
     * @version: 1.0.0
     * @param systemName
     * @param status
     * @return
     */
    @Override
    public List<CashReceiveStation> getCrsInfosByStatus(String systemName, Integer  status) {
        return cashreceivestationMapper.getCrsInfosByStatus(systemName,status);
    }

    /**
     * @description: 项目代付数据分流情况展示
     * @author:  ZhaoJunBiao
     * @date:  2018-04-19 13:59
     * @version: 1.0.0
     * @param systemFromName
     * @return
     */
    @Override
    public  List<Map<String,Object>> projectShunteDataShow(String systemFromName) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(calendar.getTime());
        return payContextMapper.projectShunteDataShow(systemFromName,date);
    }

    /**
     * @param systemFromName
     * @return
     * @description: 项目代付数据分流配置列表
     * @author: 王楠
     * @date: 2018-06-11 13:55
     * @version: 1.0.0
     */
    @Override
    public Map<String, List> getSystemCashShunting(String systemFromName) {
        logger.info("=========================项目代付数据分流配置列表服务入参："+JSONObject.toJSONString(systemFromName));
        Map<String, List> map = new HashMap<>();
        //查询项目代付分流情况
        List<SystemFromCashShuntingVO> cashShuntings = cashreceivestationMapper.getCashShuntingList(systemFromName);
        logger.info("-------------------------查询项目代付分流情况返回数据："+JSONObject.toJSONString(cashShuntings));
        //查询已开启的分流渠道
        List<String> cashChannels = cashreceivestationMapper.getCashChannelShunting();
        logger.info("-------------------------查询已开启的分流渠道返回数据："+JSONObject.toJSONString(cashChannels));
        map.put("cashShuntings",cashShuntings);
        map.put("cashChannels",cashChannels);
        return map;
    }

    /**
     * @param paramMap fromSystem 项目英文缩写
     *                 estimatedMoney 更换渠道的金额
     *                 fromChannel 被更改渠道
     *                 toChannel 目标渠道
     * @return
     * @description: 修改项目分流配置
     * @author: 王楠
     * @date: 2018-04-19 13:55
     * @version: 1.0.0
     */
    @Override
    public Result updateShuntSettings(Map<String, String> paramMap) {
        Result result = new Result();
        logger.info("=========================修改项目分流配置服务入参："+JSONObject.toJSONString(paramMap));
        BigDecimal estimatedMoney = new BigDecimal(paramMap.get("estimatedMoney"));

        SystemFromCashShuntingVO cashShunting = cashreceivestationMapper.getCashShuntingBySytemAndChannel(paramMap);
        Map<String,String> map = cashreceivestationMapper.getCrsAddress(paramMap.get("toChannel"));
        BigDecimal totalMoney = new BigDecimal(cashShunting.getTotalMoney());
        int count = Integer.valueOf(cashShunting.getTotalNum());

        if (totalMoney.compareTo(estimatedMoney) == -1 ){
            result.setCode(0);
            result.setMsg("抓取金额大于预估金额");
            result.setContent(paramMap.get("fromSystem"));
            return result;
        }

        BigDecimal divide = totalMoney.divide(new BigDecimal(cashShunting.getTotalNum()), 2, BigDecimal.ROUND_HALF_UP);
        int size = estimatedMoney.divide(divide, BigDecimal.ROUND_UP).intValue();

        if (count > 0){
            int i = calculationShuntSettings(paramMap, estimatedMoney, count, 0, size,totalMoney);
            logger.info("修改渠道订单size为:"+i);
            paramMap.put("size",i+"");
            paramMap.put("notifyUrl",map.get("address"));
            paramMap.put("crsTaskAddress",map.get("crsTaskAddress"));
            cashreceivestationMapper.updateCashShuntingBySytemAndChannelLimit(paramMap);
        }
        result = ResultUtils.returnSuccess("分配成功",paramMap.get("fromSystem"));
        return result;
    }

    public int calculationShuntSettings(Map<String,String> paramMap,BigDecimal estimatedMoney,int count,int addSize ,int size,BigDecimal totalMoney){

            if ((size+addSize) >= 0){
                paramMap.put("size",(size + addSize)+"");
                SystemFromCashShuntingVO cashShunting = cashreceivestationMapper.getCashShuntingBySytemAndChannelLimit(paramMap);


                Integer num = Integer.valueOf(cashShunting.getTotalNum());
                if ( num == 0 || cashShunting.getTotalMoney() == null){
                    return size;
                }


                size = size + addSize;
                //与抓取金额差距

                BigDecimal subtract = estimatedMoney.subtract(new BigDecimal(cashShunting.getTotalMoney()));

                if (subtract.compareTo(new BigDecimal(0)) == -1){

                    if (addSize == 1){
                        /*BigDecimal moreSubtract = new BigDecimal(paramMap.get("moreSubtract"));
                        BigDecimal subtract1 = moreSubtract.subtract(estimatedMoney);
                        return subtract.compareTo(subtract1) == 1 ? size+1 : size;*/
                        logger.info("递归运算size为："+size);
                        return size;
                    }
                    addSize =  -1;
                    if (size > 50 || (count-size) > 50){
                        BigDecimal bigDecimal = new BigDecimal(cashShunting.getTotalMoney());
                        BigDecimal avg = bigDecimal.divide(new BigDecimal(size),2, BigDecimal.ROUND_HALF_UP);
                        BigDecimal divide = subtract.divide(avg,0, BigDecimal.ROUND_DOWN);
                        addSize = divide.intValue();
                    }
                    if (addSize == 0){
                        addSize = -1;
                    }
                }else if (subtract.compareTo(new BigDecimal(0)) == 1){
                    addSize = 1;
                    if (size > 50 || (count-size) > 50){
                        BigDecimal balance = totalMoney.subtract(new BigDecimal(cashShunting.getTotalMoney()));
                        BigDecimal avg = balance.divide(new BigDecimal( count - size),2, BigDecimal.ROUND_HALF_UP);
                        addSize = subtract.divide(avg,0, BigDecimal.ROUND_DOWN).intValue();
                    }
                    if (addSize == 0){
                        addSize = 1;
                    }
                }else {
                    return size;
                }
            }
            /*paramMap.put("moreSubtract",subtract.toString());*/
        return calculationShuntSettings(paramMap, estimatedMoney, count, addSize, size ,totalMoney);
    }
    /**
     * @param pageBean
     * @return 页面路径
     * @description: 去重发渠道配额设置页面
     * @author: 王楠
     * @date: 2018-04-19 13:55
     * @version: 1.0.0
     */
    @Override
    public HashMap<String, Object> getReShuntingList(PageBean pageBean) {
        logger.info("=======================分页获取重发渠道配额设置服务入参："+JSONObject.toJSONString(pageBean));
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Map<String,String>> list = cashreceivestationMapper.getReShuntingList();
        logger.info("---------------------获取重发渠道配额设置列表返回数据列表："+JSONObject.toJSONString(list));
        PageInfo<Map<String,String>> info = new PageInfo<>(list);
        map.put("code",200);
        map.put("msg","操作成功");
        map.put("content",info.getList());
        map.put("count",info.getTotal());
        return map;
    }

    /**
     * @param systemFromName
     * @return
     * @description: 重发项目代付数据分流配置列表
     * @author: 王楠
     * @date: 2018-04-19 13:55
     * @version: 1.0.0
     */
    @Override
    public Map<String, List> getReSystemCashShunting(String systemFromName) {
        logger.info("=========================项目重发代付数据分流配置列表服务入参："+JSONObject.toJSONString(systemFromName));
        Map<String, List> map = new HashMap<>();
        //查询项目代付分流情况
        List<SystemFromCashShuntingVO> cashShuntings = cashreceivestationMapper.getReCashShuntingList(systemFromName);
        logger.info("-------------------------查询项目重发代付分流情况返回数据："+JSONObject.toJSONString(cashShuntings));
        //查询已开启的分流渠道
        List<String> cashChannels = cashreceivestationMapper.getCashChannelShunting();
        logger.info("-------------------------查询已开启的分流渠道返回数据："+JSONObject.toJSONString(cashChannels));
        map.put("cashShuntings",cashShuntings);
        map.put("cashChannels",cashChannels);
        return map;
    }

    /**
     * @param paramMap fromSystem 项目英文缩写
     *                 estimatedMoney 更换渠道的金额
     *                 fromChannel 被更改渠道
     *                 toChannel 目标渠道
     * @return
     * @description: 修改项目重发分流配置
     * @author: 王楠
     * @date: 2018-04-19 13:55
     * @version: 1.0.0
     */
    @Override
    public Result updateReShuntSettings(Map<String, String> paramMap) {
        Result result = new Result();
        logger.info("=========================修改项目分流配置服务入参："+JSONObject.toJSONString(paramMap));
        BigDecimal estimatedMoney = new BigDecimal(paramMap.get("estimatedMoney"));

        SystemFromCashShuntingVO cashShunting = cashreceivestationMapper.getReCashShuntingBySytemAndChannel(paramMap);
        Map<String,String> map = cashreceivestationMapper.getCrsAddress(paramMap.get("toChannel"));
        BigDecimal totalMoney = new BigDecimal(cashShunting.getTotalMoney());
        int count = Integer.valueOf(cashShunting.getTotalNum());

        if (totalMoney.compareTo(estimatedMoney) == -1 ){
            result.setCode(0);
            result.setMsg("抓取金额大于预估金额");
            result.setContent(paramMap.get("fromSystem"));
            return result;
        }

        BigDecimal divide = totalMoney.divide(new BigDecimal(cashShunting.getTotalNum()), 2, BigDecimal.ROUND_HALF_UP);
        int size = estimatedMoney.divide(divide, BigDecimal.ROUND_UP).intValue();

        if (count > 0){
            int i = calculationReShuntSettings(paramMap, estimatedMoney, count, 0, size,totalMoney);
            logger.info("修改重发代付渠道订单size为:"+i);
            paramMap.put("size",i+"");
            paramMap.put("notifyUrl",map.get("address"));
            paramMap.put("crsTaskAddress",map.get("crsTaskAddress"));
            cashreceivestationMapper.updateReCashShuntingBySytemAndChannelLimit(paramMap);
        }
        result =  ResultUtils.returnSuccess("分配成功",paramMap.get("fromSystem"));
        return result;
    }



    public int calculationReShuntSettings(Map<String,String> paramMap,BigDecimal estimatedMoney,int count,int addSize ,int size,BigDecimal totalMoney){

        if ((size+addSize)>=0) {
            paramMap.put("size", (size + addSize) + "");
            SystemFromCashShuntingVO cashShunting = cashreceivestationMapper.getReCashShuntingBySytemAndChannelLimit(paramMap);

            Integer num = Integer.valueOf(cashShunting.getTotalNum());
            if (num == 0 || cashShunting.getTotalMoney() == null) {
                return size;
            }

            size = size + addSize;
            //与抓取金额差距

            BigDecimal subtract = estimatedMoney.subtract(new BigDecimal(cashShunting.getTotalMoney()));

            if (subtract.compareTo(new BigDecimal(0)) == -1){

                if (addSize == 1){
                        /*BigDecimal moreSubtract = new BigDecimal(paramMap.get("moreSubtract"));
                        BigDecimal subtract1 = moreSubtract.subtract(estimatedMoney);
                        return subtract.compareTo(subtract1) == 1 ? size+1 : size;*/
                    logger.info("递归运算size为："+size);
                    return size;
                }
                addSize =  -1;
                if (size > 50 || (count-size) > 50){
                    BigDecimal bigDecimal = new BigDecimal(cashShunting.getTotalMoney());
                    BigDecimal avg = bigDecimal.divide(new BigDecimal(size),2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal divide = subtract.divide(avg,0, BigDecimal.ROUND_DOWN);
                    addSize = divide.intValue();
                }
                if (addSize == 0){
                    addSize = -1;
                }
            }else if (subtract.compareTo(new BigDecimal(0)) == 1){
                addSize = 1;
                if (size > 50 || (count-size) > 50){
                    BigDecimal balance = totalMoney.subtract(new BigDecimal(cashShunting.getTotalMoney()));
                    BigDecimal avg = balance.divide(new BigDecimal( count - size),2, BigDecimal.ROUND_HALF_UP);
                    addSize = subtract.divide(avg,0, BigDecimal.ROUND_DOWN).intValue();
                }
                if (addSize == 0){
                    addSize = 1;
                }
            }else {
                return size;
            }
        }else {
            addSize++;
        }
        /*paramMap.put("moreSubtract",subtract.toString());*/
        return calculationReShuntSettings(paramMap, estimatedMoney, count, addSize, size ,totalMoney);
    }

}
