package ns.major.audit.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import ns.common.BaseAction;
import ns.common.Global;
import ns.common.Page;
import ns.major.audit.dao.domain.SysException;
import ns.major.audit.query.SysExceptionQuery;
import ns.major.audit.service.SysExceptionService;
import ns.util.office.ExcelMakes;

public class SystemExceptionsAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private SysExceptionService sysService;
	private SysException sysExcption;
	private SysExceptionQuery queryForm;
	//开始执行方法
	public String list() throws Exception{
		try {
			//初始化模块功能选选择
			List<String> modelNames=sysService.getInitEModuleName();
			getHttpRequest().setAttribute("InitEModuleName", modelNames);
			queryForm = this.getForm(SysExceptionQuery.class);
		    setPage(sysService.getAll(queryForm));
		    setQuery(queryForm);
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.AUDIT_MODEL, e.toString());
			return "error";
		}
		return "success";
	}
	public void delete() throws Exception{
		String[] sysExceptIds=getForm(SysExceptionQuery.class).getSysIDs().split(",");
		try {
			List ids=new ArrayList();
			for(int i=0;i<sysExceptIds.length;i++){
				ids.add(Integer.parseInt(sysExceptIds[i]));
			}
			sysService.delete(ids);
			success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.AUDIT_MODEL, e.toString());
		}
		getHttpResponse().sendRedirect(getContextPath()+"/audit/sysException/list.do");
	}
	
	
	/**
	 * 导出excel
	 * @throws Exception 
	 * @throws IOException
	 */
	public void export() throws Exception {
		try {
			queryForm = this.getForm(SysExceptionQuery.class);
			queryForm.setPageSize(1000000);
			Page page = sysService.getAll(queryForm);
	        List<SysException> list = (List<SysException>) page.getResult();
	        //表列名
	        String[] cellNames = {"用户名", "操作Ip","功能模块", "异常时间", "异常内容"};
	        String[] orderedAttrs = {"UserName","SysExceptIp", "EModuleName", "sysExceptTimeStr", "ExceptionCont"};
	        
	        ExcelMakes excel = new ExcelMakes("系统异常", cellNames, orderedAttrs);

	        excel.setTitile("系统异常");
	        excel.addData(list);
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.AUDIT_MODEL, e.toString());
		}
		
        
    }
	
	public SysExceptionService getSysService() {
		return sysService;
	}
	public void setSysService(SysExceptionService sysService) {
		this.sysService = sysService;
	}
	public SysException getSysExcption() {
		return sysExcption;
	}
	public void setSysExcption(SysException sysExcption) {
		this.sysExcption = sysExcption;
	}
	public SysExceptionQuery getQueryForm() {
		return queryForm;
	}
	public void setQueryForm(SysExceptionQuery queryForm) {
		this.queryForm = queryForm;
	}
	
}
