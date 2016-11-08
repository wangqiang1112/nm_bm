package ns.major.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ns.common.BaseService;
import ns.common.Page;
import ns.major.config.dao.domain.Privilege;
import ns.major.config.dao.domain.Role;
import ns.major.config.dao.domain.User;
import ns.major.config.dao.mapper.UserConfigMapper;
import ns.major.config.query.RoleQuery;
import ns.major.config.query.UserCofQuery;
import ns.major.config.service.UserConfigService;
@Service
public class UserConfigServiceImpl extends BaseService implements UserConfigService {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserConfigMapper userDao;
	public Page getAll(UserCofQuery userQuery)throws Exception  {
		return pageQuery(UserConfigMapper.class, "query", userQuery);
	}
	public void delete(List<Integer> ids)throws Exception  {

		userDao.delete(ids);
	}
	public int add(User user1) throws Exception {
		return userDao.add(user1);
	}
	public int getUnitId(String unitName) throws Exception {
		return userDao.getUnitId(unitName);
	}
	public int getDeptId(String departName) throws Exception {
		return userDao.getDeptId(departName);
	}
	public int update(User user1) throws Exception {
		return userDao.update(user1);
	}
	public int lock(User user)throws Exception  {
		return userDao.lock(user);
	}
	public Page roleList(RoleQuery queryForm)throws Exception  {
		return pageQuery(UserConfigMapper.class, "roleList", queryForm);
	}
	public int roleDelete(List roleIds) throws Exception {
		return userDao.roleDelete(roleIds);
	}
	public int roleAdd(Role role)throws Exception  {
		return userDao.roleAdd(role);
	}
	public int roleUpdate(Role role)throws Exception  {
		return userDao.roleUpdate(role);
	}
	public Role getRoleById(int roleId)throws Exception  {
		return userDao.getRoleById(roleId);
	}
	public List<Role> getAllRole()throws Exception  {
		return userDao.getAllRole();
	}
	public User getUserById(int userId)throws Exception  {
		
		return userDao.getUserById(userId);
	}
	public int updateRole(User user) throws Exception {
		return userDao.updateRole(user);
	}
	public int updatePerm(Role role)throws Exception  {
		
		return userDao.updatePerm(role);
	}
	public List<Privilege> getAllPrivilege() throws Exception {
		return userDao.getAllPrivilege();
	}
	public Boolean userNameCheck(String userName) throws Exception {
		Boolean ifExit=userDao.userNameCheck(userName)==0?true:false;
		return ifExit;
	}
	public Boolean pwLenthCheck(int upwLength) throws Exception {
		Boolean check=false;
		if(userDao.getPwLenth()<=upwLength){
			check=true;
		}
		return check;
	}
	public List<String> getDeptName() throws Exception {
		return userDao.getDeptName();
	}
	public List<String> getUnitName()throws Exception  {
		return  userDao.getUnitName();
	}
	public int getparamSetValue(int i) throws Exception {
		return userDao.getparamSetValue(i);
	}
	public int updateParaSet(int failTimes, int i)throws Exception  {
		return userDao.updateParaSet(failTimes,i);
	}

	public User getUser(int unitId)throws Exception {
		return userDao.getUser(unitId);
	}
	public Role getRoles() throws Exception {
		return userDao.getRoles();
	}
	public void clearLog(int userId)throws Exception  {
		userDao.clearLog(userId);
	}
	public List<User> checkUser(int roleID)throws Exception  {
		
		return userDao.checkUser(roleID);
	}
	public Boolean roleNameCheck(String roleName) throws Exception {
		Boolean ifExit=userDao. roleNameCheck(roleName)==0?true:false;
		return ifExit;
	}
	public List<String> getStafferNames(int departId)throws Exception  {
		
		return userDao.getStafferNames(departId);
	}
	public Boolean unitNameCheck(String unitName) throws Exception {
		Boolean ifExit=userDao. unitNameCheck(unitName)==0?true:false;
		return ifExit;
	}

}
