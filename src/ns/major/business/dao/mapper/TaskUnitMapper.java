package ns.major.business.dao.mapper;

import ns.major.business.dao.domain.Task;

public interface TaskUnitMapper {
	
	Task query(int taskId)throws Exception;
	
	void update(Task task)throws Exception;

}
