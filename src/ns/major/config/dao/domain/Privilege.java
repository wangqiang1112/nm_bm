package ns.major.config.dao.domain;

import java.util.Date;

/**
 * 权限实体类
 * @author Administrator
 *
 */
public class Privilege {

	private int PrivilegeId;
	private int pid;
	private String PrivilegeName;
	private String Description;
	private int IsEnabled;
	private Date UpdateTime;
	//扩展字段
	private String url;
	public int getPrivilegeId() {
		return PrivilegeId;
	}
	public void setPrivilegeId(int privilegeId) {
		PrivilegeId = privilegeId;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPrivilegeName() {
		return PrivilegeName;
	}
	public void setPrivilegeName(String privilegeName) {
		PrivilegeName = privilegeName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getIsEnabled() {
		return IsEnabled;
	}
	public void setIsEnabled(int isEnabled) {
		IsEnabled = isEnabled;
	}
	public Date getUpdateTime() {
		return UpdateTime;
	}
	public void setUpdateTime(Date updateTime) {
		UpdateTime = updateTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
