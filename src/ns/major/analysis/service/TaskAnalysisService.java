package ns.major.analysis.service;

import java.util.List;

import ns.major.analysis.dao.domain.DepartmentAnalyse;
import ns.major.analysis.dao.domain.EmployAnalyse;
import ns.major.analysis.dao.domain.WarnAnalyse;
import ns.major.analysis.query.TaskAnalysisQuery;

public interface TaskAnalysisService {
	public List<DepartmentAnalyse> getDepart(TaskAnalysisQuery query) throws Exception;
	public List<EmployAnalyse> getEmploy(TaskAnalysisQuery query) throws Exception;
	public List<WarnAnalyse> getWarn(TaskAnalysisQuery query) throws Exception;
}
