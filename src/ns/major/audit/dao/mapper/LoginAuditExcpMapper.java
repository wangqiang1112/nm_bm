package ns.major.audit.dao.mapper;

import java.util.List;
import ns.major.audit.dao.domain.LoginAuditExcp;

public interface LoginAuditExcpMapper {

	List<LoginAuditExcp> getLoginExceptionList()throws Exception;

	Integer getLoginAuditList_Count()throws Exception;

	int addLogAudit(LoginAuditExcp login)throws Exception;

	int getLoginExcByDate(int userId)throws Exception;

	int getSysSafeCof()throws Exception;

	void delete(List ids)throws Exception;

	int IfIsEnable(int userId)throws Exception;

	void lockUser(int userId)throws Exception;

}
