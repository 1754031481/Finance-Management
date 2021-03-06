package com.zc.dao.permission;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.vo.permission.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @项目：phshopping-service-permission
 * @描述： 菜单mapper
 * @作者： Mr.Shu
 * @创建时间：2017-05-12
 * @Copyright @2017 by Mr.Shu
 */
@MyBatisRepository
public interface MenuMapper {

	/**
	 * @methodname getMenuByRoleIdAndUserId 的描述：根据角色id获取菜单
	 * @param roleIds
     * @return java.util.List<com.ph.shopping.facade.permission.vo.MenuVO>
	 * @author Mr.Shu
	 * @create 2017/5/17
     */
    List<MenuVO> getMenuByRoleIds(@Param("roleIds") List<Long> roleIds);


    /**
     * @methodname getAllBaseMenuByRoleID 的描述：通过角色id查询该角色的基础菜单
     * @param roleId
     * @return java.util.List<com.ph.shopping.facade.permission.vo.MenuVO>
	 * @author Mr.Shu
	 * @create 2017/5/17
     */
    List<MenuVO> getAllBaseMenuByRoleId(@Param("roleId") Long roleId);



	/**
	 * @methodname getAllMenu 的描述：查询所有菜单
	 * @param
     * @return java.util.List<com.ph.shopping.facade.permission.vo.MenuVO>
     * @author 郑朋
	 * @create 2017/4/24
	 */
    List<MenuVO> getAllMenu();
}
