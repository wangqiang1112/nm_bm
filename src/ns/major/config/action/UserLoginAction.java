package ns.major.config.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ns.common.BaseAction;
import ns.exception.BaseException;
import ns.major.audit.dao.domain.LoginAudit;
import ns.major.audit.service.LoginAuditService;
import ns.major.config.dao.domain.Privilege;
import ns.major.config.dao.domain.User;
import ns.major.config.service.UserLoginService;

public class UserLoginAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用户登录审计处理
	private LoginAuditService loginService;
	private List<User> userList;
	private User user;
	private List<Privilege> privList;
	//用户登录处理
	private UserLoginService loginServ;
	//方法
	public String userLogin()throws Exception {
		
		
		try {
			
			WebDeployPath.outPutWebDeployPath(super.getHttpSession().getServletContext().getRealPath("/"));
			
			HttpServletRequest request=getHttpRequest();
			HttpSession session=getHttpSession();
			 //验证密码是否正确
			loginServ.checkPassWord(request,session);
			//session.setMaxInactiveInterval(60)
			
		} 
		catch(BaseException e){
			this.error(e.getMessage());
			return "error";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String left()throws Exception {
		
		try {
			int userId=this.getUserInfo().getUserId();
			System.out.println(userId+"id");
			privList=loginServ.getprivList(userId);
			//获取1，2，3级的权限
			List<Privilege> secondPri=new ArrayList<Privilege>();
			List<Privilege> thridPri=new ArrayList<Privilege>();
			for(Privilege p:privList){
				if((p.getPrivilegeId()+"").length()==2){
					secondPri.add(p);
				}
				if((p.getPrivilegeId()+"").length()==3){
					thridPri.add(p);
				}
			}
			getHttpRequest().setAttribute("privList", privList);
			getHttpRequest().setAttribute("secondPri", secondPri);
			getHttpRequest().setAttribute("thridPri", thridPri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	
	
	/**
	 * 退出--记录登出时间
	 * @return
	 */
	public String logOut()throws Exception {
		try {
			LoginAudit login = new LoginAudit();
			User user = (User)getHttpSession().getAttribute("User");
			login.setUserId(user.getUserId());
			login.setUuid(user.getUUID()); 
			loginService.addLogOutTime(login);
			getHttpSession().invalidate();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return "success";
	}
	
	/*public String getUserType(){
		String userType = session.getAttribute("user").toString();
		return null;
	}*/
	
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
	public LoginAuditService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginAuditService loginService) {
		this.loginService = loginService;
	}
	public List<Privilege> getPrivList() {
		return privList;
	}
	public void setPrivList(List<Privilege> privList) {
		this.privList = privList;
	}
	public UserLoginService getLoginServ() {
		return loginServ;
	}
	public void setLoginServ(UserLoginService loginServ) {
		this.loginServ = loginServ;
	}
	
	
	
}
