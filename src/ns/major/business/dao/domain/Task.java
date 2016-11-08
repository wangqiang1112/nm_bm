package ns.major.business.dao.domain;

import java.util.Date;

public class Task {

	private Integer taskId;// 任务ID
	private String taskName;// 任务名称
	private String taskNumber;// 任务编号
	private Integer departId;// 部门ID
	private String departName;// 下发部门
	private Date issuedTime;// 下发时间
	private String issuedTime_str;//扩展
	private Date flags6;//任务创建时间
	private Integer receiveUnit;// 接收单位
	private String receiveUnit_name;// 扩展
	private String taskDescript;// 任务描述
	private String taskDetail;// 任务详情
	private String taskFileName;// 任务附件名称
	private String taskFilePath;// 任务附件路径
	private Integer taskTerm;// 任务期限
	private Date processTime;// 处理时间
	private String processTime_str;// 扩展
	private Integer status;// 任务状态
	private String feedBackDetail;// 反馈详情
	private String feedBackFileName;// 反馈文件名称
	private String feedBackFilePath;// 反馈文件路径

	private int clas; // 帐号类型  保密：1 单位：2

	public int getClas() {
		return clas;
	}

	public void setClas(int clas) {
		this.clas = clas;
	}

	public Date getFlags6() {
		return flags6;
	}
	public void setFlags6(Date flags6) {
		this.flags6 = flags6;
	}
	public String getProcessTime_str() {
		return processTime_str;
	}

	public void setProcessTime_str(String processTime_str) {
		this.processTime_str = processTime_str;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Date getIssuedTime() {
		return issuedTime;
	}

	public void setIssuedTime(Date issuedTime) {
		this.issuedTime = issuedTime;
	}

	public String getIssuedTime_str() {
		return issuedTime_str;
	}

	public void setIssuedTime_str(String issuedTime_str) {
		this.issuedTime_str = issuedTime_str;
	}

	public Integer getReceiveUnit() {
		return receiveUnit;
	}

	public void setReceiveUnit(Integer receiveUnit) {
		this.receiveUnit = receiveUnit;
	}

	public String getReceiveUnit_name() {
		return receiveUnit_name;
	}

	public void setReceiveUnit_name(String receiveUnit_name) {
		this.receiveUnit_name = receiveUnit_name;
	}

	public String getTaskDescript() {
		return taskDescript;
	}

	public void setTaskDescript(String taskDescript) {
		this.taskDescript = taskDescript;
	}

	public String getTaskDetail() {
		return taskDetail;
	}

	public void setTaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}

	public String getTaskFileName() {
		return taskFileName;
	}

	public void setTaskFileName(String taskFileName) {
		this.taskFileName = taskFileName;
	}

	public String getTaskFilePath() {
		return taskFilePath;
	}

	public void setTaskFilePath(String taskFilePath) {
		this.taskFilePath = taskFilePath;
	}

	public Integer getTaskTerm() {
		return taskTerm;
	}

	public void setTaskTerm(Integer taskTerm) {
		this.taskTerm = taskTerm;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFeedBackDetail() {
		return feedBackDetail;
	}

	public void setFeedBackDetail(String feedBackDetail) {
		this.feedBackDetail = feedBackDetail;
	}

	public String getFeedBackFileName() {
		return feedBackFileName;
	}

	public void setFeedBackFileName(String feedBackFileName) {
		this.feedBackFileName = feedBackFileName;
	}

	public String getFeedBackFilePath() {
		return feedBackFilePath;
	}

	public void setFeedBackFilePath(String feedBackFilePath) {
		this.feedBackFilePath = feedBackFilePath;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName
				+ ", taskNumber=" + taskNumber + ", departId=" + departId
				+ ", departName=" + departName + ", issuedTime=" + issuedTime
				+ ", issuedTime_str=" + issuedTime_str + ", receiveUnit="
				+ receiveUnit + ", receiveUnit_name=" + receiveUnit_name
				+ ", taskDescript=" + taskDescript + ", taskDetail="
				+ taskDetail + ", taskFileName=" + taskFileName
				+ ", taskFilePath=" + taskFilePath + ", taskTerm=" + taskTerm
				+ ", processTime=" + processTime + ", status=" + status
				+ ", feedBackDetail=" + feedBackDetail + ", feedBackFileName="
				+ feedBackFileName + ", feedBackFilePath=" + feedBackFilePath
				+ ", getTaskId()=" + getTaskId() + ", getTaskName()="
				+ getTaskName() + ", getTaskNumber()=" + getTaskNumber()
				+ ", getDepartId()=" + getDepartId() + ", getDepartName()="
				+ getDepartName() + ", getIssuedTime()=" + getIssuedTime()
				+ ", getIssuedTime_str()=" + getIssuedTime_str()
				+ ", getReceiveUnit()=" + getReceiveUnit()
				+ ", getReceiveUnit_name()=" + getReceiveUnit_name()
				+ ", getTaskDescript()=" + getTaskDescript()
				+ ", getTaskDetail()=" + getTaskDetail()
				+ ", getTaskFileName()=" + getTaskFileName()
				+ ", getTaskFilePath()=" + getTaskFilePath()
				+ ", getTaskTerm()=" + getTaskTerm() + ", getProcessTime()="
				+ getProcessTime() + ", getStatus()=" + getStatus()
				+ ", getFeedBackDetail()=" + getFeedBackDetail()
				+ ", getFeedBackFileName()=" + getFeedBackFileName()
				+ ", getFeedBackFilePath()=" + getFeedBackFilePath()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
