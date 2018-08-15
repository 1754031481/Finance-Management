package com.zc.controller.permission;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zc.service.permission.IMenuService;
import com.zc.service.permission.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zc.common.core.result.Result;

@RequestMapping("permission")
@Controller
public class MenuController {

    @Reference
    private IMenuService menuService;

    @Reference
    private IUserService userService;


    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String toListRolePage() {
        return "permission/user";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getMenuByUserId(@RequestParam(value = "userId", required = true) Long userId) {
        Result result = menuService.getMenuTreeByUserId(userId);
        return result;
    }


    /**
     * @描述：获取用户拥有的角色列表
     */
    @RequestMapping(value = "/getUserRole", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserRole(Long userId) {
        return userService.getUserRoleByUserId(userId);
    }
}
