package ns.major.config.service;

import java.util.List;

import ns.common.Page;
import ns.major.config.dao.domain.Privilege;
import ns.major.config.dao.domain.Role;
import ns.major.config.dao.domain.User;
import ns.major.config.query.RoleQuery;
import ns.major.config.query.UserCofQuery;
/**
 * 处理用户配置，包括登录
 * @author Administrator
 *
 */
public interface UserConfigService {

	Page getAll(UserCofQuery userQuery)throws Exception ;

	void delete(List<Integer> ids)throws Exception ;

	int add(User user1)throws Exception ;

	int getUnitId(String unitName)throws Exception ;

	int getDeptId(String departName)throws Exception ;

	int update(User user1)throws Exception ;

	int lock(User user)throws Exception ;

	Page roleList(RoleQuery queryForm)throws Exception ;

	int roleDelete(List roleIds)throws Exception ;

	int roleAdd(Role role)throws Exception ;

	int roleUpdate(Role role)throws Exception ;

	Role getRoleById(int roleId)throws Exception ;

	List<Role> getAllRole()throws Exception ;

	User getUserById(int userId)throws Exception ;

	int updateRole(User user)throws Exception ;

	int updatePerm(Role role)throws Exception ;

	List<Privilege> getAllPrivilege()throws Exception ;
	//判断是否存在相同的用户名
	Boolean userNameCheck(String userName)throws Exception ;

	Boolean pwLenthCheck(int upwLength)throws Exception ;

	List<String> getDeptName()throws Exception ;

	List<String> getUnitName()throws Exception ;

	int getparamSetValue(int i)throws Exception ;

	int updateParaSet(int failTimes, int i)throws Exception ;


	User getUser(int unitId)throws Exception ;
	
	Role getRoles()throws Exception ;

	void clearLog(int userId)throws Exception ;
    //验证是否存在角色用户，存在不允许删除
	List<User> checkUser(int parseInt)throws Exception ;
    //判断是否存在相同的角色名
	Boolean roleNameCheck(String roleName)throws Exception ;
    //得到用户部门下所有员工名字
	List<String> getStafferNames(int departId)throws Exception ;
	//验证单位下是否已经存在用户
	Boolean unitNameCheck(String unitName)throws Exception;
	
}
