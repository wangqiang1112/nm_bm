package ns.major.business.query;

import ns.common.BaseQuery;

public class TaskUnitQuery extends BaseQuery {

	private String departId;// 下发部门
	private String taskName;// 任务名称
	private String issuedTime; // 下发时间
	private int unitId;// 单位Id

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getIssuedTime() {
		return issuedTime;
	}

	public void setIssuedTime(String issuedTime) {
		this.issuedTime = issuedTime;
	}

}
