package ns.major.business.service;

import java.util.List;

import ns.common.Page;
import ns.major.analysis.dao.domain.TaskAnalysis;
import ns.major.analysis.query.TaskAnalysisQuery;
import ns.major.business.dao.domain.Task;
import ns.major.business.query.TaskQuery;

public interface TaskService {
	//验证任务编号  true 可用 
	public boolean judgeTaskNumber(int departId,String taskNumber)throws Exception;
	public Page getAll(TaskQuery taskQuery)throws Exception;
	
	//根据主键获取任务信息
	public Task getTaskById(Integer taskId)throws Exception;
	//新增任务
	public int save(Task task)throws Exception;
	public int updateStatus(Integer taskId,Integer status)throws Exception;
	//删除任务
	public void delete(List<Integer> ids)throws Exception;
	
}
