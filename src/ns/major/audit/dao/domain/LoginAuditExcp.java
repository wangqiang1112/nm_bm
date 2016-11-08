package ns.major.audit.dao.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginAuditExcp {

	private int loginExceptId;
	private int UserId;
	private String loginExceptIp;
	private Date loginExceptTime;
	private String LException;
	private String userName;
	private String loginExceptTimeStr;
	
	
	
	public String getLoginExceptTimeStr() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(this.loginExceptTime!=null){
		this.setLoginExceptTimeStr(sdf.format(this.loginExceptTime));
		}else{
			this.setLoginExceptTimeStr("");
		}
		return loginExceptTimeStr;
	}
	public void setLoginExceptTimeStr(String loginExceptTimeStr) {
		this.loginExceptTimeStr = loginExceptTimeStr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getLoginExceptId() {
		return loginExceptId;
	}
	public void setLoginExceptId(int loginExceptId) {
		this.loginExceptId = loginExceptId;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getLoginExceptIp() {
		return loginExceptIp;
	}
	public void setLoginExceptIp(String loginExceptIp) {
		this.loginExceptIp = loginExceptIp;
	}
	public Date getLoginExceptTime() {
		return loginExceptTime;
	}
	public void setLoginExceptTime(Date loginExceptTime) {
		this.loginExceptTime = loginExceptTime;
	}
	public String getLException() {
		return LException;
	}
	public void setLException(String lException) {
		LException = lException;
	}
	
}
