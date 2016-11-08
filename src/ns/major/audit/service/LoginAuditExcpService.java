package ns.major.audit.service;

import java.util.List;

import ns.common.Page;
import ns.major.audit.query.LogAuditExcpQuery;

public interface LoginAuditExcpService {

	Page getAll (LogAuditExcpQuery queryForm) throws Exception;

	void delete(List ids) throws Exception;

	
}
