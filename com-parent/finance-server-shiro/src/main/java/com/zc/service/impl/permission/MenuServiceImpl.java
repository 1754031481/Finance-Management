package com.zc.service.impl.permission;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.zc.service.permission.IMenuService;
import com.zc.vo.permission.MenuVO;
import com.zc.vo.permission.RoleVO;
import com.zc.dao.permission.MenuMapper;
import com.zc.dao.permission.RoleMapper;
import com.zc.common.core.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：菜单service
 */
@Component
@Service
public class MenuServiceImpl implements IMenuService {

	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
    public Result getMenuTreeByUserId(Long userId) {
		Result result =new Result();
        try {
            List<RoleVO> list = roleMapper.getRoleListByUserId(userId);
            if (CollectionUtils.isEmpty(list)) {
				result.setCode(10001);
				result.setContent("用户无访问权限");
				return result;
			}
			List<Long> role = new ArrayList<>();
            for (RoleVO r : list) {
                role.add(r.getId());
			}
            List<MenuVO> menuVos = menuMapper.getMenuByRoleIds(role);
			result.setCode(200);
			result.setContent(getMenuTree(menuVos));
			return result;
		} catch (Exception ex) {
			logger.error("通过用户id查询菜单异常，ex={}",ex);
		}

		result.setCode(0405);
		result.setContent("服务器内部错误");
		return result;
	}

    @Override
	public Result getMenuList() {
		Result result=new Result();
		result.setCode(200);
		result.setContent(menuMapper.getAllMenu());
		return result;
	}

    @Override
    public List<MenuVO> getMenuByRoleIds(List<Long> roleIds) {
        return menuMapper.getMenuByRoleIds(roleIds);
    }

//===============================================私有通用方法================================================/

    /**
	 * 获取菜单树
	 * @param menuVos
	 * @return
	 */
    public static List<MenuVO> getMenuTree(List<MenuVO> menuVos) {
        List<MenuVO> treeList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(menuVos)) {
            for (MenuVO vo : menuVos) {
                if (null != vo.getParentId() && Long.valueOf(0).equals(vo.getParentId())) {
					vo = getChildMenu(vo,menuVos);
					treeList.add(vo);
				}
			}
		}
		return treeList;
	}

	private static MenuVO getChildMenu(MenuVO parent, List<MenuVO> menuVos) {
		List<MenuVO> child = null;
		for (MenuVO vo : menuVos) {
			if (parent.getId().equals(vo.getParentId())) {
				vo = getChildMenu(vo, menuVos);
				if (child == null) {
					child = new ArrayList<>();
				}
				child.add(vo);
			}
		}
		parent.setChild(child);
		return parent;
	}


}
