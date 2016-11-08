package ns.major.business.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import ns.common.BaseAction;
import ns.common.Global;
import ns.common.Page;
import ns.major.business.dao.domain.Task;
import ns.major.business.query.TaskQuery;
import ns.major.business.service.TaskService;
import ns.major.common.service.BaseDataService;
import ns.major.config.dao.domain.Department;
import ns.major.config.dao.domain.User;
import ns.major.employ.dao.domain.Unit;

public class TaskAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private BaseDataService baseDataServ;
	private TaskService taskServ;
	private List<Department> deptList;//下发部门
	
	
	//页面 任务发布 新建任务
	public String add() throws Exception{
		//加载单位名称列表
		super.getHttpRequest().setAttribute("list_SUnitName",baseDataServ.getAllUnit());
		super.getHttpRequest().setAttribute("userInfo",this.getUserInfo());
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateStr=sdf.format(new Date());
		super.getHttpRequest().setAttribute("dateStr",dateStr);
		return "success";
	}
	// 页面 任务发布 新建任务 编辑
	public String edit() throws Exception{
		Integer taskId=Integer.parseInt(
				super.getHttpRequest().getParameter("taskId").toString());
		Task task=taskServ.getTaskById(taskId);
		//加载单位名称列表
		super.getHttpRequest().setAttribute("list_SUnitName",baseDataServ.getAllUnit());
		super.getHttpRequest().setAttribute("dateStr",task.getIssuedTime_str());
		super.getHttpRequest().setAttribute("userInfo",this.getUserInfo());
		super.getHttpRequest().setAttribute("bean_Task",task);
		return "success";
	}
	//新建任务 保存
	public void save() throws Exception{
		Task task=this.getForm(Task.class);
		//添加或修改时把ID返回回来
	    int id = taskServ.save(task);
	    if(task.getTaskId()!=null)id=task.getTaskId();
		success("保存成功");
		getHttpResponse().sendRedirect(getContextPath()+"/business/taskList.do?opratId="+id);
	}
	//任务发布 任务查询 列表
	public String list() throws Exception {
		try {
			//测试session
			TaskQuery taskQuery=getForm(TaskQuery.class);
			Page page=taskServ.getAll(taskQuery);
			@SuppressWarnings("unchecked")
			List<Task>list=(List<Task>) page.getResult();
			for(int i=0;i<list.size();i++){
				Task task=list.get(i);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				task.setIssuedTime_str(sdf.format(task.getIssuedTime()));
				SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				task.setProcessTime_str(sdf2.format(task.getProcessTime()));
				Unit unit=baseDataServ.getUnitById(task.getReceiveUnit());
				task.setReceiveUnit_name(unit.getUnitName());
			}
			setPage(page);
			setQuery(taskQuery);
			deptList = baseDataServ.getAllDept();
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.BUSINESS_MODEL, e.toString());
		}
		return "success";
	}
	//任务发布 任务查询 删除
	public void delete() throws Exception{
        String[] taskIds=getForm(TaskQuery.class).getTaskIds().split(",");
        try {
        	List<Integer> ids=new ArrayList<Integer>();
        	for(int i=0;i<taskIds.length;i++){
        		ids.add(Integer.parseInt(taskIds[i]));
        	}
        	taskServ.delete(ids);
			success("删除成功");
        } catch (Exception e) {
        	e.printStackTrace();
        	this.logSysExcption(Global.BUSINESS_MODEL, e.toString());
        	error("删除失败");
        }
		getHttpResponse().sendRedirect(getContextPath()+"/business/taskList.do");
	}
	public void judgeTaskNumber() throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		try {
			String taskNumber=getHttpRequest().getParameter("param");
			User user=this.getUserInfo();
			int departId=user.getDepartId();
			boolean ifExit=taskServ.judgeTaskNumber(departId, taskNumber);
			if(ifExit==true){
				map.put("info", "通过验证");
				map.put("status","y");
			}else{
				map.put("info", "任务编号已经存在");
				map.put("status","n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
			JSONObject json=JSONObject.fromObject(map);
			getHttpResponse().setContentType("text/javascript;charset=UTF-8");
			getHttpResponse().getWriter().write(json.toString());
	}
	//任务详情
	public String taskDetail() throws Exception{
		String id=super.getHttpRequest().getParameter("taskId").toString();
		Integer taskId=Integer.parseInt(id);
		Object statusObj=super.getHttpRequest().getParameter("status");
		if(null!=statusObj){
			Integer status=Integer.parseInt(statusObj.toString());
			taskServ.updateStatus(taskId,status);
		}
		Task task=taskServ.getTaskById(taskId);
		Unit unit=baseDataServ.getUnitById(task.getReceiveUnit());
		task.setReceiveUnit_name(unit.getUnitName());
		setBean("bean_Task",task);
		return "success";
	}
	
	
	
	
	
	// get/set -----------------------------------------
	public TaskService getTaskServ() {
		return taskServ;
	}
	public void setTaskServ(TaskService taskServ) {
		this.taskServ = taskServ;
	}
	public BaseDataService getBaseDataServ() {
		return baseDataServ;
	}
	public void setBaseDataServ(BaseDataService baseDataServ) {
		this.baseDataServ = baseDataServ;
	}
	public List<Department> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}
	
}
