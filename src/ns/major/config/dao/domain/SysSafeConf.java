package ns.major.config.dao.domain;

import java.util.Date;
/**
 * 系统安全实体类
 * @author Administrator
 *
 */
public class SysSafeConf {

	private String ConfigName;
	private int ConfigId;
	private String ConfigValue;
	private String ConfigDesc;
	private Date UpdateTime;
	public String getConfigName() {
		return ConfigName;
	}
	public void setConfigName(String configName) {
		ConfigName = configName;
	}
	public int getConfigId() {
		return ConfigId;
	}
	public void setConfigId(int configId) {
		ConfigId = configId;
	}
	public String getConfigValue() {
		return ConfigValue;
	}
	public void setConfigValue(String configValue) {
		ConfigValue = configValue;
	}
	public String getConfigDesc() {
		return ConfigDesc;
	}
	public void setConfigDesc(String configDesc) {
		ConfigDesc = configDesc;
	}
	public Date getUpdateTime() {
		return UpdateTime;
	}
	public void setUpdateTime(Date updateTime) {
		UpdateTime = updateTime;
	}
	
}
