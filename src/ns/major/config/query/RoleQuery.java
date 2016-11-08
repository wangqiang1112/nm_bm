package ns.major.config.query;

import ns.common.BaseQuery;

public class RoleQuery extends BaseQuery {
     private String roleIds;
     private String type;

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
     
 }
