package ns.major.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ns.common.BaseAction;
import ns.common.Global;
import ns.common.Page;
import ns.major.audit.dao.domain.LoginAuditExcp;
import ns.major.audit.query.LogAuditExcpQuery;
import ns.major.audit.service.LoginAuditExcpService;
import ns.util.office.ExcelMakes;

public class LoginAuditExcpAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginAuditExcp logAudit;
    private LoginAuditExcpService loginExcpService;	
    private LogAuditExcpQuery queryForm;
	
	//方法调用
	public String list() throws Exception{
		
		try {
			queryForm=getForm(LogAuditExcpQuery.class);
			setPage(loginExcpService.getAll(queryForm));
		    setQuery(queryForm);
		    //success("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.AUDIT_MODEL, e.toString());
			return "error";
		}
		
		return "success";
	}
	public void delete() throws Exception{
		String[] LogIds=getForm(LogAuditExcpQuery.class).getLogIds().split(",");
		try {
			List ids=new ArrayList();
			for(int i=0;i<LogIds.length;i++){
				ids.add(Integer.parseInt(LogIds[i]));
			}
			loginExcpService.delete(ids);
			success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.AUDIT_MODEL, e.toString());
		}
		getHttpResponse().sendRedirect(getContextPath()+"/audit/loginException/list.do");
	}
	
	/**
	 * 导出excel
	 * @throws Exception 
	 */
	public void export() throws Exception {
		queryForm=getForm(LogAuditExcpQuery.class);
		queryForm.setPageSize(1000000);
		Page page = loginExcpService.getAll(queryForm);
        List list = page.getResult();
        //表列名
        String[] cellNames = {"用户名", "异常IP", "异常时间", "异常内容"};
        String[] orderedAttrs = {"UserName", "LoginExceptIp", "LoginExceptTimeStr", "LException"};
        
        ExcelMakes excel = new ExcelMakes("登录异常", cellNames, orderedAttrs);

        excel.setTitile("登录异常");
        excel.addData(list);
    }
	
	
	
	
	public LoginAuditExcp getLogAudit() {
		return logAudit;
	}
	public void setLogAudit(LoginAuditExcp logAudit) {
		this.logAudit = logAudit;
	}
	public LoginAuditExcpService getLoginExcpService() {
		return loginExcpService;
	}
	public void setLoginExcpService(LoginAuditExcpService loginExcpService) {
		this.loginExcpService = loginExcpService;
	}
	public LogAuditExcpQuery getQueryForm() {
		return queryForm;
	}
	public void setQueryForm(LogAuditExcpQuery queryForm) {
		this.queryForm = queryForm;
	}
	
	
	
}
