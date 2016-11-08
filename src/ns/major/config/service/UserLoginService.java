package ns.major.config.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ns.exception.BaseException;
import ns.major.audit.dao.domain.LoginAuditExcp;
import ns.major.config.dao.domain.Privilege;
import ns.major.config.dao.domain.User;

public interface UserLoginService {

	User findUserByUserName(String userName)throws Exception ;

	List<Privilege> getprivList(int userId)throws Exception ;

	void addLogAudit(LoginAuditExcp loginEx)throws Exception ;

	int getLoginExcByDate(int userId)throws Exception ;

	int IfIsEnable(int userId)throws Exception ;

	int getSysSafeCof()throws Exception ;

	void checkPassWord(HttpServletRequest request,HttpSession session) throws BaseException,Exception;

}
