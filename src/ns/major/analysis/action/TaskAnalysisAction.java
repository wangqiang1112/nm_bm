package ns.major.analysis.action;

import java.util.List;

import ns.common.BaseAction;
import ns.major.analysis.dao.domain.DepartmentAnalyse;
import ns.major.analysis.dao.domain.EmployAnalyse;
import ns.major.analysis.dao.domain.WarnAnalyse;
import ns.major.analysis.query.TaskAnalysisQuery;
import ns.major.analysis.service.TaskAnalysisService;
import ns.major.common.service.BaseDataService;

public class TaskAnalysisAction extends BaseAction {

	private TaskAnalysisService taskAnalysisService;
	private BaseDataService baseDataService;
	private TaskAnalysisQuery queryForm;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//部门分析
	public String depart() throws Exception {
		queryForm=this.getForm(TaskAnalysisQuery.class);
		
		List<DepartmentAnalyse>list=taskAnalysisService.getDepart(queryForm);
		setBean("departmentAnalyseList",list);
		
		String interval="1";
		for(int i=0;i<list.get(0).getDeptAmount().toString().length();i++)
			if(i!=0)interval+="0";
		Integer max=Integer.parseInt(interval)*10;
		setBean("department_max",max);
		setBean("department_interval",Integer.parseInt(interval));
		
		setBean("query",queryForm);
		return "success";
	}
	//单位分析 
	public String employ()  throws Exception {
		queryForm=this.getForm(TaskAnalysisQuery.class);
		
		
		List<EmployAnalyse>list=taskAnalysisService.getEmploy(queryForm);
		setBean("employAnalyseList",list);
		
		String interval="1";
		if(list.size() > 0){
			for(int i=0;i<list.get(0).getUnitAmount().toString().length();i++)
				if(i!=0)interval+="0";
		}
		
		Integer max=Integer.parseInt(interval)*10;
		setBean("employ_max",max);
		setBean("employ_interval",Integer.parseInt(interval));
		
		setBean("query",queryForm);
		System.out.println("queryForm:"+queryForm.toString());
		return "success";
	}
	//任务预警 
	public String warn() throws Exception {
		queryForm=this.getForm(TaskAnalysisQuery.class);
		
		List<WarnAnalyse>list=taskAnalysisService.getWarn(queryForm);
		setBean("query",queryForm);
		
		String interval="1";
		
		if(list.size() > 0){
			for(int i=0;i<list.get(0).getUnitAmount().toString().length();i++)
				if(i!=0)interval+="0";
		}
		
		Integer max=Integer.parseInt(interval)*10;
		setBean("warn_max",max);
		setBean("warn_interval",Integer.parseInt(interval));
		
		setBean("warnAnalyseList",list);
		return "success";
	}
	
	public TaskAnalysisService getTaskAnalysisService() {
		return taskAnalysisService;
	}

	public void setTaskAnalysisService(TaskAnalysisService taskAnalysisService) {
		this.taskAnalysisService = taskAnalysisService;
	}
	public BaseDataService getBaseDataService() {
		return baseDataService;
	}

	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}
}
