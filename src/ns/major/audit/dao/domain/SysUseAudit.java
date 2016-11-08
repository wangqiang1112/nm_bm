package ns.major.audit.dao.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SysUseAudit {

	private int sysUseAuditId;// 系统使用审计ID
	private int userId;// 用户ID
	private String userName;// 用户名称
	private String auditIp;// 操作IP
	private Date auditTime;// 操作时间
	private String umodule;// 操作模块
	private String operation;// 操作内容
	// 时间扩展字段
	private String auditTimeStr;

	public String getAuditTimeStr() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(this.auditTime!=null){
		this.setAuditTimeStr(sdf.format(this.auditTime));
		}else{
			this.setAuditTimeStr("");
		}
		return auditTimeStr;
	}

	public void setAuditTimeStr(String auditTimeStr) {
		this.auditTimeStr = auditTimeStr;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public int getSysUseAuditId() {
		return sysUseAuditId;
	}

	public void setSysUseAuditId(int sysUseAuditId) {
		this.sysUseAuditId = sysUseAuditId;
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

	public String getAuditIp() {
		return auditIp;
	}

	public void setAuditIp(String auditIp) {
		this.auditIp = auditIp;
	}

	public String getUmodule() {
		return umodule;
	}

	public void setUmodule(String umodule) {
		this.umodule = umodule;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
