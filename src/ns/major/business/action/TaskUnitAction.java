package ns.major.business.action;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;

import ns.common.BaseAction;
import ns.common.Global;
import ns.major.business.dao.domain.Task;
import ns.major.business.query.TaskUnitQuery;
import ns.major.business.service.TaskUnitService;
import ns.major.common.service.BaseDataService;
import ns.major.config.dao.domain.Department;

@Controller
public class TaskUnitAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TaskUnitService taskUnitService;
	private BaseDataService baseDataService;
	private TaskUnitQuery taskUnitQuery;
	private Task task;
	private List<Department> deptList;
	private int pageType;//1:任务处理 2:任务预警
	private int taskId;//任务Id
	

	/**
	 * 首页数据(查询)
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String list() throws Exception {
		try {
			taskUnitQuery = getForm(TaskUnitQuery.class);
			// 获取用户单位
			if(getUserInfo().getUnitId() != 0){
				taskUnitQuery.setUnitId(getUserInfo().getUnitId());
			}
			// 任务处理
			if (pageType == 1) {
				setPage(taskUnitService.list(taskUnitQuery));
				setQuery(taskUnitQuery);
				deptList = baseDataService.getAllDept();	
				return "dealt";
			} 
			// 任务预警
			else if (pageType == 3) {
				setPage(taskUnitService.alertedList(taskUnitQuery));
				setQuery(taskUnitQuery);
				deptList = baseDataService.getAllDept();
				return "warning";
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.BUSINESS_MODEL, e.toString());
			return null;
		}
	}

	/**
	 * 跳转
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String jump() throws Exception {
		task = taskUnitService.query(taskId);
		// 任务处理-处理
		if (pageType == 1) {
			this.setPageType(1);
			return "handle1";
		}
		// 任务处理-查看详情
		if (pageType == 2) {
			this.setPageType(2);
			return "detail";
		}
		// 任务预警-处理
		if (pageType == 3) {
			this.setPageType(3);
			return "handle2";
		}
		return null;
	}

	/**
	 * 处理
	 * @throws Exception 
	 */
	public void update() throws Exception {
		try {
			task = this.getForm(Task.class);
			taskUnitService.update(task);
			// 确认接受
			if(task.getStatus() == 1){
				success("接收成功");
				getHttpResponse().sendRedirect(getContextPath() + "/taskUnit/list.do?pageType=" + pageType );
			}
			// 处理  /taskUnit/list.do?pageType=" + pageType
			else{
				success("处理成功", true);
				getHttpResponse().sendRedirect(getContextPath() + "/toMessage.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.BUSINESS_MODEL, e.toString());
			error("处理失败", true);
			getHttpResponse().sendRedirect(getContextPath() + "/toMessage.do");
		}
	}


	// get/set ---------------------
	
	public TaskUnitService getTaskUnitService() {
		return taskUnitService;
	}

	public void setTaskUnitService(TaskUnitService taskUnitService) {
		this.taskUnitService = taskUnitService;
	}

	public TaskUnitQuery getTaskUnitQuery() {
		return taskUnitQuery;
	}

	public void setTaskUnitQuery(TaskUnitQuery taskUnitQuery) {
		this.taskUnitQuery = taskUnitQuery;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getPageType() {
		return pageType;
	}

	public void setPageType(int pageType) {
		this.pageType = pageType;
	}

	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	public List<Department> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

}
