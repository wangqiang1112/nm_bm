package ns.major.audit.dao.mapper;


import java.util.List;
import java.util.Map;

import ns.major.audit.dao.domain.SysException;

public interface SystemExceptionsMapper {



	Integer getSysExceptionList_Count()throws Exception;

	void logSysException(Map sYsExceptionMap)throws Exception;

	List<String> getInitEModuleName()throws Exception;



	void delete(List ids)throws Exception;

	void insertOneSysExcption(SysException sysException)throws Exception;

	

}
