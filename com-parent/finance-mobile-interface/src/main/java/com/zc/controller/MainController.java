package com.zc.controller;

import com.zc.common.core.annotation.Explosionproof;
import com.zc.common.core.result.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主页控制器
 * 
 * @author 张靠勤
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2013-6-5 下午9:56:34
 * 
 */
@Controller
@RequestMapping("main")
public class MainController {
/*
   @Reference
   private IUserService userService;*/

    @RequestMapping("/test")
    @ResponseBody
    @Explosionproof
    public com.zc.common.core.result.Result test() {

        /*User user = new User();
        Result r = new Result();
       r =  userService.getUserRoleByUserId(1L);
*/
        return ResultUtils.returnSuccess("");
    }

}
