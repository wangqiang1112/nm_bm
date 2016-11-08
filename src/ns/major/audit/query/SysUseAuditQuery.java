package ns.major.audit.query;

import ns.common.BaseQuery;

public class SysUseAuditQuery extends BaseQuery {

	private String userName;
	private String logIds;
	private String umodule;

	
	public String getUmodule() {
		return umodule;
	}

	public void setUmodule(String umodule) {
		this.umodule = umodule;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogIds() {
		return logIds;
	}

	public void setLogIds(String logIds) {
		this.logIds = logIds;
	}

}
