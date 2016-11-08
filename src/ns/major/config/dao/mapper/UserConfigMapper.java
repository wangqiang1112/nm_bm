package ns.major.config.dao.mapper;

import java.util.List;
import ns.common.Page;
import ns.major.config.dao.domain.Privilege;
import ns.major.config.dao.domain.Role;
import ns.major.config.dao.domain.User;
import ns.major.config.query.UserCofQuery;

public interface UserConfigMapper {


	public Integer getUesrConfList_Count()throws Exception ;

	public List<String> getDeptName()throws Exception ;

	public List<String> getUnitName()throws Exception ;

	public User getUserById(int userId)throws Exception ;
    //获取参数设置的值
	public int getparamSetValue(int ConfigId)throws Exception ;

	public int updateParaSet(int ConfigValue,int ConfigId)throws Exception ;

	public Page list(UserCofQuery userQuery)throws Exception ;

	public void delete(List ids)throws Exception ;

	public int add(User user1)throws Exception ;

	//根据单位名字找到id
	public int getUnitId(String unitName)throws Exception ;
	//根据部门名字找到id
	public int getDeptId(String departName)throws Exception ;

	public int update(User user1)throws Exception ;

	public int lock(User user)throws Exception ;

	public int roleDelete(List roleIds)throws Exception ;

	public int roleAdd(Role role)throws Exception ;

	public int roleUpdate(Role role)throws Exception ;

	public Role getRoleById(int roleId)throws Exception ;

	public List<Role> getAllRole()throws Exception ;

	public int updateRole(User user)throws Exception ;

	public int updatePerm(Role role)throws Exception ;

	public List<Privilege> getAllPrivilege()throws Exception ;

	public Integer userNameCheck(String userName)throws Exception ;
    
	public int getPwLenth()throws Exception ;

	User getUser(int unitId)throws Exception ;
	
	Role getRoles()throws Exception ;

	public void clearLog(int userId)throws Exception ;

	public List<User> checkUser(int roleID)throws Exception ;

	public int roleNameCheck(String roleName)throws Exception ;

	public List<String> getStafferNames(int departId)throws Exception ;

	public int unitNameCheck(String unitName)throws Exception;
    
}
