package com.zc.controller.bankcard;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.CommonConstants;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.util.core.ResultUtil;
import com.zc.common.util.result.Result;
import com.zc.config.shiro.MyShiroRealm;
import com.zc.dto.permission.UserDTO;
import com.zc.entity.bankcard.Bankcard;
import com.zc.entity.finance.AlPayConfig;
import com.zc.entity.permission.User;
import com.zc.service.bankcard.BankcardService;
import com.zc.service.finance.AlPayService;
import com.zc.service.permission.ILoginService;
import com.zc.service.permission.IUserService;
import com.zc.utils.IpUtil;
import com.zc.vo.permission.SessionUserVO;
import com.zc.vo.permission.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author 杨文杰
 * @Title: BankcardController
 * @Description: 测试银行卡
 * @date 2018/6/4
 */

@Controller
@RequestMapping("/web/bankcard")
public class BankcardController {

    private static Logger logger = LoggerFactory.getLogger(BankcardController.class);

    @Reference
    private BankcardService bankcardService;

    @Reference
    private IUserService userService;


    @Reference
    private ILoginService loginService;

    @Autowired
    MyShiroRealm myShiroRealm;


    /**
     * 跳转页面
     * @author : 杨文杰
     * @Creation Date ： 2018/6/4
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toBankcardPage",method = {RequestMethod.POST,RequestMethod.GET})
    public  String toBankcardPage(){
        return "bankcard/bankcardList";
    }

    /**
     * 分页获取测试银行卡页面
     * @author : 杨文杰
     * @Creation Date ： 2018/6/4
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toBankcard",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getBankcardList(PageBean pageBean){
        //logger.info("--------------获取测试银行卡数据Conntroller-----------------");
        if(Objects.isNull(pageBean.getPageNum())){
            pageBean.setPageNum(1);
        }
        if(Objects.isNull(pageBean.getPageSize())){
            pageBean.setPageSize(10);
        }
        Result result = bankcardService.getList(pageBean);
        //logger.info("--------------获取测试银行卡数据Conntroller,出参"+JSON.toJSON(result.getData()));
        Cat.logEvent("BankcardController","获取测试银行卡数据列表", Event.SUCCESS,"result="+JSON.toJSON(result.getData()));
        HashMap<String,Object> map= (HashMap<String, Object>) result.getData();
        return  map;
    }

    /**
     * 添加银行卡
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/addBankcard",method = {RequestMethod.POST})
    @ResponseBody
    public  Result addBankcard(Bankcard bankcard, HttpServletRequest request){
        Cat.logEvent("BankcardController","添加测试银行卡入参", Event.SUCCESS,"bankcard="+JSON.toJSON(bankcard));
        //logger.info("--------------添加银行卡数据Conntroller,入参-----------------"+JSON.toJSON(bankcard));
        if(bankcard.getBankcardNum()==null&&bankcard.getBankcardUser()==null&&bankcard.getStatus()==null&&bankcard.getBankName()==null){
            return ResultUtil.setResult(false,"参数为空");
        }
        //获取当前IP
        String ip = IpUtil.getIpAddr(request);
        //获取操作人信息
        String name = getLoginUser().getUserName();
        Result result = bankcardService.addBankcard(bankcard,ip,name);
        Cat.logEvent("BankcardController","添加测试银行卡出参", Event.SUCCESS,"bankcard="+JSON.toJSON(result.getData()));
       // logger.info("--------------添加银行卡数据Conntroller,出参"+JSON.toJSON(result.getData()));
        return  result;
    }

   /**
   * 修改银行卡启用状态
   * @author : 杨文杰
   * @Creation Date ： 2018/4/8
   * @version 1.0.0
   * @param
   *
   */
    @RequestMapping(value = "/updateStatus",method = {RequestMethod.POST})
    @ResponseBody
    public  Result updateStatus(Bankcard bankcard,HttpServletRequest request) {
        Cat.logEvent("BankcardController","银行卡修改启用状态,bankcard="+JSON.toJSON(bankcard));
       // logger.info("银行卡修改启用状态"+JSON.toJSON(bankcard));
        //获取当前IP
        String ip = IpUtil.getIpAddr(request);
        //获取操作人信息
        String name = getLoginUser().getUserName();
        Result result = bankcardService.updateStatus(bankcard,ip,name);
        return result;
    }

    /**
     * 删除银行卡
     * @author : 杨文杰
     * @Creation Date ： 2018/4/8
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/deleteBankcard",method = {RequestMethod.POST})
    @ResponseBody
    public  Result deleteBankcard(Bankcard bankcard,HttpServletRequest request) {
        Cat.logEvent("BankcardController","删除银行卡Controller,id="+bankcard.getId());
        //logger.info("删除银行卡Controller,id="+bankcard.getId());
        //获取当前IP
        String ip = IpUtil.getIpAddr(request);
        //获取操作人信息
        String name = getLoginUser().getUserName();
        Result result = bankcardService.deleteBankcard(bankcard,ip,name);
        return result;
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
