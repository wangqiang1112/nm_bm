package ns.major.audit.service;

import java.util.List;

import ns.common.Page;
import ns.major.audit.dao.domain.LoginAudit;
import ns.major.audit.query.LoginAuditQuery;

public interface LoginAuditService {
	
	void addLogTime(LoginAudit audit)throws Exception;
	
	Page query(LoginAuditQuery query)throws Exception;
	
	void delete(List ids)throws Exception;
	
	void addLogOutTime(LoginAudit audit)throws Exception;

}
