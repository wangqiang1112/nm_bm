package ns.major.audit.dao.mapper;


import java.util.List;

import ns.major.audit.dao.domain.SysMethod;
import ns.major.audit.dao.domain.SysUseAudit;

public interface SysUseAuditMapper {
	
	void addSysUse(SysUseAudit audit)throws Exception;
	
	void delete(List ids)throws Exception;
	
	SysMethod getMethod(String url)throws Exception;

	List<String> getInitUmodule()throws Exception;

}
