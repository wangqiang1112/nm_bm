package ns.major.analysis.dao.mapper;

import java.util.List;

import ns.major.analysis.query.TaskAnalysisQuery;
import ns.major.config.dao.domain.Department;
import ns.major.employ.dao.domain.Unit;

public interface TaskAnalysisMapper {
	//任务处理-部门分析
	public Integer getDepart(TaskAnalysisQuery query) throws Exception;
	public Integer getEmploy(TaskAnalysisQuery query) throws Exception;	
		
	public Integer getWarn(TaskAnalysisQuery query) throws Exception;
		
	public List<Department> getAllDep() throws Exception;
	public List<Unit> getAllUnit() throws Exception;
}
