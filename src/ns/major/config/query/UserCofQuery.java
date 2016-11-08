package ns.major.config.query;

import ns.common.BaseQuery;

public class UserCofQuery extends BaseQuery{

	private String userName;
	//用于删除用户信息
	private String userIds;
	//操作类型-增，删
	private String type;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
