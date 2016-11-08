package ns.major.audit.dao.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SysException {

	private int sysExceptId;
	private int userId;
	private String sysExceptIp;
	private String eModuleName;
	private Date sysExceptTime;
	private String exceptionCont;
	private String userName;
	//拓展字段-系统异常时间
	private String sysExceptTimeStr;
	
	
	
	
	public String getSysExceptTimeStr() {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(this.sysExceptTime!=null){
        this.setSysExceptTimeStr(sdf.format(this.sysExceptTime));
		}
		else{
			 this.setSysExceptTimeStr("");
		}
		return sysExceptTimeStr;
	}
	public void setSysExceptTimeStr(String sysExceptTimeStr) {
		this.sysExceptTimeStr = sysExceptTimeStr;
	}
	public String geteModuleName() {
		return eModuleName;
	}
	public void seteModuleName(String eModuleName) {
		this.eModuleName = eModuleName;
	}
	public String getEModuleName() {
		return eModuleName;
	}
	public void setEModuleName(String eModuleName) {
		this.eModuleName = eModuleName;
	}
	public int getSysExceptId() {
		return sysExceptId;
	}
	public void setSysExceptId(int sysExceptId) {
		this.sysExceptId = sysExceptId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSysExceptIp() {
		return sysExceptIp;
	}
	public void setSysExceptIp(String sysExceptIp) {
		this.sysExceptIp = sysExceptIp;
	}
	
	
	public Date getSysExceptTime() {
		return sysExceptTime;
	}
	public void setSysExceptTime(Date sysExceptTime) {
		this.sysExceptTime = sysExceptTime;
	}
	public String getExceptionCont() {
		return exceptionCont;
	}
	public void setExceptionCont(String exceptionCont) {
		
		this.exceptionCont = exceptionCont;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
