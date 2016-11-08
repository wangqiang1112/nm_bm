package ns.major.audit.dao.mapper;

import java.util.List;

import ns.major.audit.dao.domain.LoginAudit;

public interface LoginAuditMapper {
	
	void addLogTime(LoginAudit audit)throws Exception;
	
	void deleteLogin(List ids)throws Exception;

	void addLogOutTime(LoginAudit audit)throws Exception;
}
