package ns.major.config.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ns.common.BaseService;
import ns.exception.BaseException;
import ns.major.audit.dao.domain.LoginAudit;
import ns.major.audit.dao.domain.LoginAuditExcp;
import ns.major.audit.dao.mapper.LoginAuditExcpMapper;
import ns.major.audit.dao.mapper.LoginAuditMapper;
import ns.major.config.dao.domain.Privilege;
import ns.major.config.dao.domain.User;
import ns.major.config.dao.mapper.UserLoginMapper;
import ns.major.config.service.UserLoginService;
import ns.util.SymmetricEncoder;
@Service
public class UserLoginServiceImpl extends BaseService implements UserLoginService {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private UserLoginMapper userlogDao;
	@Autowired
	private LoginAuditExcpMapper logauditDao;
	@Autowired
	private LoginAuditMapper loginAudit;

	public User findUserByUserName(String userName)throws Exception {
		return userlogDao.findUserByUserName(userName);
	}

	public List<Privilege> getprivList(int userId)throws Exception {
		String permis=userlogDao.getPermissions(userId);
		String[] permisArr=permis.split(",");
		List<Privilege> permisList=new ArrayList<Privilege>();
		for(String p:permisArr){
			permisList.add(userlogDao.getOnePermission(Integer.parseInt(p)));
		}
		return permisList;
	}

	public void addLogAudit(LoginAuditExcp loginEx) throws Exception {
		logauditDao.addLogAudit( loginEx);
		
	}

	public int getLoginExcByDate(int userId) throws Exception {
		return logauditDao. getLoginExcByDate( userId);
	}

	public int IfIsEnable(int userId)throws Exception {
		return logauditDao.IfIsEnable(userId);
	}

	public int getSysSafeCof()throws Exception {
		return logauditDao.getSysSafeCof();
	}

	public void checkPassWord(HttpServletRequest request,HttpSession session) throws BaseException,Exception {
		
			String userName=request.getParameter("userName");
			String upassword=request.getParameter("upassword");
			User user=null;
			if(null!=userName && ""!=userName){
				user=new User();
				user=userlogDao.findUserByUserName(userName);
			}
			String msg = "";
			//得到系统安全配置的一天最多能输错的密码次数
			int sysFailTimes=logauditDao.getSysSafeCof();
			if(null==user){
				msg = "用户名不存在";
			}else if(logauditDao.IfIsEnable(user.getUserId())==1){
				msg = "您的帐号被锁定，请先联系管理员解锁";
			}
			else if(logauditDao.getLoginExcByDate(user.getUserId())>=sysFailTimes){
				logauditDao.lockUser(user.getUserId());
				msg = "您的登录次数超出限额，请明天登录";
			}
			else if(!SymmetricEncoder.AESDncode("AES", user.getUpassword()).equals(upassword)){
			    msg = "密码输入错误";
			}
		    if(msg!=""){
		    	String ip=request.getRemoteAddr();
			    LoginAuditExcp loginEx=new LoginAuditExcp();
			    if(user!=null){
			    	loginEx.setUserId(user.getUserId());
			    }
			    if(null==userName){
			    	userName="";
			    }
			    loginEx.setUserName(userName);
			    loginEx.setLException(msg);
			    loginEx.setLoginExceptIp(ip);
			    logauditDao.addLogAudit(loginEx);
		    	throw new BaseException(msg);
		    }
			user.setUUID(UUID.randomUUID().toString());
			////登录成功，加载权限用户信息存入session
			session.setAttribute("User", user);
			LoginAudit login = new LoginAudit();
			login.setUuid(user.getUUID());
			login.setUserId(user.getUserId());
			login.setUserName(user.getUserName());
			login.setLoginIp(request.getRemoteAddr());
			loginAudit.addLogTime(login);
	}
	
}
