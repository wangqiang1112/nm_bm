package ns.major.audit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ns.common.BaseService;
import ns.common.Page;
import ns.major.audit.dao.mapper.LoginAuditExcpMapper;
import ns.major.audit.query.LogAuditExcpQuery;
import ns.major.audit.service.LoginAuditExcpService;

@Service
public class LoginAuditExcpServiceImpl extends BaseService implements LoginAuditExcpService {
	
    private static final long serialVersionUID = 1L;
    @Autowired
    private LoginAuditExcpMapper logmapper;
    
	public Page getAll(LogAuditExcpQuery queryForm) throws Exception {
		
		return pageQuery(LoginAuditExcpMapper.class,"query", queryForm);
	}

	public void delete(List ids)throws Exception {
		logmapper.delete(ids);
		
	}
	 
	

}
