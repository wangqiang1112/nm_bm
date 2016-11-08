package ns.major.audit.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ns.common.BaseService;
import ns.common.Page;
import ns.major.audit.dao.domain.SysException;
import ns.major.audit.dao.mapper.SystemExceptionsMapper;
import ns.major.audit.query.SysExceptionQuery;
import ns.major.audit.service.SysExceptionService;
import ns.major.config.dao.domain.User;

@Service
public class SysExceptionServiceImpl extends BaseService implements SysExceptionService {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	SystemExceptionsMapper mapper;
	
	public Page getAll(SysExceptionQuery query)throws Exception {
		
		return pageQuery(SystemExceptionsMapper.class,"query", query);
	}
	


	public void delete(List ids)throws Exception {
		mapper.delete(ids);
	}


    //获得初始化的模块名字
	public List<String> getInitEModuleName()throws Exception {
		return mapper.getInitEModuleName();
	}



	public void insertOneSysExcption(String model, String context,HttpServletRequest request)throws Exception {
		try {
			User user=(User)request.getSession().getAttribute("User");
			SysException sysException=new SysException();
			sysException.setEModuleName(model);
			if(context.indexOf("NullPointerException")!=-1){
				context="数据错误";
			}
			else {
	            context="系统异常";
			}
			sysException.setExceptionCont(context);
			
			sysException.setUserId(user.getUserId());
			sysException.setUserName(user.getUserName());
			sysException.setSysExceptIp(request.getRemoteAddr());
			sysException.setSysExceptTime(new Date());
			mapper.insertOneSysExcption(sysException);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

   
	

}
