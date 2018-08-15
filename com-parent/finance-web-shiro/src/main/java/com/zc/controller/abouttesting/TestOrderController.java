package com.zc.controller.abouttesting;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.CommonConstants;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.logs.TestOrderLogs;
import com.zc.service.abouttesting.TestOrderService;
import com.zc.vo.permission.SessionUserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @package : com.zc.controller.abouttesting
 * @progect : Finance-Management
 * @Description : 关于测试  （测试订单）控制层
 * @Author by :王楠
 * @Creation Date ：2018年06月04日22:42
 */
@Controller
@RequestMapping("aboutTesting/testOrder")
public class TestOrderController {

    @Reference
    private TestOrderService testOrderService;

    /**
     * @author 王楠
     * @version
     * @Description: 跳转浦发扫码渠道
     * @return 页面路径
     * @exception
     **/
    @RequestMapping("listPage")
    public String gotoTestOrderListPage(){
        return "abouttesting/TestOrder";
    }

    /**
     * @author 王楠
     * @version
     * @Description: 获取浦发扫码渠道列表
     * @return 浦发扫码渠道列表
     * @exception
     **/
    @RequestMapping("/TestOrderList")
    @ResponseBody
    public Object getTestOrderList(@ModelAttribute PageBean page){
        HashMap<String,Object> hashMap = testOrderService.getTestOrderList(page);
        return hashMap;
    }

    /**
     * @author 王楠
     * @version
     * @Description: 获取测试银行卡和第三方渠道列表
     * @return  bankNums 测试银行卡列表
     *          channels 第三方渠道列表
     * @exception
     **/
    @RequestMapping("/getBankNumAndChannelList")
    @ResponseBody
    public Object getBankNumAndChannelList(){
        HashMap<String,Object> hashMap = testOrderService.getBankNumAndChannelList();
        return hashMap;
    }
    /**
     * @author 王楠
     * @version
     * @Description: 修改测试订单信息
     * @return  result
     * @exception
     **/
    @RequestMapping(value = "/updateTestOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result updateTestOrder(CashReceiveStation cashReceiveStation, HttpSession session,String createTimeStr){
        Result result = null;

        try {
            SessionUserVO userVO = (SessionUserVO)session.getAttribute(CommonConstants.LOGIN_BACK_USER_SESSION);
            if (userVO == null){
                return ResultUtils.returnError("用户未登录");
            }
            String userName = userVO.getUserName();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            cashReceiveStation.setCreatedTime(date.parse(createTimeStr));
            result = testOrderService.updateTestOrder(cashReceiveStation,userName);
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultUtils.returnError("服务器内部异常");
        }
        return result;
    }

    /**
     * @author 王楠
     * @version
     * @Description: 测试订单操作日志页面
     * @return
     * @exception
     **/
    @RequestMapping(value = "/gotoTestOrderLogs",method = RequestMethod.GET)
    public String gotoTestOrderLogs(){
        return "abouttesting/TestOrderLogs";
    }

    /**
     * @author 王楠
     * @param testOrderLogs 条件查询过滤信息
     * @param page 分页信息
     * @version
     * @Description: 条件查询测试订单日志数据(分页)
     * @return  result
     * @exception
     **/
    @RequestMapping(value = "/getTestOrderLogs")
    @ResponseBody
    public Object getTestOrderLogs(TestOrderLogs testOrderLogs,@ModelAttribute PageBean page,String createTime){
        HashMap<String,Object> hashMap = null;
        if (StringUtils.isBlank(testOrderLogs.getMerSeqId())){
            testOrderLogs.setMerSeqId(null);
        }
        if (StringUtils.isBlank(testOrderLogs.getCreateUser())){
            testOrderLogs.setCreateUser(null);
        }
        if (StringUtils.isBlank(createTime)){
            createTime = null;
        }

        if (createTime != null){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = dateFormat.parse(createTime);
                testOrderLogs.setCreatedTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
                hashMap.put("code",500);
                hashMap.put("msg","时间格式错误");
                return hashMap;
            }
        }
        hashMap = testOrderService.getTestOrderLogs(testOrderLogs,page);
        return hashMap;
    }
}
