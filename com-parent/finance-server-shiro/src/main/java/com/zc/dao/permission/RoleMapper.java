package com.zc.dao.permission;

import com.zc.common.core.orm.mybatis.MyBatisRepository;
import com.zc.dto.permission.RoleDTO;
import com.zc.entity.permission.Role;
import com.zc.vo.permission.RoleVO;
import com.zc.vo.permission.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @描述： 角色mapper
 */
@MyBatisRepository
public interface RoleMapper{

	/**
     * @methodname getRoleListByUserId 的描述：根据用户id获取角色列表
     */
    List<RoleVO> getRoleListByUserId(@Param("userId") Long userId);

	/**
	 * @methodname getRoleByPage 的描述：分页查询角色信息
	 */
    List<RoleVO> getRoleByPage(RoleDTO roleDTO);

	/**
	 * @methodname getAllRole 的描述：查询所有的角色
	 */
    List<RoleVO> getAllRole();

	/**
	 * @methodname selectRoleBySelective 的描述：通过条件查询角色
	 */
	List<Role> selectRoleBySelective(Role role);

    /**
     * @param roleId
     * @return List<UserVO>
     * @methodname getUserListByRoleId 的描述：通过角色Id查询用户列表
     */
    List<UserVO> getUserListByRoleId(@Param("roleId") Long roleId);
    
    int insert(Role role);

	void updateByPrimaryKeySelective(Role role);

	void delete(Role role);
    
}
