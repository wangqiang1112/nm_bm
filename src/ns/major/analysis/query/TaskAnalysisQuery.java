package ns.major.analysis.query;

import java.util.Date;

import ns.common.BaseQuery;

public class TaskAnalysisQuery extends BaseQuery {
	private int timeUnit;//页面传值 时间粒度
	private String startTime_str;//页面传值
	private Date startTime;
	private String startTime_test;
	private String endTime_str;//页面传值
	private String endTime_test;
	private Date endTime;
	private Integer departId;
	private Integer unitId;
	private Integer status;
	private Integer type;//单位分析 1 下发量 2 未报量 3 已报量 4 已关闭
	
	public String getStartTime_test() {
		return startTime_test;
	}
	public void setStartTime_test(String startTime_test) {
		this.startTime_test = startTime_test;
	}
	public String getEndTime_test() {
		return endTime_test;
	}
	public void setEndTime_test(String endTime_test) {
		this.endTime_test = endTime_test;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public int getTimeUnit() {
		return timeUnit;
	}
	public void setTimeUnit(int timeUnit) {
		this.timeUnit = timeUnit;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStartTime_str() {
		return startTime_str;
	}
	public void setStartTime_str(String startTime_str) {
		this.startTime_str = startTime_str;
	}
	public String getEndTime_str() {
		return endTime_str;
	}
	public void setEndTime_str(String endTime_str) {
		this.endTime_str = endTime_str;
	}
	
	public Integer getDepartId() {
		return departId;
	}
	public void setDepartId(Integer departId) {
		this.departId = departId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "TaskAnalysisQuery [timeUnit=" + timeUnit + ", startTime_str="
				+ startTime_str + ", startTime=" + startTime
				+ ", startTime_test=" + startTime_test + ", endTime_str="
				+ endTime_str + ", endTime_test=" + endTime_test + ", endTime="
				+ endTime + ", departId=" + departId + ", unitId=" + unitId
				+ ", status=" + status + ", type=" + type
				+ ", getStartTime_test()=" + getStartTime_test()
				+ ", getEndTime_test()=" + getEndTime_test() + ", getType()="
				+ getType() + ", getUnitId()=" + getUnitId()
				+ ", getTimeUnit()=" + getTimeUnit() + ", getStartTime()="
				+ getStartTime() + ", getEndTime()=" + getEndTime()
				+ ", getStartTime_str()=" + getStartTime_str()
				+ ", getEndTime_str()=" + getEndTime_str() + ", getDepartId()="
				+ getDepartId() + ", getStatus()=" + getStatus()
				+ ", getPageSize()=" + getPageSize() + ", getPageIndex()="
				+ getPageIndex() + ", getStartDate()=" + getStartDate()
				+ ", getEndDate()=" + getEndDate() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
