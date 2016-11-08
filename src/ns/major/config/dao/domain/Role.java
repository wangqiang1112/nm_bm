package ns.major.config.dao.domain;

public class Role {
private int RoleId;
private String RoleName;
private String RoleDescrip;
private String Permissions;
public int getRoleId() {
	return RoleId;
}
public void setRoleId(int roleId) {
	RoleId = roleId;
}
public String getRoleName() {
	return RoleName;
}
public void setRoleName(String roleName) {
	RoleName = roleName;
}
public String getRoleDescrip() {
	return RoleDescrip;
}
public void setRoleDescrip(String roleDescrip) {
	RoleDescrip = roleDescrip;
}
public String getPermissions() {
	return Permissions;
}
public void setPermissions(String permissions) {
	Permissions = permissions;
}

}
