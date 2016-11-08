package ns.major.business.service;

import ns.common.Page;
import ns.major.business.dao.domain.Task;
import ns.major.business.query.TaskUnitQuery;

public interface TaskUnitService {
	
	Task query(int taskId)throws Exception;
	
	void update(Task task)throws Exception;
	
	Page list(TaskUnitQuery query)throws Exception;
	
	Page alertedList(TaskUnitQuery query)throws Exception;

}
