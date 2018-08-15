package com.zc.controller.finance;


import com.alibaba.dubbo.config.annotation.Reference;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.CommonConstants;
import com.zc.common.core.utils.page.PageBean;
import com.zc.config.shiro.MyShiroRealm;
import com.zc.dto.permission.UserDTO;
import com.zc.entity.permission.User;
import com.zc.service.bankcard.BankcardService;
import com.zc.service.finance.CashmentHistoryService;
import com.zc.service.permission.ILoginService;
import com.zc.service.permission.IUserService;
import com.zc.utils.IpUtil;
import com.zc.vo.permission.SessionUserVO;
import com.zc.vo.permission.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author: 王楠
 * @version:
 * @Description: 代付交易历史记录控制层
 **/
@Controller
@RequestMapping("finance/cashmentHistory")
public class CashmentHistoryController {

    @Reference
    private CashmentHistoryService cashmentHistoryService;

    @Reference
    private IUserService userService;


    @Reference
    private ILoginService loginService;

    @Autowired
    MyShiroRealm myShiroRealm;

    /**
     * @author 王楠
     * @version
     * @Description: 跳转代付交易历史记录页面
     * @return 页面路径
     * @exception
     **/
    @RequestMapping("/listPage")
    public String gotoProjectSupport(){

        return "finance/cashmentHistory";
    }

    /**
     * @param page 分页信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 分页获取代付交易历史记录列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getlist(@ModelAttribute PageBean page) {
        HashMap<String,Object> hashMap = cashmentHistoryService.getlist(page);
        return hashMap;
    }

    /**
     * @param project 分页信息
     * @return
     * @throws
     * @author 王楠
     * @version
     * @Description: 获取一个项目一周代付交易历史记录列表
     */
    @RequestMapping(value = "/getWeekData", method = RequestMethod.GET)
    @ResponseBody
    public Result getWeekData(String project) {
        Result result = cashmentHistoryService.getWeekData(project);
        return result;
    }

    /**
     * @return
     * @throws
     * @author 杨文杰
     * @version
     * @Description: 更改启用状态
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public Result updateStatus(Long id,Integer status,HttpServletRequest request) {
        //获取当前IP
        String ip = IpUtil.getIpAddr(request);
        //获取操作人信息
        String name = getLoginUser().getUserName();
        Result result = cashmentHistoryService.updateStatus(ip,name,id,status);
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
