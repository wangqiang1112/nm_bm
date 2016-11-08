package ns.major.config.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import ns.common.BaseAction;
import ns.common.Global;
import ns.major.config.dao.domain.Privilege;
import ns.major.config.dao.domain.Role;
import ns.major.config.dao.domain.User;
import ns.major.config.query.RoleQuery;
import ns.major.config.query.UserCofQuery;
import ns.major.config.service.UserConfigService;
import ns.major.employ.dao.domain.Unit;
import ns.util.SymmetricEncoder;

public class UserConfigAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<User> userList;
	private User user;
	private UserConfigService userConfServ;
	private List<String> deptNames;
	private List<String> units;
    private List<Role> rolesList;
    private Role role;
	//用户配置
	public String list() throws Exception {

		try {
			UserCofQuery userQuery=getForm(UserCofQuery.class);
			setPage(userConfServ.getAll(userQuery));
			setQuery(userQuery);
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
		return "success";
	}
	public void delete() throws Exception {
		
	   String[] userIds=getForm(UserCofQuery.class).getUserIds().split(",");
		try {
			List<Integer> ids=new ArrayList<Integer>();
			for(int i=0;i<userIds.length;i++){
				ids.add(Integer.parseInt(userIds[i]));
			}
			userConfServ.delete(ids);
			success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			error("删除失败");
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
		
		getHttpResponse().sendRedirect(getContextPath()+"/config/user/list.do");
		
	}
	//跳转页面到增加页面
	public String addOneUser()throws Exception {
		String type=getForm(UserCofQuery.class).getType();
		//加载部门和单位信息
		try {
			deptNames=userConfServ.getDeptName();
			units=userConfServ.getUnitName();
			if(type.equals("edit")){
				int userId=getForm(User.class).getUserId();
				user=userConfServ.getUserById(userId);
				user.setUpassword(SymmetricEncoder.AESDncode("AES", user.getUpassword()));
				//用户部门下所有员工的名字
				List<String> stafferNames=userConfServ.getStafferNames(user.getDepartId());
				setBean("staffNames", stafferNames);
			}
			getHttpRequest().setAttribute("type",type);
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
		 
		
		return "success";
	}
	//增加和修改
	public void add() throws Exception {
		String type=this.getForm(UserCofQuery.class).getType();
        User user1=this.getForm(User.class);
        try {
        	if(user1.getUserType()==1){
            	user1.setDepartId(userConfServ.getDeptId(user1.getDepartName()));
            	user1.setUnitName("");
            }else{
            	user1.setUnitId(userConfServ.getUnitId(user1.getUnitName()));
            	user1.setDepartName("");
            }
            if(type.equals("add")){
            	 user1.setUpassword(SymmetricEncoder.AESEncode("AES", "123456"));
            	 userConfServ.add(user1);
            	 this.success("添加成功",true);
            }
            if(type.equals("edit")){
            	user1.setUpassword(SymmetricEncoder.AESEncode("AES", user1.getUpassword()));
            	userConfServ.update(user1);
            	this.success("编辑成功",true);
            }
           
		} catch (Exception e) {
			e.printStackTrace();
			this.error("操作失败",true);
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
        
        getHttpResponse().sendRedirect(getContextPath()+"/toMessage.do");
	}
	//验证是否已经存在的用户名
	public void userNameCheck() throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		try {
			String userName=getBean("param");
			String type=getBean("type");
			String preName=getBean("preName");
			Boolean ifExit=userConfServ.userNameCheck(userName);
			if(userName.equals(preName)&&type.equals("edit")) ifExit=true;
			if(ifExit==true){
				map.put("info", "通过验证");
				map.put("status","y");
			}else{
				map.put("info", "用户名已存在");
				map.put("status","n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
		JSONObject json=JSONObject.fromObject(map);
		getHttpResponse().setContentType("text/javascript;charset=UTF-8");
		getHttpResponse().getWriter().write(json.toString());
		getHttpResponse().getWriter().close();
	}
	//验证单位下是否已经存在用户
	public void cheakUnit() throws Exception{
		
		Map<String,String> map=new HashMap<String,String>();
		try {
			String unitName=getHttpRequest().getParameter("param");
			Boolean ifExit=userConfServ.unitNameCheck(unitName);
			if(ifExit==true){
				map.put("info", "通过验证");
				map.put("status","y");
			}else{
				map.put("info", "该单位下已经存在用户");
				map.put("status","n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
		JSONObject json=JSONObject.fromObject(map);
		getHttpResponse().setContentType("text/javascript;charset=UTF-8");
		getHttpResponse().getWriter().write(json.toString());
		getHttpResponse().getWriter().close();
		
		
	}
	//验证用户名密码长度
	public void pwLenthCheck() throws Exception {
		Map<String,String> map=new HashMap<String,String>();
	 try {
		  String upassword=getHttpRequest().getParameter("param");
		  int upwLength=upassword.trim().length();
		  Boolean check=userConfServ.pwLenthCheck(upwLength);
		  if(check==true){
				map.put("info", "通过验证");
				map.put("status","y");
			}else{
				map.put("info", "密码长度不符合规定");
				map.put("status","n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    JSONObject json=JSONObject.fromObject(map);
		getHttpResponse().setContentType("text/javascript;charset=UTF-8");
		getHttpResponse().getWriter().write(json.toString());
	}
	//锁定用户
	public void lockUser() throws Exception {
		 user=this.getForm(User.class);
		 if(user.getIsEnable()==0){
			 user.setIsEnable(1);
		 }else{
			 user.setIsEnable(0);
		 }
		 try {
			userConfServ.lock(user);
			if(user.getIsEnable()==0){
			//解除锁定，清空登录异常数据
			userConfServ.clearLog(user.getUserId());
			 this.success("解锁成功");}
			else{
				this.success("锁定成功");
			};
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
			this.error("操作失败");
		}
		 
		 getHttpResponse().sendRedirect(getContextPath()+"/config/user/list.do");
	}
    public String roleallot()throws Exception {
    	try {
    		int userId=getForm(User.class).getUserId();
    		user=userConfServ.getUserById(userId);
    		rolesList=userConfServ.getAllRole();
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
		return "success";
	}
    public void saveRole()throws Exception {
    	User user=getForm(User.class);
    	try {
    		userConfServ.updateRole(user);
			this.success("操作成功",true);
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
			this.error("操作失败",true);
		}
    	 getHttpResponse().sendRedirect(getContextPath()+"/toMessage.do");
    }
    //角色下是否存在用户
    public void checkUser() throws Exception {
    	RoleQuery roleQuery=getForm(RoleQuery.class);
    	boolean flag=false;
    	String [] roleArr=roleQuery.getRoleIds().split(",");
    	try {
    		for(String roleId:roleArr){
        		List<User> user=userConfServ.checkUser(Integer.parseInt(roleId));
        		if(!user.isEmpty()){
        			flag=true;
        			break;
        		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	getHttpResponse().setContentType("text/json;charset=utf-8");
    	getHttpResponse().getWriter().write(flag+"");
    	getHttpResponse().getWriter().close();
    }
    //角色名字除重
    public void roleNameCheck() throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		try {
			String roleName=getBean("param");
			String Name=getBean("preName");
			String type=getBean("type");
			Boolean ifExit=userConfServ.roleNameCheck(roleName);
			if(Name.equals(roleName)) ifExit=true;
			if(ifExit==true){
				map.put("info", "通过验证");
				map.put("status","y");
			}else{
				map.put("info", "角色名已存在");
				map.put("status","n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
		JSONObject json=JSONObject.fromObject(map);
		getHttpResponse().setContentType("text/javascript;charset=UTF-8");
		getHttpResponse().getWriter().write(json.toString());
	}
	//获取参数
	public String getParamset()throws Exception {
		try {
			 Map<String,Object> maps=new HashMap<String,Object>();
			 //获得密码错误次数 ConfigId=1
			 int failTimes =userConfServ.getparamSetValue(1);
			//获得密码最少长度  ConfigId=2
			 int pwLength=userConfServ.getparamSetValue(2);
			 maps.put("failTimes", failTimes);
			 maps.put("pwLength", pwLength);
			getHttpRequest().setAttribute("paraterConfig", maps);
		} catch (Exception e) {
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
			return "error";
		}
		return "success";
	}
	public void savaConfSet() throws Exception {
		int failTimes=Integer.parseInt(getHttpRequest().getParameter("failTimes"));
		int pwLength=Integer.parseInt(getHttpRequest().getParameter("pwLength"));
		try {
			userConfServ.updateParaSet(failTimes,1);
			userConfServ.updateParaSet(pwLength,2);
			this.success("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
			this.error("保存失败");
		}
		getHttpResponse().sendRedirect(getHttpRequest().getContextPath()+"/config/user/getParamset.do");
	}
	/**
	 * 角色配置部分处理
	 */
	public String roleList()throws Exception {
		try {
			RoleQuery queryForm=this.getForm(RoleQuery.class);
			this.setPage(userConfServ.roleList(queryForm));
			this.setQuery(queryForm);
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
			return "error";
		}
		return "success";
	}
	public void roleDelete()throws Exception {
		try {
		    String[] roleIds=  this.getForm(RoleQuery.class).getRoleIds().split(",");
			List<Integer> ids=new ArrayList<Integer>();
				for(int i=0;i<roleIds.length;i++){
					ids.add(Integer.parseInt(roleIds[i]));
				}
		     userConfServ.roleDelete(ids);
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
			this.error("删除失败");
		}
		    this.success("删除成功");
		    getHttpResponse().sendRedirect(getContextPath()+"/config/user/getRoleList.do");
	}
	public String addOneRole()throws Exception {
		String type=getForm(RoleQuery.class).getType();
		//加载部门和单位信息
		try {
			if(type.equals("edit")){
				int roleId=getForm(Role.class).getRoleId();
				 role=userConfServ.getRoleById(roleId);
				 role.getPermissions();
			}
			getHttpRequest().setAttribute("type",type);
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
		return "success";
	}
	/**
	 * 增加或许修改role
	 * @throws IOException 
	 */
	public void roleAdd() throws Exception {
		String type=getForm(RoleQuery.class).getType();
		Role role=getForm(Role.class);
		if(role.getPermissions()==null){
			role.setPermissions("");
		}
		try {
			if(type.equals("add")){
				userConfServ.roleAdd(role);
				this.success("添加成功",true);
			}
			if(type.equals("edit")){
				userConfServ.roleUpdate(role);
				this.success("编辑成功",true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
			this.error("操作失败",true);
		}
		getHttpResponse().sendRedirect(getContextPath()+"/toMessage.do");
	}
	public String getPermission()throws Exception {
		int roleId=getForm(Role.class).getRoleId();
		try {
			Role role=userConfServ.getRoleById(roleId);
			//如果有初始的权限值
			if(!role.getPermissions().isEmpty()){
				//用-前台切割用，join
				//[{ id:1, pId:0, name:"保密宣传教育", open:false,oncheck:true}-{ id:11, pId:1, name:"宣传与教育", open:false,oncheck:false}]
				List<Privilege> priv=userConfServ.getAllPrivilege();
				String[] initPri=role.getPermissions().split(",");
				String html="";
				for(Privilege p:priv){
					Boolean ifExist=false;
					for(int i=0;i<initPri.length;i++){
					if(Integer.parseInt(initPri[i])==(p.getPrivilegeId())){
						ifExist=true;
					}
					}
					html=html+"{ id:"+p.getPrivilegeId()+", pId:"+p.getPid()+", name:'"+p.getPrivilegeName()+"', open:"+ifExist.toString()+",checked:"+ifExist.toString()+"}-";
				}
				html=html.substring(0,html.length()-1);
				//JsonArray arr=JSONArray.fromObject(object);
				getHttpRequest().setAttribute("privileges", html);
			}
		} catch (Exception e) {
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
			e.printStackTrace();
		}
		
		getHttpRequest().setAttribute("roleId", roleId);
		return "success";
	}
	public void savePrivilege() throws Exception {
		Role role=getForm(Role.class);
		try {
			userConfServ.updatePerm(role);
			this.success("操作成功",true);
		} catch (Exception e) {
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
			e.printStackTrace();
			this.error("操作失败",true);
		}
		
		 getHttpResponse().sendRedirect(getContextPath()+"/toMessage.do");
	}
	/**
	 * 
	 */
	
	
	public List<String> getDeptNames() {
		return deptNames;
	}

	public List<Role> getRolesList() {
		return rolesList;
	}
	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}
	public void setDeptNames(List<String> deptNames) {
		this.deptNames = deptNames;
	}

	public List<String> getUnits() {
		return units;
	}

	public void setUnits(List<String> units) {
		this.units = units;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public UserConfigService getUserConfServ() {
		return userConfServ;
	}
	public void setUserConfServ(UserConfigService userConfServ) {
		this.userConfServ = userConfServ;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	

}
