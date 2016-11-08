package ns.major.audit.query;

import java.util.List;

import ns.common.BaseQuery;

public class SysExceptionQuery extends BaseQuery{
	   private String userName;
	   private String sysIDs;
	   private String eModuleName;
       private List<String> initEModuleNames;
       
	public List<String> getInitEModuleNames() {
		return initEModuleNames;
	}

	public void setInitEModuleNames(List<String> initEModuleNames) {
		this.initEModuleNames = initEModuleNames;
	}

	

	public String geteModuleName() {
		return eModuleName;
	}

	public void seteModuleName(String eModuleName) {
		this.eModuleName = eModuleName;
	}

	public String getSysIDs() {
		return sysIDs;
	}

	public void setSysIDs(String sysIDs) {
		this.sysIDs = sysIDs;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
