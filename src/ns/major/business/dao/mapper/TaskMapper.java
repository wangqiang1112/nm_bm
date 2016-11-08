package ns.major.business.dao.mapper;

import java.util.List;

import ns.major.analysis.dao.domain.TaskAnalysis;
import ns.major.business.dao.domain.Task;

public interface TaskMapper {
	public Task getTaskByDepAndNum(int departId,String taskNumber )throws Exception;
	public Task getTaskById(Integer taskId)throws Exception;
	//保存
	public int save(Task task)throws Exception;
	public int getAutoId()throws Exception;
	//更新状态
	public int updateStatus(Integer taskId,Integer status)throws Exception;
	//编辑
	public int edit(Task task)throws Exception;
	//删除
	public void delete(List<Integer>ids)throws Exception;

	
}
