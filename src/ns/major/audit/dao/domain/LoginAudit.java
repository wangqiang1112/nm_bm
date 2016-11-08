package ns.major.audit.dao.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginAudit {

	private int loginAuditId;// 登录审计ID
	private int userId;// 用户ID
	private String userName;// 用户名
	private String loginIp;// 登录IP
	private Date loginTime;// 登录时间
	private Date logOutTime;// 登出时间
	private String uuid; // uuid
	private String logOutTimeStr;//
	private String loginTimeStr;//扩展字段-logOutTime字符串
	
	
    
	public Date getLogOutTime() {
		return logOutTime;
	}

	public String getLogOutTimeStr() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(this.getLogOutTime()!=null){
		this.setLogOutTimeStr(sdf.format(this.getLogOutTime()));}
		else{
			this.setLogOutTimeStr("");
		}
		return logOutTimeStr;
	}

	public void setLogOutTimeStr(String logOutTimeStr) {
		this.logOutTimeStr = logOutTimeStr;
	}

	public void setLogOutTime(Date logOutTime) {
		this.logOutTime = logOutTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginTimeStr() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(this.getLoginTime()!=null){
		this.setLoginTimeStr(sdf.format(this.getLoginTime()));
		}
		else{
			setLoginTimeStr("");
		}
		return loginTimeStr;
	}

	public void setLoginTimeStr(String loginTimeStr) {
		this.loginTimeStr = loginTimeStr;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getLoginAuditId() {
		return loginAuditId;
	}

	public void setLoginAuditId(int loginAuditId) {
		this.loginAuditId = loginAuditId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}



}
