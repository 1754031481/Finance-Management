package com.zc.service.permission;

import com.zc.common.core.result.Result;
import com.zc.dto.permission.UserDTO;
import com.zc.entity.permission.User;

/**
 * @项目：phshopping-facade-permission
 *
 * @描述：登录校验
 *
 * @作者： Mr.Shu
 *
 * @创建时间：2017-03-15
 *
 * @Copyright @2017 by Mr.Shu
 */
public interface ILoginService {
    /**
     * @描述：登录校验(动态口令)
     *
     * @param: user
     *
     * @return:
     *
     * @作者：Mr.Zu
     *
     * @创建时间：2017/5/17 17:52
     */
    Result login(User user,String ootpCode);


    /**
     * @描述：登录校验
     *
     * @param: user
     *
     * @return:
     *
     * @作者： Mr.Shu
     *
     * @创建时间：2017/5/17 17:52
     */
    Result login(User user);



    /**
     * @描述：忘记密码
     * @param: user
     * @return:
     * @作者： Mr.Shu
     * @创建时间：2017/5/17 17:52
     */
    Result findPass(UserDTO userDTO);
}
