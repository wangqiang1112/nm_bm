package ns.major.audit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ns.common.BaseService;
import ns.common.Page;
import ns.major.audit.dao.domain.LoginAudit;
import ns.major.audit.dao.domain.LoginAuditExcp;
import ns.major.audit.dao.mapper.LoginAuditMapper;
import ns.major.audit.query.LoginAuditQuery;
import ns.major.audit.service.LoginAuditService;

@Service
public class LoginAuditServiceImpl extends BaseService implements LoginAuditService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private LoginAuditMapper loginAudit;

	public void addLogTime(LoginAudit audit)throws Exception {
		loginAudit.addLogTime(audit);
	}

	public Page query(LoginAuditQuery query)throws Exception {
		return pageQuery(LoginAuditMapper.class, "query", query);
	}

	public void delete(List ids)throws Exception {
		loginAudit.deleteLogin(ids);
	}

	public void addLogOutTime(LoginAudit audit)throws Exception {
		loginAudit.addLogOutTime(audit);
	}

	public void addLogAudit(LoginAuditExcp loginEx)throws Exception {
		addLogAudit(loginEx);
		
	}
	
}
