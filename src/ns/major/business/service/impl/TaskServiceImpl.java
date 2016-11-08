package ns.major.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ns.common.BaseService;
import ns.common.Page;
import ns.major.business.dao.domain.Task;
import ns.major.business.dao.mapper.TaskMapper;
import ns.major.business.query.TaskQuery;
import ns.major.business.service.TaskService;
import ns.util.UploadFileUtil;
@Service
public class TaskServiceImpl extends BaseService implements TaskService{
	@Autowired
	TaskMapper taskDao;
	public boolean judgeTaskNumber(int departId,String taskNumber)throws Exception {
		Task task=taskDao.getTaskByDepAndNum(departId,taskNumber);
		if(null==task)return true;
		return false;
	}
	public Page getAll(TaskQuery taskQuery)throws Exception {
		return pageQuery(TaskMapper.class, "query", taskQuery);
	}
	//根据主键获取任务信息
	public Task getTaskById(Integer taskId)throws Exception{
		Task task= taskDao.getTaskById(taskId);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		task.setIssuedTime_str(sdf.format(task.getIssuedTime()));
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		task.setProcessTime_str(sdf2.format(task.getProcessTime()));
		return task;
	}
	//保存任务
	public int save(Task task)throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			task.setIssuedTime(sdf.parse(task.getIssuedTime_str()));
			Task mod=taskDao.getTaskById(task.getTaskId());
			String a=mod.getTaskFilePath();
			
			String b=task.getTaskFilePath();
			if((null==a&&null!=b)||(null!=b&&null!=a&&!b.equals(a))){
				if(!StringUtil.isBlank(task.getTaskFilePath())){
					String filepath = UploadFileUtil.moveUploadFile(task.getTaskFilePath());
					//设置新的路径
					task.setTaskFilePath(filepath);
				}
			}
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
		if(null!=task.getTaskId()&&0!=task.getTaskId()){//编辑
			return taskDao.edit(task);
		}else{
			task.setFlags6(new Date());
			task.setProcessTime(task.getFlags6());
			task.setStatus(1);
;
			taskDao.save(task);
			Integer y=taskDao.getAutoId();
			return y;
		}
		
	}
	//更新任务状态
	public int updateStatus(Integer taskId,Integer status)throws Exception{
		return taskDao.updateStatus(taskId,status);
	}
	//删除任务
	public void delete(List<Integer> ids)throws Exception{
		taskDao.delete(ids);
	}
	
}
