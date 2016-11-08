package ns.major.business.query;

import ns.common.BaseQuery;

public class TaskQuery extends BaseQuery {
	//搜索条件
	private String departName;//部门名称
	private String taskName;//任务名称
	private String taskNumber;//任务编号
	private Integer status;//处理状态
	private String departId;// 下发部门ID
	
	
	//用于删除用户信息
	private String taskIds;
	//操作类型-增，删
	private String type;
	
	
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTaskIds() {
		return taskIds;
	}
	public void setTaskIds(String taskIds) {
		this.taskIds = taskIds;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "TaskQuery [departName=" + departName + ", taskName=" + taskName
				+ ", status=" + status + ", taskIds=" + taskIds + ", type="
				+ type + "]";
	}
	
	
}
