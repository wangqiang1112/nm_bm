package ns.major.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ns.common.BaseService;
import ns.common.Page;
import ns.major.business.dao.domain.Task;
import ns.major.business.dao.mapper.TaskUnitMapper;
import ns.major.business.query.TaskUnitQuery;
import ns.major.business.service.TaskUnitService;
import ns.util.UploadFileUtil;


public class TaskUnitServiceImpl extends BaseService implements
		TaskUnitService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private TaskUnitMapper taskMapper;

	public Task query(int taskId)throws Exception {
		return taskMapper.query(taskId);
	}

	public void update(Task task)throws Exception{
		Task taskTemp = new Task();
		taskTemp = this.query(task.getTaskId());
		
		//任务状态修改
		if(task.getStatus()==1){
			taskTemp.setStatus(2);
		}
		if(task.getStatus()==2||task.getStatus()==3){
			taskTemp.setStatus(4);
		}
		if(task.getStatus()==4){
			taskTemp.setStatus(5);
		}
		
		try {
			//移动附件到正式目录 并得到路径 
			if(task.getFeedBackFilePath() != null && task.getFeedBackFilePath() != ""){
				String filepath = UploadFileUtil.moveUploadFile(task.getFeedBackFilePath());
				//设置路径
				taskTemp.setFeedBackFilePath(filepath);
				//设置名字
				taskTemp.setFeedBackFileName(task.getFeedBackFileName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//修改属性赋值
		if(task.getTaskDetail() != null && task.getTaskDetail() != ""){
			taskTemp.setTaskDetail(task.getTaskDetail());
		}
		
		if(task.getFeedBackDetail() != null && task.getFeedBackDetail() != ""){
			taskTemp.setFeedBackDetail(task.getFeedBackDetail());
		}
		
		taskMapper.update(taskTemp);
	}

	public Page list(TaskUnitQuery query)throws Exception {
		Page p = pageQuery(TaskUnitMapper.class, "list", query);
		List<?> list = p.getResult();
		for(int i = 0 ; i < list.size() ; i++){
			Task taskTemp = (Task) list.get(i);
			String issuedTime_str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(taskTemp.getIssuedTime());
			taskTemp.setIssuedTime_str(issuedTime_str);
			// 用户类型
			if(query.getUnitId()!=0){
				taskTemp.setClas(2);
			}
		}
		return p;
	}

	public Page alertedList(TaskUnitQuery query)throws Exception {
		Page p = pageQuery(TaskUnitMapper.class, "alertedList", query);
		List<?> list = p.getResult();
		for(int i = 0 ; i < list.size() ; i++){
			Task taskTemp = (Task) list.get(i);
			String issuedTime_str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(taskTemp.getIssuedTime());
			taskTemp.setIssuedTime_str(issuedTime_str);
			// 用户类型
			if(query.getUnitId()!=0){
				taskTemp.setClas(2);
			}
		}
		return p;
	}

}
