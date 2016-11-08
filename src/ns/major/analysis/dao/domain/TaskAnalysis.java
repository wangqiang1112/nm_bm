package ns.major.analysis.dao.domain;

public class TaskAnalysis {

	private int taskId; // 任务ID
	private String taskName; // 任务名称
	private int taskNumber; // 任务编号
	private int departId; // 部门ID
	private String departName; // 下发部门
	private String issuedTime; // 下发时间
	private String receiveUnit; // 接收单位
	private String taskDescript; // 任务描述 可为空
	private String taskDetail; // 任务详情 可为空
	private String taskFilePath; // 任务附件 可为空
	private int taskTerm; // 任务期限
	private String processTime; // 处理时间
	private int status; // 任务状态    1:已下发  2：已接收 3：已上报 4：已关闭
	private String feedBackDetail; // 反馈详情 可为空
	private String feedBackFile; // 反馈文件路径 可为空

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(int taskNumber) {
		this.taskNumber = taskNumber;
	}

	public int getDepartId() {
		return departId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getIssuedTime() {
		return issuedTime;
	}

	public void setIssuedTime(String issuedTime) {
		this.issuedTime = issuedTime;
	}

	public String getReceiveUnit() {
		return receiveUnit;
	}

	public void setReceiveUnit(String receiveUnit) {
		this.receiveUnit = receiveUnit;
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

	public String getTaskFilePath() {
		return taskFilePath;
	}

	public void setTaskFilePath(String taskFilePath) {
		this.taskFilePath = taskFilePath;
	}

	public int getTaskTerm() {
		return taskTerm;
	}

	public void setTaskTerm(int taskTerm) {
		this.taskTerm = taskTerm;
	}

	public String getProcessTime() {
		return processTime;
	}

	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFeedBackDetail() {
		return feedBackDetail;
	}

	public void setFeedBackDetail(String feedBackDetail) {
		this.feedBackDetail = feedBackDetail;
	}

	public String getFeedBackFile() {
		return feedBackFile;
	}

	public void setFeedBackFile(String feedBackFile) {
		this.feedBackFile = feedBackFile;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

}
