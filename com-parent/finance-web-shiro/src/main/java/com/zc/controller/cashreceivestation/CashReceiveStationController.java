package com.zc.controller.cashreceivestation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.zc.common.core.utils.CommonConstants;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.config.shiro.MyShiroRealm;
import com.zc.dto.permission.UserDTO;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.permission.User;
import com.zc.service.cashreceivestationupdate.CashReceiveStationUpdateService;
import com.zc.service.permission.ILoginService;
import com.zc.service.permission.IUserService;
import com.zc.utils.IpUtil;
import com.zc.vo.permission.SessionUserVO;
import com.zc.vo.permission.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;

/**
 * @package : com.zc.controller.cashreceivestation
 * @progect : Finance-Management
 * @Description :已发起处理订单修改
 * @Description : logger切Cat日志监控  更新时间2018年07月09日14:20
 * @Author by :shiyunshun
 * @Creation Date ：2018年6月08日14:20
 */
@Controller
@RequestMapping("pc/view/CashReceiveStationUpdate")
public class CashReceiveStationController {

    private Logger logger = Logger.getLogger(CashReceiveStationController.class);

    @Reference
    private CashReceiveStationUpdateService cashreceivestationupdateService;


    @Reference
    private IUserService userService;


    @Reference
    private ILoginService loginService;

    @Autowired
    MyShiroRealm myShiroRealm;


    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * @return
     * @description: 跳转发起代付页面
     * @author: shiyunshun
     * @date: 2018-06-08 14:35
     * @version: 1.0.0
     */
    @RequestMapping("toCashReceiveStationquery")
    public String showToFind() {
        return "cashreceivestationupdate/cashreceivestationupdate";
    }


    /**
     * @return
     * @description: 代发起配置信息分页展示
     * @author: wangnan
     * @date: 2018-06-08 14:37
     * @version: 1.0.0
     */
    @RequestMapping("fromSystemList")
    @ResponseBody
    public Object fromSystemList() {
        List<Map<String, Object>> list = cashreceivestationupdateService.getfromSystemList();
        Cat.logEvent("代付发起订单修改接口", "查询代付发起配置信息展示");
        return list;
    }


    /**
     * @param pageBean
     * @return
     * @description: 代发起配置信息分页展示
     * @author: shiyusnhun
     * @date: 2018-06-08 14:37
     * @version: 1.0.0
     */
    @RequestMapping("list")
    @ResponseBody
    public Object showFfPayConfig(PageBean pageBean,String systemFrom,String personName,String orderNum) {
        Cat.logEvent("代付发起订单修改接口", "查询下拉框展示入参", Event.SUCCESS, "参数="+systemFrom+personName+orderNum);
        Map<String, String> paramMap = new HashMap<>();
        if (StringUtils.isBlank(systemFrom)){
            systemFrom = null;
        }
        if (StringUtils.isBlank(personName)){
            personName = null;
        }
        if (StringUtils.isBlank(orderNum)){
            orderNum = null;
        }
        paramMap.put("systemFrom",systemFrom);
        paramMap.put("personName",personName);
        paramMap.put("orderNum",orderNum);

        Map<String, Object> map = cashreceivestationupdateService.getCashReceiveStation(pageBean,paramMap);
        Cat.logEvent("代付发起订单修改接口", "查询下拉框展示出参", Event.SUCCESS, "参数="+map.toString());
        return map;
    }


    /**
     * @param cashReceiveStation
     * @return
     * @description: 代付发起订单修改
     * @author: 史云顺
     * @date: 2018-06-08
     * @version: 2.0.0
     */
    @RequestMapping(value = "CashReceiveStationUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object CashReceiveStationUpdate(CashReceiveStation cashReceiveStation,HttpServletRequest request) throws ParseException {
        Cat.logEvent("代付发起订单修改接口", "执行发起代付修改方法入参", Event.SUCCESS, "参数="+JSON.toJSON(cashReceiveStation));
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(cashReceiveStation.getCreatedTime());
        Date date0 = sdf1.parse(date);
        cashReceiveStation.setCreatedTime(date0);
        //获取当前IP
        String ip = IpUtil.getIpAddr(request);
        //获取操作人信息
        String name = getLoginUser().getUserName();
     //   logger.info("--------------获取发起代付配置数据Conntroller-----------------" + JSON.toJSON(cashReceiveStation));
       if (org.apache.commons.lang.StringUtils.isBlank(cashReceiveStation.getMoney() + "")) {
            return ResultUtil.setResult(false, "金额不能为空！");
        } else if (org.apache.commons.lang.StringUtils.isBlank(cashReceiveStation.getBankName())) {
            return ResultUtil.setResult(false, "银行不能为空！");
        } else if (org.apache.commons.lang.StringUtils.isBlank(cashReceiveStation.getCardNo())) {
            return ResultUtil.setResult(false, "银行卡号不能为空！");
        } else if (org.apache.commons.lang.StringUtils.isBlank(cashReceiveStation.getPersonName())) {
            return ResultUtil.setResult(false, "收款人姓名不能为空！");
        }

        //    logger.info("-------------------执行发起代付修改方法------------------");
            Map<String, Object> map = cashreceivestationupdateService.CashReceiveStationUpdate(cashReceiveStation,ip,name);
        Cat.logEvent("代付发起订单修改接口", "执行发起代付修改方法入参", Event.SUCCESS, "参数="+JSON.toJSON(cashReceiveStation));
            return map;
    }


    public SessionUserVO getLoginUser() {

        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        SessionUserVO info = (SessionUserVO) currentUser.getSession().getAttribute(
                CommonConstants.LOGIN_BACK_USER_SESSION);

        //如果是记住我，处理Session失效
        if (info == null) {
            Object telPhone = currentUser.getPrincipal();
            if (telPhone != null) {
                boolean isRemembered = currentUser.isRemembered();
                if (isRemembered) {
                    //清除权限缓存
                    myShiroRealm.getAuthorizationCache().remove(currentUser.getPrincipals());
                    UserDTO userDTO = new UserDTO();
                    userDTO.setTelphone(telPhone.toString());
                    UserVO userVO = userService.getUserByCondition(userDTO);
                    //对密码进行加密后验证
                    UsernamePasswordToken token = new UsernamePasswordToken(userVO.getTelphone(), userVO.getPassword(), currentUser.isRemembered());
                    //把当前用户放入session
                    currentUser.login(token);
                    Session session = currentUser.getSession();
                    User user = new User();
                    user.setTelphone(userVO.getTelphone());
                    user.setPassword(userVO.getPassword());
                    com.zc.common.core.result.Result result = loginService.login(user);
                    session.setAttribute(CommonConstants.LOGIN_BACK_USER_SESSION, result.getContent());
                    info = (SessionUserVO) result.getContent();
                }
            }

        }
        return info;

    }

}