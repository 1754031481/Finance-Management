package com.zc.service.impl.permission;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zc.common.core.encrypt.MD5;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.verifycode.CheckOtp;
import com.zc.dto.permission.UserDTO;
import com.zc.service.permission.ILoginService;
import com.zc.entity.permission.User;
import com.zc.vo.permission.SessionUserVO;
import com.zc.vo.permission.UserVO;
import com.zc.dao.permission.UserMapper;
import com.zc.dao.permission.UserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @描述：登录实现层
 */
@Component
@Service
public class LoginServiceImpl implements ILoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    CheckOtp checkOtp = new CheckOtp();


    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    /**
     * 登录校验
     * @param user
     * @return
     */
    @Override
    public Result login(User user,String ootpCode) {
        Result result =new Result();
        logger.info("登录参数入参，user={}", JSON.toJSONString(user));
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setTelphone(user.getTelphone());
            UserVO dbUser = userMapper.getUserByCondition(userDTO);
            if (dbUser == null || !user.getPassword().equals(dbUser.getPassword())) {
                //手机号或者密码有错误
                result.setCode(0405);
                result.setContent("服务器内部错误");
                return result;
            }
            int isable = 2;
            if (dbUser.getIsable() == isable) {
                result.setCode(10005);
                result.setContent("账号被冻结");
                return result;
            }

/*            String otpCode = dbUser.getOtpCode();
            try {
                Result checkOtp2 = checkOtp.checkOtp(otpCode, ootpCode);
                if(checkOtp2==null||!"1".equals(checkOtp2.getCode()+"")){
                    result.setCode(10006);
                    result.setContent("您输入的动态口令有误，请重新输入");
                    return result;
                }
            } catch (Exception e) {
                result.setCode(10006);
                result.setContent("验证动态口令异常，请联系客服");
                return result;
            }*/
            //更新登录日期
            User loginUser = new User();
            loginUser.setId(dbUser.getId());
            loginUser.setLoginTime(new Date());

            //存用户Session
            SessionUserVO sessionUserVo = new SessionUserVO();
            sessionUserVo.setId(dbUser.getId());
            //判断是否是代理商，商户，或者供应商登录
            if (dbUser.getAgentName() != null) {
                sessionUserVo.setUserName(dbUser.getAgentName());
            } else if (dbUser.getMerchantName() != null) {
                sessionUserVo.setUserName(dbUser.getMerchantName());
            } else if (dbUser.getSupplierName() != null) {
                sessionUserVo.setUserName(dbUser.getSupplierName());
            } else {
                sessionUserVo.setUserName(dbUser.getUserName());
            }
            sessionUserVo.setTelphone(dbUser.getTelphone());
            sessionUserVo.setSessionRoleVo(userRoleMapper.selectRoleVOByUserId(dbUser.getId()));
            result.setCode(200);
            result.setContent(sessionUserVo);
            return result;
        } catch (Exception ex) {
            logger.error("登录异常,ex={}", ex);
        }
        result.setCode(0405);
        result.setContent("服务器内部错误");
        return result;
    }



    /**
     * 登录校验
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {
        Result result =new Result();
        logger.info("登录参数入参，user={}", JSON.toJSONString(user));
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setTelphone(user.getTelphone());
            UserVO dbUser = userMapper.getUserByCondition(userDTO);
            if (dbUser == null || !user.getPassword().equals(dbUser.getPassword())) {
                //手机号或者密码有错误
                result.setCode(0405);
                result.setContent("服务器内部错误");
                return result;
            }
            int isable = 2;
            if (dbUser.getIsable() == isable) {
                result.setCode(10005);
                result.setContent("账号被冻结");
                return result;
            }

            //更新登录日期
            User loginUser = new User();
            loginUser.setId(dbUser.getId());
            loginUser.setLoginTime(new Date());

            //存用户Session
            SessionUserVO sessionUserVo = new SessionUserVO();
            sessionUserVo.setId(dbUser.getId());
            //判断是否是代理商，商户，或者供应商登录
            if (dbUser.getAgentName() != null) {
                sessionUserVo.setUserName(dbUser.getAgentName());
            } else if (dbUser.getMerchantName() != null) {
                sessionUserVo.setUserName(dbUser.getMerchantName());
            } else if (dbUser.getSupplierName() != null) {
                sessionUserVo.setUserName(dbUser.getSupplierName());
            } else {
                sessionUserVo.setUserName(dbUser.getUserName());
            }
            sessionUserVo.setTelphone(dbUser.getTelphone());
            sessionUserVo.setSessionRoleVo(userRoleMapper.selectRoleVOByUserId(dbUser.getId()));
            result.setCode(200);
            result.setContent(sessionUserVo);
            return result;
        } catch (Exception ex) {
            logger.error("登录异常,ex={}", ex);
        }
        result.setCode(0405);
        result.setContent("服务器内部错误");
        return result;
    }





    @Override
    public Result findPass(UserDTO userDTO) {
        Result result =new Result();
        logger.info("忘记密码参数入参，userDTO={}", JSON.toJSONString(userDTO));
        try {
            User dbUser = userMapper.findUserByPhone(userDTO.getTelphone());
            if (dbUser == null) {
                //手机号不存在
                result.setCode(100023);
                result.setContent("手机号不存在");
                return result;
            } else {
                int isable = 2;
                if (dbUser.getIsable() == isable) {
                    //账户已被冻结
                    result.setCode(10005);
                    result.setContent("账号被冻结");
                    return result;
                }

                User user = new User();
                user.setId(dbUser.getId());
                user.setTelphone(userDTO.getTelphone());
                user.setPassword(MD5.getMD5Str(userDTO.getPassword()));
                user.setUpdateTime(new Date());
                result.setCode(200);
                return result;
            }
        } catch (Exception ex) {
            logger.error("找回密码异常,ex={}", ex);
           // new PermissionBizException(PermissionEnum.FIND_PASSWORD_ERROR);

        }

        result.setCode(0405);
        result.setContent("服务器内部错误");
        return result;
    }
}
