package ns.major.audit.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ns.common.BaseAction;
import ns.common.Global;
import ns.common.Page;
import ns.major.audit.dao.domain.LoginAudit;
import ns.major.audit.query.LoginAuditQuery;
import ns.major.audit.service.LoginAuditService;
import ns.util.office.ExcelMakes;

public class LoginAuditAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private LoginAudit logAudit;
	private LoginAuditService loginService;
	private LoginAuditQuery queryForm;

	/**
	 * 登陆信息列表
	 * @return
	 * @throws Exception 
	 */
	public String list() throws Exception {
		try {
			queryForm = getForm(LoginAuditQuery.class);
			setPage(loginService.query(queryForm));
			setQuery(queryForm);
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.AUDIT_MODEL, e.toString());
			return "error";
		}
		return "success";
	}
	
	/**
	 * 删除--批量 
	 * @throws Exception 
	 */
	public void delete() throws Exception{
		String[] LogIds = getForm(LoginAuditQuery.class).getLogIds().split(",");
		try {
			List ids=new ArrayList();
			for(int i=0;i<LogIds.length;i++){
				ids.add(Integer.parseInt(LogIds[i]));
			}
			loginService.delete(ids);
			success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.AUDIT_MODEL, e.toString());
		}
		getHttpResponse().sendRedirect(getContextPath()+"/audit/loginAudit/list.do");
	}
	
	/**
	 * 导出excel
	 * @throws Exception 
	 */
	public void export() throws Exception {
		queryForm=getForm(LoginAuditQuery.class);
		queryForm.setPageSize(1000000);
		Page page = loginService.query(queryForm);
        List<LoginAudit> list = (List<LoginAudit>) page.getResult();
        //表列名
        String[] cellNames = {"用户名", "登录IP", "登录时间", "登出时间"};
        String[] orderedAttrs = {"UserName", "LoginIp", "loginTimeStr", "LogOutTimeStr"};
        
        ExcelMakes excel = new ExcelMakes("登录记录", cellNames, orderedAttrs);

        excel.setTitile("登录记录");
        excel.addData(list);
    }

	// --------------------------------

	public LoginAudit getLogAudit() {
		return logAudit;
	}

	public void setLogAudit(LoginAudit logAudit) {
		this.logAudit = logAudit;
	}

	public LoginAuditService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginAuditService loginService) {
		this.loginService = loginService;
	}

	public LoginAuditQuery getQueryForm() {
		return queryForm;
	}

	public void setQueryForm(LoginAuditQuery queryForm) {
		this.queryForm = queryForm;
	}

	
	
	

}
