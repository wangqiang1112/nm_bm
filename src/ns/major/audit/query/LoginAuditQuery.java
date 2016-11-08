package ns.major.audit.query;

import ns.common.BaseQuery;

public class LoginAuditQuery extends BaseQuery {

	private String userName;
	private String logIds;

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
