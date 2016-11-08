package ns.major.audit.service;

import java.util.List;

import ns.common.Page;
import ns.major.audit.dao.domain.SysMethod;
import ns.major.audit.dao.domain.SysUseAudit;
import ns.major.audit.query.SysUseAuditQuery;
import ns.major.config.dao.domain.User;

public interface SysUseAuditService {

	Page query(SysUseAuditQuery query)throws Exception;

	void addSysUse(String url,String ip,User user)throws Exception;

	void delete(List ids)throws Exception;

	List<String> getInitUmodule()throws Exception;

}
