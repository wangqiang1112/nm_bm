package ns.major.audit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ns.common.BaseService;
import ns.common.Page;
import ns.major.audit.dao.domain.SysMethod;
import ns.major.audit.dao.domain.SysUseAudit;
import ns.major.audit.dao.mapper.SysUseAuditMapper;
import ns.major.audit.query.SysUseAuditQuery;
import ns.major.audit.service.SysUseAuditService;
import ns.major.config.dao.domain.User;

@Service
public class SysUseAuditServiceImpl extends BaseService implements
		SysUseAuditService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SysUseAuditMapper sysUseAuditMapper;

	public Page query(SysUseAuditQuery query)throws Exception {
		return pageQuery(SysUseAuditMapper.class, "query", query);
	}

	public void addSysUse(String url,String ip,User user)throws Exception {
		SysMethod sys = sysUseAuditMapper.getMethod(url);
		if(null!=sys){
		SysUseAudit audit = new SysUseAudit();
		audit.setUserId(user.getUserId());
		audit.setAuditIp(ip);
		audit.setUmodule(sys.getModuleName());
		audit.setOperation(sys.getContent());
		audit.setUserName(user.getUserName());
		sysUseAuditMapper.addSysUse(audit);
		}
	}

	public void delete(List ids) throws Exception {
		sysUseAuditMapper.delete(ids);
	}

	public List<String> getInitUmodule()throws Exception {
		return sysUseAuditMapper.getInitUmodule();
	}


}
